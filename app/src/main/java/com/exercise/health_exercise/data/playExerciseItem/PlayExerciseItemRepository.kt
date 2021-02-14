package com.exercise.health_exercise.data.playExerciseItem

import android.app.Application
import androidx.lifecycle.LiveData
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemDao
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemData
import com.exercise.health_exercise.database.AppDataBase

class PlayExerciseItemRepository(application: Application) {
    private var playExerciseItemDao : PlayExerciseItemDao

    init {
        val database = AppDataBase.getInstance(application)!!
        playExerciseItemDao = (database as AppDataBase).playExerciseItemDao()
    }

    fun getItemList(): LiveData<List<PlayExerciseItemData>> {
        return playExerciseItemDao.getAll()
    }


    fun getItemList(date : String) : LiveData<List<PlayExerciseItemHeaderData>>{
        return playExerciseItemDao.getSumPlayList(date)
    }

    fun insertPlayItemData(itemData: PlayExerciseItemData) : Long{
        return playExerciseItemDao.insert(itemData)
    }

    fun updatePlayItemData(itemData:PlayExerciseItemData):Int{
        return playExerciseItemDao.update(itemData)
    }
}