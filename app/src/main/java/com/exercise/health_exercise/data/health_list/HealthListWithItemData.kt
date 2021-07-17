package com.exercise.health_exercise.data.health_list

import java.io.Serializable

data class HealthListWithItemData(
        var idx : Long,
        var title : String,
        var list_type : String,
        var health_Photo :String?,
        var item_count:Int,
        var isChecked:Boolean
):Serializable {
}