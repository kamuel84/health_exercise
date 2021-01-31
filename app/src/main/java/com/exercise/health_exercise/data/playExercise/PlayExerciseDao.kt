package com.exercise.health_exercise.data.playExercise

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.exercise.health_exercise.data.BaseDao
import com.exercise.health_exercise.data.exercises.ExercisesData

/** DAO (Data Access Object) **/
@Dao
interface PlayExerciseDao : BaseDao<ExercisesData> {

    @Query("SELECT * FROM play_exercise")
    fun getAll() : LiveData<List<PlayExerciseData>>
}