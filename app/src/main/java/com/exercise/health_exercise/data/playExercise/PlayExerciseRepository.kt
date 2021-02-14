package com.exercise.health_exercise.data.playExercise

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemDao
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.database.AppDataBase

class PlayExerciseRepository(application: Application) {
    private var playExerciseDao : PlayExerciseDao

    init {
        val database = AppDataBase.getInstance(application)!!
        playExerciseDao = (database as AppDataBase).playExerciseDao()
    }

    fun getItemList(): LiveData<List<PlayExerciseData>> {
        return playExerciseDao.getAll()
    }

    fun getAll(date:String):LiveData<List<PlayExerciseData>>?{
        return playExerciseDao.getAll(date)
    }

    fun getGroupAll(month:String):LiveData<List<PlayExerciseData>>{
        return playExerciseDao.getGroupAll(month)
    }


    fun getItemList(date:String):Int {
        val list : LiveData<List<PlayExerciseData>> = playExerciseDao.getCount(date)
        if(list.value != null && list.value!!.size > 0){
            return list.value!!.size
        } else
            return 0
    }

    fun insertPlayData(itemData:PlayExerciseData) : Long{
        return playExerciseDao.insert(itemData)
    }

    fun updatePlayData(itemData:PlayExerciseData){
        playExerciseDao.update(itemData)
    }
}