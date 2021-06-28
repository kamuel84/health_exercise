package com.exercise.health_exercise.data.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import com.exercise.health_exercise.data.BaseDao
import com.exercise.health_exercise.data.exercises.ExercisesData

/** DAO (Data Access Object) **/
@Dao
interface GroupListDao : BaseDao<ExercisesData> {
    @Query("SELECT exercise.* FROM exercise " +
            "LEFT JOIN health_list_items ON health_index = exercise.idx " +
            "WHERE health_list_items.health_list_index = :index")
    fun getGroupList(index:Long): List<ExercisesData>
}