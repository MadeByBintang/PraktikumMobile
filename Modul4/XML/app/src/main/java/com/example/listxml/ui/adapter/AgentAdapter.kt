package com.example.listxml.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listxml.data.model.Agent
import com.example.listxml.databinding.ItemAgentBinding

interface OnAgentClickListener {
    fun onDetailClicked(agent: Agent)
    fun onLinkClicked(agent: Agent)
}

class AgentAdapter(
    private val agentList: List<Agent>,
    private val listener: OnAgentClickListener
) : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    inner class AgentViewHolder(val binding: ItemAgentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val binding = ItemAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agent = agentList[position]
        holder.binding.apply {
            agentName.text = agent.name
            agentRole.text = agent.role
            agentDesc.text = agent.desc
            agentImage.setImageResource(agent.image)
            btnDetail.setOnClickListener { listener.onDetailClicked(agent) }
            btnLink.setOnClickListener { listener.onLinkClicked(agent) }
        }
    }

    override fun getItemCount(): Int = agentList.size
}