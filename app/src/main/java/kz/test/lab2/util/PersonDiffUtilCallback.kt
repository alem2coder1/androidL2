package kz.test.lab2.util

import androidx.recyclerview.widget.DiffUtil
import kz.test.lab2.model.entity.Person


class PersonDiffUtilCallback(
    private val oldItemList: List<Person>,
    private val newItemList: List<Person>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItemList.size
    }

    override fun getNewListSize(): Int {
        return newItemList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList[oldItemPosition].name == newItemList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList[oldItemPosition] == newItemList[newItemPosition]
    }
}