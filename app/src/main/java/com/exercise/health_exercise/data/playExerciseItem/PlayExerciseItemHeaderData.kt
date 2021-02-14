package com.exercise.health_exercise.data.playExerciseItem

import java.io.Serializable

data class PlayExerciseItemHeaderData(
        val title:String,
        val playTotalCount : Int,
        val completeCount : Int,
        val playTime:Long
):Serializable {
}