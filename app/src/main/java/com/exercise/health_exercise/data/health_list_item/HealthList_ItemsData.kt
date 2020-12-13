package com.exercise.health_exercise.data.health_list_item

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListData

@Entity(
    tableName = "health_list_items",
    foreignKeys = [
        ForeignKey(entity = HealthListData::class, parentColumns = ["idx"], childColumns = ["health_list_index"]),
        ForeignKey(entity = ExercisesData::class, parentColumns = ["idx"], childColumns = ["health_index"])
    ]
)
data class HealthList_ItemsData(
    @PrimaryKey(autoGenerate = true)
    var idx : Long,
    var health_list_index:Long,
    var health_index:Long,
    var revert_count:Int,
    var play_time:Long
) {
}