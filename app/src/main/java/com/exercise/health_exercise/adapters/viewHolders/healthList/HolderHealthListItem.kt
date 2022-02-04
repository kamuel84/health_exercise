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
        fun onSelectItem(data:HealthListWithItemData, position:Int)
        fun onChecked(data:HealthListWithItemData, position: Int, isCheck:Boolean)
        fun onMore(data:HealthListWithItemData, position:Int)
    }
    fun setHealthListItem(healthData: HealthListWithItemData, position:Int, viewType:String){
        with(itemView){
            tvList_Title.text = healthData.title

            clList_Root.setTag(R.id.list_data, healthData)
            clList_Root.setTag(R.id.list_position, position)

            ivListMenu.setTag(R.id.list_data, healthData)
            ivListMenu.setTag(R.id.list_position, position)

            chkList.setTag(R.id.list_data, healthData)
            chkList.setTag(R.id.list_position, position)

            clList_Root.setOnClickListener {
                var listData : HealthListWithItemData = it.getTag(R.id.list_data) as HealthListWithItemData
                var pos : Int = it.getTag(R.id.list_position).toString().toInt()
                listener.onSelectItem(listData, pos)
            }

            var path:String = ""

            if(!TextUtils.isEmpty(healthData.health_Photo))
                ViewUtils.loadGifImage(healthData.health_Photo!!, null).into(ivList_Exercise)

            tvList_subtitle.text = String.format("%s%s", healthData.item_count.toString(), " exercises")

            if(healthData.list_type == "C") {
                clList_Root.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_99ccff)
                ivListMenu.visibility = View.VISIBLE
                chkList.visibility = View.GONE
                ivListMenu.setOnClickListener {
                    var listData: HealthListWithItemData = it.getTag(R.id.list_data) as HealthListWithItemData
                    var pos: Int = it.getTag(R.id.list_position).toString().toInt()
                    listener.onMore(listData, pos)
                }
            } else {
                clList_Root.background = ContextCompat.getDrawable(context, R.drawable.bg_radius3_e5e5e5)
                ivListMenu.visibility = View.INVISIBLE
                if(healthData.list_type == "CD")
                    chkList.visibility = View.GONE
                else
                    chkList.visibility = View.VISIBLE
                ivListMenu.setOnClickListener {}
                chkList.isChecked = healthData.isChecked

                chkList.setOnCheckedChangeListener { buttonView, isChecked ->
                    var listData: HealthListWithItemData = buttonView.getTag(R.id.list_data) as HealthListWithItemData
                    var pos: Int = buttonView.getTag(R.id.list_position).toString().toInt()
                    listener.onChecked(listData, pos, isChecked)
                }
            }

            if(viewType == "group_list"){
                chkList.visibility = View.GONE
            }
        }
    }
}