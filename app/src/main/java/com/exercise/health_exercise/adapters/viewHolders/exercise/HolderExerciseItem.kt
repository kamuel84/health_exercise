package com.exercise.health_exercise.adapters.viewHolders.exercise

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.exercises.ExercisesData
import kotlinx.android.synthetic.main.holder_exerciselist.view.*

class HolderExerciseItem(itemView:View):RecyclerView.ViewHolder(itemView) {
    interface onExerciseItemListener{
        fun onCheck(data:ExercisesData, position:Int)
    }
    fun setExercise(data:ExercisesData, position:Int){
        with(itemView){
            tvExerciseItem_Title.text = data.title
            clExerciseItem_Root.setTag(R.id.list_data, data)
            clExerciseItem_Root.setTag(R.id.list_position, position)

            clExerciseItem_Root.setOnClickListener {
                var exerciseItem : ExercisesData = it.getTag(R.id.list_data) as ExercisesData
                var pos : Int = it.getTag(R.id.list_position).toString().toInt()
            }

            if(data.check){
                clExerciseItem_Root.setBackgroundColor(ContextCompat.getColor(context, R.color.color_99ccff))
            } else {
                clExerciseItem_Root.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            }

        }
    }
}