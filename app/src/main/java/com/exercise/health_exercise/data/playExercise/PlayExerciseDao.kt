package com.exercise.health_exercise.data.playExercise

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.exercise.health_exercise.data.BaseDao

/** DAO (Data Access Object) **/
@Dao
interface PlayExerciseDao : BaseDao<PlayExerciseData> {

    @Query("SELECT * FROM play_exercise")
    fun getAll() : LiveData<List<PlayExerciseData>>

}