package com.exercise.health_exercise.adapters.viewHolders.healthList

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.R
import kotlinx.android.synthetic.main.holder_healthlist.view.*

class HolderHealthListItem(var context:Context, itemView: View, var listener:HolderHealthListItem.onHolderHealthListListener) : RecyclerView.ViewHolder(itemView) {

    interface onHolderHealthListListener{
        fun onSelectItem(data:HealthListData, position:Int)
    }
    fun setHealthListItem(healthData:HealthListData, position:Int){
        with(itemView){
            tvList_Title.text = healthData.title

            clList_Root.setTag(R.id.list_data, healthData)
            clList_Root.setTag(R.id.list_position, position)
            clList_Root.setOnClickListener {
                var listData : HealthListData = it.getTag(R.id.list_data) as HealthListData
                var pos : Int = it.getTag(R.id.list_position).toString().toInt()
                listener.onSelectItem(listData, pos)
            }
            if(healthData.list_type == "C")
                ivList_Background.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_99ccff)
            else
                ivList_Background.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_bb86fc)
        }
    }
}