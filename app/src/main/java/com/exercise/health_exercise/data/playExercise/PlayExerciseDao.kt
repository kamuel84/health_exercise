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

    @Query("SELECT * FROM play_exercise WHERE strDate=:date")
    fun getAll(date:String):LiveData<List<PlayExerciseData>>?

    @Query("SELECT * FROM play_exercise WHERE substr(strDate, 0,7)=:month GROUP BY strDate")
    fun getGroupAll(month:String) : LiveData<List<PlayExerciseData>>

    @Query("SELECT * FROM play_exercise WHERE strDate LIKE :date")
    fun getCount(date:String): LiveData<List<PlayExerciseData>>

}