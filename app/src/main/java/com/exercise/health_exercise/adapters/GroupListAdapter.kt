package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.HolderListAdd
import com.exercise.health_exercise.adapters.viewHolders.healthList.HolderHealthListItem
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.utils.ArrayUtils

class GroupListAdapter(var context: Context, var listener:GroupListAdapter.onGroupListListener): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        HolderListAdd.onAddHolderListener,
        HolderHealthListItem.onHolderHealthListListener {

    var healthList : List<HealthListWithItemData> ?= null

    val VIEWTYPE_ITME:Int = 0
    val VIEWTYPE_ADD:Int = 1

    interface onGroupListListener{
        fun onSelectItem(data : HealthListWithItemData, position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        val itemView: View = inflater.inflate(R.layout.holder_healthlist, parent, false)
        var holder: RecyclerView.ViewHolder = HolderHealthListItem(context, itemView, this)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HolderHealthListItem).setHealthListItem(healthList!!.get(position), position, "group_list")
    }

    override fun getItemCount(): Int {
        if(ArrayUtils().hasValue(healthList))
            return healthList!!.size
        else
            return 0
    }

    override fun getItemViewType(position: Int): Int {
        return VIEWTYPE_ITME
    }

    fun updateList(list: List<HealthListWithItemData>){
        healthList = list
        notifyDataSetChanged()
    }

    override fun onSelectItem(data: HealthListWithItemData, position: Int) {
        listener.onSelectItem(data, position)
    }

    override fun onChecked(data: HealthListWithItemData, position: Int, isCheck:Boolean) {
        /** 사용안함 **/
        /** ViewHolder를 같이 사용하기 때문에 리스너를 붙어야 함 **/
    }

    override fun onMore(data: HealthListWithItemData, position: Int) {
        /** 사용안함 **/
        /** ViewHolder를 같이 사용하기 때문에 리스너를 붙어야 함 **/
    }

    override fun onAddClick() {
        /** 사용안함 **/
        /** ViewHolder를 같이 사용하기 때문에 리스너를 붙어야 함 **/
    }

}