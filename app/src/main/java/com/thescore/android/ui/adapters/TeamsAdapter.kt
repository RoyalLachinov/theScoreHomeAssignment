package com.thescore.android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thescore.android.databinding.ItemTeamBinding
import com.thescore.android.model.Team

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class TeamsAdapter : BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ItemTeamBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = modelList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemTeamBinding = (holder as ItemViewHolder).binding
        val teamModel = modelList[position] as Team

        teamModel.let {
            itemTeamBinding.txtCount.text = (position + 1).toString().plus(".")
            itemTeamBinding.team = teamModel

            itemTeamBinding.layoutItemTeam.setOnClickListener {
                itemClickListener.onItemClicked(teamModel)
            }
        }
    }

    fun updateData(newTeamsList: MutableList<Team>) {
        setNewData(newList = newTeamsList.toMutableList())
    }

    class ItemViewHolder(val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root)

}