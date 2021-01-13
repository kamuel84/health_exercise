package com.exercise.health_exercise.data.health_list_item

import android.app.Application
import androidx.lifecycle.LiveData
import com.exercise.health_exercise.data.exercises.ExercisesDao
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.database.AppDataBase
import io.reactivex.rxjava3.core.Completable

class HealthList_ItemRepository(application: Application) {
    private var healthListItemDao : HealthList_ItemDao

    init {
        val database = AppDataBase.getInstance(application)!!
        healthListItemDao = (database as AppDataBase).healthListItemDao()
    }

    fun getItemList():LiveData<List<HealthList_ItemsData>>{
        return healthListItemDao.getAll()
    }

    fun getCustomData():LiveData<List<HealthList_ItemJoinData>>{
        return healthListItemDao.getCustomExerciseList()
    }

    fun healthListItemInsert(entity: HealthList_ItemsData) {
        healthListItemDao.insert(entity)
    }


    fun healthListItemUpdate(entity: HealthList_ItemsData) : Int {
        return healthListItemDao.update(entity)
    }

    fun healthListItemDelete(entity: HealthList_ItemsData) : Int {
        return healthListItemDao.delete(entity)
    }
}