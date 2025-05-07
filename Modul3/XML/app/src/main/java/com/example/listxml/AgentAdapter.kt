package com.example.listxml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listxml.databinding.ItemAgentBinding

interface OnAgentClickListener {
    fun onDetailClicked(agent: Agent)
    fun onLinkClicked(linkUrl: String)
}

class AgentAdapter(
    private val agentList: ArrayList<Agent>,
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
            btnLink.setOnClickListener {
                listener.onLinkClicked(agent.detail)
            }
            btnDetail.setOnClickListener {
                listener.onDetailClicked(agent)
            }
        }
    }

    override fun getItemCount(): Int {
        return agentList.size
    }
}