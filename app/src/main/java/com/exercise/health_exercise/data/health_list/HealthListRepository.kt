package com.exercise.health_exercise.data.health_list

import android.app.Application
import androidx.lifecycle.LiveData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemDao
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.database.AppDataBase
import io.reactivex.rxjava3.core.Completable

class HealthListRepository(daoHealthList:HealthListDao) {
    private var healthListDao : HealthListDao

    companion object{
        @Volatile private var instance:HealthListRepository?=null

        fun getInstance(healthDao:HealthListDao) =
                instance ?: synchronized(this){
                    instance?: HealthListRepository(healthDao).also { instance = it }
                }
    }

    init {
        healthListDao = daoHealthList
    }

    fun healthListAll(): LiveData<List<HealthListData>> {
        return healthListDao.getAll()
    }

    fun healthListThatIndex(index:Int): LiveData<List<HealthListData>> {
        return healthListDao.getIndexData(index)
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