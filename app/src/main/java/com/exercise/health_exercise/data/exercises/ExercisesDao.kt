package com.exercise.health_exercise.data.exercises

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.exercise.health_exercise.data.BaseDao

/** DAO (Data Access Object) **/
@Dao
interface ExercisesDao : BaseDao<ExercisesData> {

    @Query("SELECT * FROM exercise")
    fun getAll() : LiveData<List<ExercisesData>>

    @Query("SELECT * FROM exercise WHERE title LIKE :keyword")
    fun getSearchExercise(keyword:String):LiveData<List<ExercisesData>>

    @Query("SELECT * FROM exercise WHERE idx in (SELECT health_index from health_list_items WHERE health_list_index = :index )")
    fun getGroupSelectExercise(index:String):List<ExercisesData>

    // LEFT JOIN health_list_items ON health_list_items.health_index = exercise.idx AND"
//    @Query("SELECT exercise.idx, exercise.title, health_list_items.revert_count, health_list_items.play_time AS \'play_Time\', " +
//            "exercise.health_Notice, exercise.health_Photo, health_list_items.health_sort AS \'checkIndex\', 1 AS \'check\' " +
//            "FROM health_list_items " +
//            "LEFT JOIN exercise ON health_list_items.health_index = exercise.idx WHERE health_list_items.health_list_index = :index")
    @Query("SELECT health_list_items.health_index AS 'idx', exercise.title, health_list_items.revert_count, health_list_items.play_time AS 'play_Time',  exercise.health_Notice, exercise.health_Photo, health_list_items.health_sort AS 'checkIndex', 1 AS 'check' FROM exercise INNER JOIN health_list_items ON health_index = exercise.idx WHERE health_list_index = :idx")
    fun getEditMode(idx:Long) :List<ExercisesData>

    @Query("SELECT exercise.idx, exercise.title, exercise.revert_count, exercise.play_time, exercise.health_notice, exercise.health_photo, " +
            "CASE WHEN health_list_items.idx not null THEN 1 else 0 end AS \'check\', " +
            "CASE WHEN health_list_items.idx not null THEN health_list_items.health_sort else -1 end AS \'checkIndex\' from exercise " +
            "LEFT JOIN health_list_items ON health_index = exercise.idx AND health_list_index = :index WHERE exercise.title LIKE :keyword")
    fun getEditMode(index:Long, keyword:String) : LiveData<List<ExercisesData>>

    @Query("SELECT exercise.idx, exercise.title, exercise.revert_count, exercise.play_time, exercise.health_notice, exercise.health_photo, " +
            "CASE WHEN health_list_items.idx not null THEN 1 else 0 end AS \'check\', " +
            "CASE WHEN health_list_items.idx not null THEN health_list_items.health_sort else -1 end AS \'checkIndex\' from exercise " +
            "LEFT JOIN health_list_items ON health_index = exercise.idx AND health_list_index = :index WHERE :keyword")
    fun getEditModeInSearch(index:Long, keyword:String) : LiveData<List<ExercisesData>>

    @RawQuery(observedEntities = [ExercisesData::class])
    fun getEditModeInSearch(query: SupportSQLiteQuery):LiveData<List<ExercisesData>>

//    @Insert
//    fun insertExercisesData(exercisesData : ExercisesData){
//
//    }
//    @Query("SELECT * FROM exercise WHERE idx = :index")
//    fun getIndexData(index : Int) : LiveData<ArrayList<ExercisesData>>
}