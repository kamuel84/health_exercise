package com.exercise.health_exercise.data.playExercise

import android.app.Application
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

    fun insertPlayData(itemData:PlayExerciseData){
        playExerciseDao.insert(itemData)
    }

    fun updatePlayData(itemData:PlayExerciseData){
        playExerciseDao.update(itemData)
    }
}