package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.exercise.HolderCustomExerciseItem
import com.exercise.health_exercise.adapters.viewHolders.exercise.HolderExerciseItem
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.utils.ArrayUtils

class ExerciseDetailAdapter(var context: Context, var listener : ExerciseDetailAdapter.onExerciseDetailListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HolderCustomExerciseItem.onCustomExerciseListener{

    var list : List<HealthList_ItemJoinData> ?= null

    interface onExerciseDetailListener{
        fun onItemSelect(data:HealthList_ItemJoinData, position:Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.holder_exerciselist, parent, false)
        var holder : RecyclerView.ViewHolder = HolderCustomExerciseItem(itemView, this)
        return holder
    }

    override fun getItemCount(): Int {
        if(ArrayUtils().hasValue(list))
            return list!!.size
        else
            return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HolderCustomExerciseItem).setCustomExercise(list!!.get(position), position)
    }

    override fun onItemSelect(data: HealthList_ItemJoinData, position: Int) {
        listener.onItemSelect(data, position)
    }

    fun updateList(list:List<HealthList_ItemJoinData>){
        this.list = list
        notifyDataSetChanged()
    }
}