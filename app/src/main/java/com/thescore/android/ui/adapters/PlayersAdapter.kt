package com.thescore.android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thescore.android.databinding.ItemPlayerBinding
import com.thescore.android.model.Player

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class PlayersAdapter : BaseAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ItemViewHolder(
            ItemPlayerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount() = modelList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemBinding = (holder as ItemViewHolder).binding

        val playerModel = modelList[position] as Player
        playerModel.let {
            itemBinding.player = playerModel
        }
    }

    fun updateData(newPlayerList: MutableList<Player>) {
        setNewData(newList = newPlayerList.toMutableList())
    }

    class ItemViewHolder(val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root)
}