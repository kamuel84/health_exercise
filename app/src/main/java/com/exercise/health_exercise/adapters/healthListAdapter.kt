package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.utils.ArrayUtils


class healthListAdapter(context:Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var healthList : List<HealthListData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        if(ArrayUtils().hasValue(healthList))
            return healthList!!.size
        else
            return 0
    }

    fun updateList(list:List<HealthListData>){
        healthList = list
        notifyDataSetChanged()
    }
}