package com.exercise.health_exercise.adapters.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_addlist.view.*

class HolderListAdd(itemView:View, var listener:HolderListAdd.onAddHolderListener): RecyclerView.ViewHolder(itemView) {
    interface onAddHolderListener{
        fun onAddClick()
    }

    fun setAdd(){
        with(itemView){
            clListAdd_Root.setOnClickListener {
                listener.onAddClick()
            }
        }
    }

}