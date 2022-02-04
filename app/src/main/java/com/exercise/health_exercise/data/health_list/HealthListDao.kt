package com.exercise.health_exercise.data.health_list

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.exercise.health_exercise.data.BaseDao
import com.exercise.health_exercise.data.exercises.ExercisesData

@Dao
interface HealthListDao : BaseDao<HealthListData> {
    @Query("SELECT * FROM health_list ORDER BY list_type")
    fun getAll() : LiveData<List<HealthListData>>

    @Query("SELECT * FROM health_list WHERE idx = :index")
    fun getAll(index:Long) : LiveData<List<HealthListData>>

    @Query("SELECT * FROM health_list WHERE list_type = 'C' OR list_type ='CD'")
    fun getCustomList() : LiveData<List<HealthListData>>

    @Query("SELECT idx FROM health_list ORDER BY idx DESC LIMIT 1")
    fun getLastIndex():Long

    @Query("SELECT health_list.*, health_list_items.health_Photo, health_list_items.item_count, 0 AS 'isChecked' FROM health_list " +
            "LEFT JOIN (SELECT *, count(*) as item_count FROM health_list_items " +
            "INNER JOIN exercise ON health_list_items.health_index = exercise.idx GROUP BY health_list_items.health_list_index) as health_list_items " +
            "ON health_list.idx = health_list_items.health_list_index WHERE health_list.list_type = 'C' AND health_list.idx = :index")
    fun getAllWithItem(index:Long):List<HealthListWithItemData>


    @Query("SELECT health_list.*, health_list_items.health_Photo, health_list_items.item_count, 0 AS 'isChecked' FROM health_list " +
            "LEFT JOIN (SELECT *, count(*) as item_count FROM health_list_items " +
            "INNER JOIN exercise ON health_list_items.health_index = exercise.idx GROUP BY health_list_items.health_list_index) as health_list_items " +
            "ON health_list.idx = health_list_items.health_list_index WHERE health_list.list_type = 'D'")
    fun getAllWithItem():LiveData<List<HealthListWithItemData>>

    @Query("SELECT health_list.*, health_list_items.health_Photo, health_list_items.item_count, 0 AS 'isChecked' FROM health_list " +
            "LEFT JOIN (SELECT *, count(*) as item_count FROM health_list_items " +
            "INNER JOIN exercise ON health_list_items.health_index = exercise.idx GROUP BY health_list_items.health_list_index) as health_list_items " +
            "ON health_list.idx = health_list_items.health_list_index WHERE health_list.list_type = 'C'")
    fun getCustomWithItem():LiveData<List<HealthListWithItemData>>

    @Query("DELETE FROM health_list WHERE idx = :index")
    fun deleteList(index:Long)

//    @Query("SELECT * FROM health_list WHERE idx = :index")
//    fun getIndexData(index : Int) : LiveData<List<HealthListData>>
}