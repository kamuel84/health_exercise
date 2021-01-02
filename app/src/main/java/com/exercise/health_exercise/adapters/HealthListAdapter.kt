package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.healthList.HolderHealthListItem
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.utils.ArrayUtils


class HealthListAdapter(var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var healthList : List<HealthListData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.holder_healthlist, parent, false)
        var holder : RecyclerView.ViewHolder = HolderHealthListItem(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HolderHealthListItem).setHealthListItem(healthList!!.get(position))
    }

    override fun getItemCount(): Int {
        if(ArrayUtils().hasValue(healthList))
            return healthList!!.size
        else
            return 0
    }

    fun updateList(list: List<HealthListData>){
        healthList = list
        notifyDataSetChanged()
    }
}