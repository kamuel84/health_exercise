package com.exercise.health_exercise.data.playExercise

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "play_exercise")
data class PlayExerciseData(
    @PrimaryKey(autoGenerate = true)
    val idx : Long,
    val strDate:String,
    val healthListItemIdx:Long
) {
}