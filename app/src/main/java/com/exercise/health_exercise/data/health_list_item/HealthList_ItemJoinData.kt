package com.exercise.health_exercise.data.health_list_item

import java.io.Serializable

data class HealthList_ItemJoinData(
    val item_idx : Long,
    val custom_count:Int,
    val custom_play_time:Long,
    // list item
    val health_list_index:Long,
    val health_list_title:String,
    // exercise item
    val health_index:Long,
    val health_title:String,
    val health_description:String,
    val health_image_url:String
):Serializable {
}