package com.example.listxml.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.listxml.databinding.FragmentDetailBinding
import com.example.listxml.viewmodel.AgentViewModel
import com.example.listxml.viewmodel.AgentViewModelFactory
import timber.log.Timber

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AgentViewModel by activityViewModels {
        AgentViewModelFactory("Agent Detail")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.selectedAgent.value?.let { agent ->
            Timber.i("Navigating to detail: $agent")
            binding.detailName.text = agent.name
            binding.detailRole.text = agent.role
            binding.detailDesc.text = agent.desc
            binding.detailImage.setImageResource(agent.image)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}