package com.exercise.health_exercise.adapters.viewHolders.healthList

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.R
import kotlinx.android.synthetic.main.holder_healthlist.view.*

class HolderHealthListItem(var context:Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setHealthListItem(healthData:HealthListData){
        with(itemView){
            tvList_Title.text = healthData.title

            if(healthData.list_type == "C")
                ivList_Background.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_99ccff)
            else
                ivList_Background.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_bb86fc)
        }
    }
}