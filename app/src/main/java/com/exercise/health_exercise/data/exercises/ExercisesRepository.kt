package com.exercise.health_exercise.data.exercises

import android.app.Application
import androidx.lifecycle.LiveData
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.database.AppDataBase
import io.reactivex.rxjava3.core.Completable

class ExercisesRepository(application: Application) {
    private var exercisesDao : ExercisesDao

    init {
        val database = AppDataBase.getInstance(application)!!
        exercisesDao = (database as AppDataBase).exercisesDao()
    }

    fun exerciseList(): LiveData<List<ExercisesData>> {
        return exercisesDao.getAll()
    }


    fun exerciseInsert(entity: ExercisesData){
        exercisesDao.insert(entity)
    }

    fun exerciseUpdate(entity: ExercisesData) : Int{
        return exercisesDao.update(entity)
    }

    fun exerciseDelete(entity:ExercisesData) : Int{
        return exercisesDao.delete(entity)
    }
}