package com.exercise.health_exercise.data.exercises

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class ExercisesData(
    @PrimaryKey(autoGenerate = true)
    val idx : Long,
    val title : String,
    val revert_count : Int,
    val play_Time : Long,
    val health_Notice : String,
    val health_Photo : String,
    var check:Boolean = false) {

}