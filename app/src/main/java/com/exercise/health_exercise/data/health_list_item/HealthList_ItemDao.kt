package com.exercise.health_exercise.data.health_list_item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.exercise.health_exercise.data.BaseDao
import com.exercise.health_exercise.data.exercises.ExercisesData

@Dao
interface HealthList_ItemDao : BaseDao<HealthList_ItemsData> {
    @Query("SELECT * FROM health_list_items")
    fun getAll() : LiveData<List<HealthList_ItemsData>>

    @Query("SELECT health_list_items.revert_count as custom_count," +
            "health_list_items.play_time as custom_play_time," +
            "health_list_items.health_list_index as health_list_index," +
            "health_list.title as health_list_title," +
            "health_list_items.health_index as health_index," +
            "exercise.title as health_title," +
            "exercise.health_Notice as health_description," +
            "exercise.health_Photo as health_image_url " +
            "FROM health_list_items" +
            "LEFT JOIN health_list ON health_list_items.health_list_index = health_list.idx" +
            "LEFT JOIN exercise ON health_list_items.health_index = exercise.idx")
    fun getCustomExerciseList():LiveData<List<HealthList_ItemJoinData>>

//    @Query("SELECT * FROM health_list_items WHERE idx =")
//    fun getIndexData(index : Int) : LiveData<ArrayList<HealthList_ItemsData>>
}