package com.exercise.health_exercise.data.health_list

import android.app.Application
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemDao
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.database.AppDataBase
import io.reactivex.rxjava3.core.Completable

class HealthListRepository(application: Application) {
    private var healthListDao : HealthListDao

    init {
        val database = AppDataBase.getInstance(application)!!
        healthListDao = (database as AppDataBase).healthListDao()
    }

    fun healthListInsert(entity: HealthListData) : Completable {
        return healthListDao.insert(entity)
    }


    fun healthListUpdate(entity: HealthListData) : Completable {
        return healthListDao.update(entity)
    }

    fun healthListDelete(entity: HealthListData) : Completable {
        return healthListDao.delete(entity)
    }
}