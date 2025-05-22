package com.example.listxml.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listxml.data.model.Agent
import com.example.listxml.databinding.FragmentHomeBinding
import com.example.listxml.ui.adapter.AgentAdapter
import com.example.listxml.ui.adapter.OnAgentClickListener
import com.example.listxml.viewmodel.AgentViewModel
import com.example.listxml.viewmodel.AgentViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AgentViewModel by activityViewModels {
        AgentViewModelFactory("Agent List")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = getListAgent()
        viewModel.setAgentList(list)

        viewModel.agentList.collectIn(viewLifecycleOwner) { agentList ->
            val adapter = AgentAdapter(agentList, object : OnAgentClickListener {
                override fun onDetailClicked(agent: Agent) {
                    viewModel.selectAgent(agent)
                    val fragment = DetailFragment()
                    parentFragmentManager.beginTransaction()
                        .replace(com.example.listxml.R.id.frame_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }

                override fun onLinkClicked(agent: Agent) {
                    viewModel.onLinkClicked(agent)
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(agent.detail)))
                }
            })
            binding.rvAgent.layoutManager = LinearLayoutManager(context)
            binding.rvAgent.adapter = adapter
        }
    }

    private fun getListAgent(): List<Agent> {
        val dataName = resources.getStringArray(com.example.listxml.R.array.data_agentName)
        val dataDesc = resources.getStringArray(com.example.listxml.R.array.data_agentDesc)
        val dataRole = resources.getStringArray(com.example.listxml.R.array.data_agentRole)
        val dataImage = resources.obtainTypedArray(com.example.listxml.R.array.data_agentImage)
        val dataDetail = resources.getStringArray(com.example.listxml.R.array.data_agentLink)
        val list = mutableListOf<Agent>()
        for (i in dataName.indices) {
            val agent = Agent(
                dataName[i], dataRole[i], dataDesc[i],
                dataImage.getResourceId(i, -1), dataDetail[i]
            )
            list.add(agent)
        }
        dataImage.recycle()
        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun <T> StateFlow<T>.collectIn(owner: LifecycleOwner, collector: suspend (T) -> Unit) {
    owner.lifecycleScope.launch {
        owner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@collectIn.collectLatest {
                collector(it)
            }
        }
    }
}