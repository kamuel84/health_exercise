package com.exercise.health_exercise.adapters.viewHolders.selectExerciseList

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.utils.ViewUtils
import kotlinx.android.synthetic.main.holder_exerciselist.view.*
import kotlinx.android.synthetic.main.holder_exerciselist.view.clExerciseItem_Root
import kotlinx.android.synthetic.main.holder_select_exerciselist.view.*

class HolderSelectExerciseItem(var context:Context, itemView:View, var listener:HolderSelectExerciseItem.onSelectExerciseItemListener):RecyclerView.ViewHolder(itemView) {
    interface onSelectExerciseItemListener{
        fun onCountUp(data:ExercisesData, position: Int)
        fun onCountDown(data:ExercisesData, position:Int)
        fun onIntervalUp(data:ExercisesData, position:Int)
        fun onIntervalDown(data:ExercisesData, position:Int)
        fun onSortUp(data:ExercisesData, position:Int)
        fun onSortDown(data:ExercisesData, position:Int)
    }
    fun setSelectExercise(data:ExercisesData, position:Int, max:Int){
        with(itemView){
            tvSelectItem_ExerciseTitle.text = data.title

            ivSelectItem_CountLeft.setTag(R.id.list_data, data)
            ivSelectItem_CountLeft.setTag(R.id.list_position, position)

            ivSelectItem_CountRight.setTag(R.id.list_data, data)
            ivSelectItem_CountRight.setTag(R.id.list_position, position)

            ivSelectItem_IntervalLeft.setTag(R.id.list_data, data)
            ivSelectItem_IntervalLeft.setTag(R.id.list_position, position)

            ivSelectItem_IntervalRight.setTag(R.id.list_data, data)
            ivSelectItem_IntervalRight.setTag(R.id.list_position, position)

            ivSelectItem_SortUp.setTag(R.id.list_data, data)
            ivSelectItem_SortUp.setTag(R.id.list_position, position)

            ivSelectItem_SortDown.setTag(R.id.list_data, data)
            ivSelectItem_SortDown.setTag(R.id.list_position, position)

            ivSelectItem_CountLeft.setOnClickListener {
                var data:ExercisesData = it.getTag(R.id.list_data) as ExercisesData
                var pos:Int = it.getTag(R.id.list_position).toString().toInt()

                listener.onCountDown(data, pos)
            }

            ivSelectItem_CountRight.setOnClickListener {
                var data:ExercisesData = it.getTag(R.id.list_data) as ExercisesData
                var pos:Int = it.getTag(R.id.list_position).toString().toInt()

                listener.onCountUp(data, pos)
            }

            ivSelectItem_IntervalLeft.setOnClickListener {
                var data:ExercisesData = it.getTag(R.id.list_data) as ExercisesData
                var pos:Int = it.getTag(R.id.list_position).toString().toInt()

                listener.onIntervalDown(data, pos)
            }

            ivSelectItem_IntervalRight.setOnClickListener {
                var data:ExercisesData = it.getTag(R.id.list_data) as ExercisesData
                var pos:Int = it.getTag(R.id.list_position).toString().toInt()

                listener.onIntervalUp(data, pos)
            }

            ivSelectItem_SortUp.setOnClickListener {
                var data:ExercisesData = it.getTag(R.id.list_data) as ExercisesData
                var pos:Int = it.getTag(R.id.list_position).toString().toInt()

                if(pos == 0)
                    Toast.makeText(context, "더 이상 올릴 수 없습니다.", Toast.LENGTH_SHORT).show()
                else
                    listener.onSortUp(data, pos)
            }

            ivSelectItem_SortDown.setOnClickListener {
                var data:ExercisesData = it.getTag(R.id.list_data) as ExercisesData
                var pos:Int = it.getTag(R.id.list_position).toString().toInt()

                if(pos >= max-1)
                    Toast.makeText(context, "더 이상 내릴 수 없습니다.", Toast.LENGTH_SHORT).show()
                else
                    listener.onSortDown(data, pos)
            }

            tvSelectItem_Count.text = data.revert_count.toString()
            tvSelectItem_Interval.text = (data.play_Time/1000).toString()

            ViewUtils.loadGifImage(data.health_Photo, null).into(ivSelectItem_Image)

        }
    }
}