package com.example.listxml

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listxml.databinding.FragmentHomeBinding
import android.net.Uri

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var agentAdapter: AgentAdapter
    private val list = ArrayList<Agent>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        list.clear()
        list.addAll(getListAgent())
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        agentAdapter = AgentAdapter(
            list,
            object : OnAgentClickListener {
                override fun onDetailClicked(agent: Agent) {
                    val detailFragment = DetailFragment().apply {
                        arguments = Bundle().apply {
                            putString("EXTRA_NAME", agent.name)
                            putString("EXTRA_DESC", agent.desc)
                            putString("EXTRA_ROLE", agent.role)
                            putInt("EXTRA_IMAGE", agent.image)
                        }
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, detailFragment)
                        .addToBackStack(null)
                        .commit()
                }

                override fun onLinkClicked(linkUrl: String) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
                    startActivity(intent)
                }
            },
        )
        binding.rvAgent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = agentAdapter
        }
    }

    private fun getListAgent(): ArrayList<Agent> {
        val dataName = resources.getStringArray(R.array.data_agentName)
        val dataDesc = resources.getStringArray(R.array.data_agentDesc)
        val dataRole = resources.getStringArray(R.array.data_agentRole)
        val dataImage = resources.obtainTypedArray(R.array.data_agentImage)
        val dataDetail = resources.getStringArray(R.array.data_agentLink)
        val listAgent = ArrayList<Agent>()
        for (i in dataName.indices) {
            val agent = Agent(
                dataName[i],
                dataRole[i],
                dataDesc[i],
                dataImage.getResourceId(i, -1),
                dataDetail[i]
            )
            listAgent.add(agent)
        }
        dataImage.recycle()
        return listAgent
    }
}