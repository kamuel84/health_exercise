package com.exercise.health_exercise.data.health_list

import android.app.Application
import androidx.lifecycle.LiveData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemDao
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.database.AppDataBase
import io.reactivex.rxjava3.core.Completable

class HealthListRepository(application: Application) {
    private var healthListDao : HealthListDao

    companion object{
        @Volatile private var instance:HealthListRepository?=null

        fun getInstance(application: Application) =
                instance ?: synchronized(this){
                    instance?: HealthListRepository(application).also { instance = it }
                }
    }

    init {
        val database = AppDataBase.getInstance(application)!!
        healthListDao = (database as AppDataBase).healthListDao()
    }

    fun healthListAll(): LiveData<List<HealthListData>> {
        return healthListDao.getAll()
    }

    fun healthListAll(index:Long): LiveData<List<HealthListData>> {
        return healthListDao.getAll(index)
    }

    fun healthListWithItem():LiveData<List<HealthListWithItemData>>{
        return healthListDao.getAllWithItem()
    }

    fun healthCustomListWithItem():LiveData<List<HealthListWithItemData>>{
        return healthListDao.getCustomWithItem()
    }

    fun healthLastIndex():Long{
        return healthListDao.getLastIndex()
    }

//    fun healthListThatIndex(index:Int): LiveData<List<HealthListData>> {
//        return healthListDao.getIndexData(index)
//    }

    fun healthListInsert(entity: HealthListData) {
        healthListDao.insert(entity)
    }


    fun healthListUpdate(entity: HealthListData) : Int {
        return healthListDao.update(entity)
    }

    fun healthListDelete(entity: HealthListData) : Int {
        return healthListDao.delete(entity)
    }

    fun healthListDelete(index:Long){
        healthListDao.deleteList(index)
    }
}