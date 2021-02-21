package com.exercise.health_exercise.data.exercises

import androidx.lifecycle.LiveData
import androidx.room.*
import com.exercise.health_exercise.data.BaseDao

/** DAO (Data Access Object) **/
@Dao
interface ExercisesDao : BaseDao<ExercisesData> {

    @Query("SELECT * FROM exercise")
    fun getAll() : LiveData<List<ExercisesData>>

    // LEFT JOIN health_list_items ON health_list_items.health_index = exercise.idx AND"
    @Query("SELECT exercise.idx, exercise.title, exercise.revert_count, exercise.play_time, exercise.health_notice, exercise.health_photo, " +
            "CASE WHEN health_list_items.idx not null THEN 1 else 0 end as \'check\' from exercise " +
            "LEFT JOIN health_list_items ON health_index = exercise.idx AND health_list_index = :index")
    fun getEditMode(index:Long) : LiveData<List<ExercisesData>>

//    @Insert
//    fun insertExercisesData(exercisesData : ExercisesData){
//
//    }
//    @Query("SELECT * FROM exercise WHERE idx = :index")
//    fun getIndexData(index : Int) : LiveData<ArrayList<ExercisesData>>
}