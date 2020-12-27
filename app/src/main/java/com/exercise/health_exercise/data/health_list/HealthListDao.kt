package com.exercise.health_exercise.data.health_list

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.exercise.health_exercise.data.BaseDao
import com.exercise.health_exercise.data.exercises.ExercisesData

@Dao
interface HealthListDao : BaseDao<HealthListData> {
    @Query("SELECT * FROM health_list")
    fun getAll() : LiveData<List<HealthListData>>

    @Query("SELECT * FROM health_list WHERE idx = :index")
    fun getIndexData(index : Int) : LiveData<List<HealthListData>>
}