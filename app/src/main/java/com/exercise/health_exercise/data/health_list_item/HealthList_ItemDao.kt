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

//    @Query("SELECT * FROM health_list_items WHERE idx =")
//    fun getIndexData(index : Int) : LiveData<ArrayList<HealthList_ItemsData>>
}