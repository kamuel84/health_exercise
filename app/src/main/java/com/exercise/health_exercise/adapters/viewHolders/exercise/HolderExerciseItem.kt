package com.exercise.health_exercise.adapters.viewHolders.exercise

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.utils.ViewUtils
import kotlinx.android.synthetic.main.holder_exerciselist.view.*

class HolderExerciseItem(itemView:View, var listener:HolderExerciseItem.onExerciseItemListener):RecyclerView.ViewHolder(itemView) {
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

                listener.onCheck(exerciseItem, pos)
            }

            cbExerciseItem_Check.visibility = View.VISIBLE

            if(data.check){
                cbExerciseItem_Check.isChecked = true
            } else {
                cbExerciseItem_Check.isChecked = false
            }

            if(data.checkIndex != -1) {
                tvExerciseItem_Index.visibility = View.VISIBLE
                tvExerciseItem_Index.text = data.checkIndex.toString()
            } else {
                tvExerciseItem_Index.visibility = View.GONE
            }

            ViewUtils.loadGifImage(data.health_Photo, null).into(ivExerciseItem_Image)

        }
    }
}