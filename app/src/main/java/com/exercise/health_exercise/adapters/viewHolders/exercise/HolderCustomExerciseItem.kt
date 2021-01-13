package com.exercise.health_exercise.adapters.viewHolders.exercise

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.utils.ViewUtils
import kotlinx.android.synthetic.main.holder_exerciselist.view.*

class HolderCustomExerciseItem(itemView: View, var listener:HolderCustomExerciseItem.onCustomExerciseListener) : RecyclerView.ViewHolder(itemView) {

    interface onCustomExerciseListener{
        fun onItemSelect(data:HealthList_ItemJoinData, position:Int)
    }

    fun setCustomExercise(itemData:HealthList_ItemJoinData, position:Int){
        with(itemView){
            tvExerciseItem_Title.text = itemData.health_title
            clExerciseItem_Root.setTag(R.id.list_data, itemData)
            clExerciseItem_Root.setTag(R.id.list_position, position)

            clExerciseItem_Root.setOnClickListener {
                var exerciseItem : HealthList_ItemJoinData = it.getTag(R.id.list_data) as HealthList_ItemJoinData
                var pos : Int = it.getTag(R.id.list_position).toString().toInt()

                listener.onItemSelect(exerciseItem, pos)
            }

            ViewUtils.loadGifImage(itemData.health_image_url, null).into(ivExerciseItem_Image)

        }
    }
}