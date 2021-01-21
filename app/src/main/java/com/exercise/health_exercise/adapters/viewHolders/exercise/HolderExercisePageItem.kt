package com.exercise.health_exercise.adapters.viewHolders.exercise

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.utils.ViewUtils
import kotlinx.android.synthetic.main.holder_exercise_item_detail.view.*

class HolderExercisePageItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setPageItem(data:HealthList_ItemJoinData, position:Int){
        with(itemView){
            ViewUtils.loadGifImage(data.health_image_url, null).into(ivItemDetail_Image)
            tvItemDetail_Notice.text = data.health_description
        }

    }
}