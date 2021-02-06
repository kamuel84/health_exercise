package com.exercise.health_exercise.data.playExerciseItem

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "play_exercise_item")
data class PlayExerciseItemData (
    @PrimaryKey(autoGenerate = true)
    val idx : Long,
    val play_exercise_index : Long,
    val health_list_index : Long,
    val health_item_index : Long,
    val play_time : Long,
    val isComplete:Boolean
):Serializable {
}