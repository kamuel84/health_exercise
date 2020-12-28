package com.exercise.health_exercise.data.exercises

import androidx.lifecycle.LiveData
import androidx.room.*
import com.exercise.health_exercise.data.BaseDao

/** DAO (Data Access Object) **/
@Dao
interface ExercisesDao : BaseDao<ExercisesData> {

    @Query("SELECT * FROM exercise")
    fun getAll() : LiveData<List<ExercisesData>>

    @Insert
    fun insertExercisesData(exercisesData : ExercisesData){

    }
//    @Query("SELECT * FROM exercise WHERE idx = :index")
//    fun getIndexData(index : Int) : LiveData<ArrayList<ExercisesData>>
}