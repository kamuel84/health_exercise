package com.exercise.health_exercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.exercise.HolderExerciseItem
import com.exercise.health_exercise.adapters.viewHolders.selectExerciseList.HolderSelectExerciseItem
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.utils.ArrayUtils

class SelectExerciseListAdapter(var context: Context, var listener:SelectExerciseListAdapter.onSelectExerciseListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), HolderSelectExerciseItem.onSelectExerciseItemListener {
    var itemList : List<ExercisesData> ? = null

    interface onSelectExerciseListener{
        fun onCountUp(data:ExercisesData, position: Int)
        fun onCountDown(data:ExercisesData, position:Int)
        fun onIntervalUp(data:ExercisesData, position:Int)
        fun onIntervalDown(data:ExercisesData, position:Int)
        fun onSortUp(data:ExercisesData, position:Int)
        fun onSortDown(data:ExercisesData, position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.holder_select_exerciselist, parent, false)
        var holder : RecyclerView.ViewHolder = HolderSelectExerciseItem(context, itemView, this)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HolderSelectExerciseItem).setSelectExercise(itemList!!.get(position), position, itemList!!.size)
    }

    override fun getItemCount(): Int {
        if(ArrayUtils().hasValue(itemList))
            return itemList!!.size
        else
            return 0
    }

    fun updateList(list: List<ExercisesData>){
        itemList = list
        notifyDataSetChanged()
    }

    fun updateSortList(position:Int) : List<ExercisesData>{
        notifyItemMoved(position, position-1)
        return itemList!!
    }

    override fun onCountUp(data: ExercisesData, position: Int) {
        listener.onCountUp(data, position)
    }

    override fun onCountDown(data: ExercisesData, position: Int) {
        listener.onCountDown(data, position)
    }

    override fun onIntervalUp(data: ExercisesData, position: Int) {
        listener.onIntervalUp(data, position)
    }

    override fun onIntervalDown(data: ExercisesData, position: Int) {
        listener.onIntervalDown(data, position)
    }

    override fun onSortUp(data: ExercisesData, position: Int) {
        listener.onSortUp(data,position)
    }

    override fun onSortDown(data: ExercisesData, position: Int) {
        listener.onSortDown(data,position)
    }
}