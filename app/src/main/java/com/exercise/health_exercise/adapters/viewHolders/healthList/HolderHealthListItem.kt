package com.exercise.health_exercise.adapters.viewHolders.healthList

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.utils.ViewUtils
import kotlinx.android.synthetic.main.holder_healthlist.view.*

class HolderHealthListItem(var context:Context, itemView: View, var listener:HolderHealthListItem.onHolderHealthListListener) : RecyclerView.ViewHolder(itemView) {

    interface onHolderHealthListListener{
        fun onSelectItem(data:HealthListData, position:Int)
    }
    fun setHealthListItem(healthData: HealthListWithItemData, position:Int){
        with(itemView){
            tvList_Title.text = healthData.title

            clList_Root.setTag(R.id.list_data, healthData)
            clList_Root.setTag(R.id.list_position, position)
            clList_Root.setOnClickListener {
                var listData : HealthListData = it.getTag(R.id.list_data) as HealthListData
                var pos : Int = it.getTag(R.id.list_position).toString().toInt()
                listener.onSelectItem(listData, pos)
            }

            var path:String = ""

            if(!TextUtils.isEmpty(healthData.health_Photo))
                ViewUtils.loadGifImage(healthData.health_Photo!!, null).into(ivList_Exercise)

            if(healthData.list_type == "C")
                clList_Root.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_99ccff)
            else
                clList_Root.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_line_e5e5e5)
        }
    }
}