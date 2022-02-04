package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.HolderListAdd
import com.exercise.health_exercise.adapters.viewHolders.healthList.HolderHealthListItem
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.utils.ArrayUtils


class HealthListAdapter(
    var context: Context,
    var listener: HealthListAdapter.onHealthListListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HolderListAdd.onAddHolderListener,
    HolderHealthListItem.onHolderHealthListListener {

    var healthList: List<HealthListWithItemData>? = null

    val VIEWTYPE_ITME: Int = 0
    val VIEWTYPE_ADD: Int = 1

    interface onHealthListListener {
        fun onAdd()
        fun onSelectItem(data: HealthListWithItemData, position: Int)
        fun onMore(data: HealthListWithItemData, position: Int)
        fun onChecked(data: HealthListWithItemData, position: Int, isCheck: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        if (viewType == VIEWTYPE_ITME) {
            val itemView: View = inflater.inflate(R.layout.holder_healthlist, parent, false)
            var holder: RecyclerView.ViewHolder = HolderHealthListItem(context, itemView, this)
            return holder
        } else {
            val itemView: View = inflater.inflate(R.layout.holder_addlist, parent, false)
            var holder: RecyclerView.ViewHolder = HolderListAdd(itemView, this)
            return holder
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            /** ADD View **/
            (holder as HolderListAdd).setAdd()
        } else
            (holder as HolderHealthListItem).setHealthListItem(
                healthList!!.get(position - 1),
                position - 1,
                "home_list"
            )
    }

    override fun getItemCount(): Int {
        if (ArrayUtils().hasValue(healthList))
            return healthList!!.size + 1
        else
            return 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return VIEWTYPE_ADD
        else
            return VIEWTYPE_ITME
    }

    fun updateList(list: List<HealthListWithItemData>) {
        healthList = list
        notifyDataSetChanged()
    }

    override fun onAddClick() {
        listener.onAdd()
    }

    override fun onSelectItem(data: HealthListWithItemData, position: Int) {
        listener.onSelectItem(data, position)
    }

    override fun onChecked(data: HealthListWithItemData, position: Int, isCheck: Boolean) {
        if (healthList != null)
            healthList!!.get(position).isChecked = isCheck

        listener.onChecked(data, position, isCheck)
    }

    override fun onMore(data: HealthListWithItemData, position: Int) {
        listener.onMore(data, position)
    }
}