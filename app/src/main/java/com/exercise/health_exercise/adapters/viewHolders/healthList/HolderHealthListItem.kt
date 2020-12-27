package com.exercise.health_exercise.adapters.viewHolders.healthList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.R
import kotlinx.android.synthetic.main.holder_healthlist.view.*

class HolderHealthListItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setHealthListItem(healthData:HealthListData){
        with(itemView){
            tvList_Title.text = healthData.title
        }
    }
}