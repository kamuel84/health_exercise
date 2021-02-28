package com.exercise.health_exercise.adapters

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.viewHolders.completeList.HolderCompleteHeader
import com.exercise.health_exercise.adapters.viewHolders.completeList.HolderCompleteItem
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.data.playExercise.PlayExerciseListData
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemHeaderData

class CompleteListAdapter(var context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list:ArrayList<PlayExerciseListData> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        var itemView : View ?= null
        var holder : RecyclerView.ViewHolder ?= null
        if(viewType == AppContents.VIEWTYPE_DATEHADER) {
            itemView = inflater.inflate(R.layout.holder_complete_header, parent, false)
            holder = HolderCompleteHeader(itemView)
        } else if(viewType == AppContents.VIEWTYPE_PLAYITEM){
            itemView = inflater.inflate(R.layout.holder_complete_item, parent, false)
            holder = HolderCompleteItem(itemView)
        }
        return holder!!
    }

    override fun getItemCount(): Int {
        if(list != null && list!!.size > 0)
            return list!!.size
        else
            return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewType:Int = list!!.get(position).view_type

        if(viewType == AppContents.VIEWTYPE_DATEHADER){
            var headerDate : String = list!!.get(position).view_item.toString()
            if(!TextUtils.isEmpty(headerDate)) {
                var strDate: String = headerDate.substring(0, 4) + "-" + headerDate.substring(4, 6) + "-" + headerDate.substring(6, 8)
                (holder as HolderCompleteHeader).setHeaderData(strDate)
            }
        } else if(viewType == AppContents.VIEWTYPE_PLAYITEM){
            var itemData:PlayExerciseItemHeaderData = list!!.get(position).view_item as PlayExerciseItemHeaderData
            (holder as HolderCompleteItem).setItem(itemData)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var viewType:Int = list!!.get(position).view_type
        return viewType
    }

    fun update(list:ArrayList<PlayExerciseListData>?){
        this.list = list
        notifyDataSetChanged()
    }
}