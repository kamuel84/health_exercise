package com.exercise.health_exercise.adapters.viewHolders.exercise

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.data.exercises.ExercisesData
import kotlinx.android.synthetic.main.holder_exerciselist.view.*

class HolderExerciseItem(itemView:View):RecyclerView.ViewHolder(itemView) {
    fun setExercise(data:ExercisesData){
        with(itemView){
            tvExerciseItem_Title.text = data.title

        }
    }
}