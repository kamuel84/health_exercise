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

class ExerciseDetailAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HolderCustomExerciseItem.onCustomExerciseListener{

    interface onExerciseDetailListener{
        fun onItemSelect(data:HealthList_ItemJoinData)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.holder_exerciselist, parent, false)
        var holder : RecyclerView.ViewHolder = HolderCustomExerciseItem(itemView, this)
        return holder
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemSelect(data: HealthList_ItemJoinData, position: Int) {
        TODO("Not yet implemented")
    }
}