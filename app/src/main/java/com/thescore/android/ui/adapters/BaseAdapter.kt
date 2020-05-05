package com.thescore.android.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thescore.android.model.BaseModel
import com.thescore.android.util.DiffUtilCallback

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

abstract class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var itemClickListener: ItemClickListener
    fun setListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
    interface ItemClickListener {
        fun onItemClicked(baseModel: BaseModel)
    }

    var modelList: MutableList<BaseModel> = mutableListOf()

    fun setNewData(newList: MutableList<BaseModel>) {
        val diffCallback = DiffUtilCallback(modelList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        modelList.clear()
        modelList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}