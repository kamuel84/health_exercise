package com.exercise.health_exercise.data.health_list_item

import android.app.Application
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

    fun healthListItemInsert(entity: HealthList_ItemsData) : Completable {
        return healthListItemDao.insert(entity)
    }


    fun healthListItemUpdate(entity: HealthList_ItemsData) : Completable {
        return healthListItemDao.update(entity)
    }

    fun healthListItemDelete(entity: HealthList_ItemsData) : Completable {
        return healthListItemDao.delete(entity)
    }
}