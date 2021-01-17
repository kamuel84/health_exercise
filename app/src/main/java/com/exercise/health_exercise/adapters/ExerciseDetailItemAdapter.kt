package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.exercise.HolderCustomExerciseItem
import com.exercise.health_exercise.adapters.viewHolders.exercise.HolderExercisePageItem
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.utils.ArrayUtils

class ExerciseDetailItemAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var list:List<HealthList_ItemJoinData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.holder_exercise_item_detail, parent, false)
        var holder : RecyclerView.ViewHolder = HolderExercisePageItem(itemView)
        return holder
    }

    override fun getItemCount(): Int {
        if(ArrayUtils().hasValue(list))
            return list!!.size
        else
            return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HolderExercisePageItem).setPageItem(list!!.get(position), position)
    }

    fun updateList(data:List<HealthList_ItemJoinData>){
        list = data

        notifyDataSetChanged()
    }
}