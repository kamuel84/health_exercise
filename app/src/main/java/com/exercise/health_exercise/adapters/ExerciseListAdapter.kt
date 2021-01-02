package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.exercise.HolderExerciseItem
import com.exercise.health_exercise.adapters.viewHolders.healthList.HolderHealthListItem
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.utils.ArrayUtils

class ExerciseListAdapter(var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var exerciseList : List<ExercisesData> ?= null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.holder_exerciselist, parent, false)
        var holder : RecyclerView.ViewHolder = HolderExerciseItem(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HolderExerciseItem).setExercise(exerciseList!!.get(position))
    }

    override fun getItemCount(): Int {
        if(ArrayUtils().hasValue(exerciseList))
            return exerciseList!!.size
        else
            return 0
    }

    fun updateList(list: List<ExercisesData>){
        exerciseList = list
        notifyDataSetChanged()
    }
}