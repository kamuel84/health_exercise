package com.exercise.health_exercise.data.repositorys

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exercise.health_exercise.data.daos.GroupListDao
import com.exercise.health_exercise.data.exercises.ExercisesDao
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.database.AppDataBase

class GroupListRepository(application: Application) {
    private var groupListDao : GroupListDao

    init {
        val database = AppDataBase.getInstance(application)!!
        groupListDao = (database as AppDataBase).groupListDao()
    }


    fun groupList(idx:Long): List<ExercisesData> {
        return groupListDao.getGroupList(idx)
    }
}