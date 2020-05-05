package com.thescore.android.util

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.thescore.android.model.BaseModel

/**
 * Created by Royal Lachinov on 2020-05-02.
 */

class DiffUtilCallback (private val oldList: MutableList<BaseModel>, private val newList: MutableList<BaseModel>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].updatedAt == newList[newItemPosition].updatedAt
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]

    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}