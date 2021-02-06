package com.exercise.health_exercise.data.playExerciseItem

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.exercise.health_exercise.data.BaseDao

@Dao
interface PlayExerciseItemDao : BaseDao<PlayExerciseItemData> {
    @Query("SELECT * FROM play_exercise_item")
    fun getAll() : LiveData<List<PlayExerciseItemData>>
}