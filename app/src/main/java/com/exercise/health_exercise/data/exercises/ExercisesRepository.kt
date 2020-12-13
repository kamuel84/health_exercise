package com.exercise.health_exercise.data.exercises

import android.app.Application
import com.exercise.health_exercise.database.AppDataBase
import io.reactivex.rxjava3.core.Completable

class ExercisesRepository(application: Application) {
    private var exercisesDao : ExercisesDao

    init {
        val database = AppDataBase.getInstance(application)!!
        exercisesDao = (database as AppDataBase).exercisesDao()
    }

    fun exerciseInsert(entity: ExercisesData) : Completable{
        return exercisesDao.insert(entity)
    }


    fun exerciseUpdate(entity: ExercisesData) : Completable{
        return exercisesDao.update(entity)
    }

    fun exerciseDelete(entity:ExercisesData) : Completable{
        return exercisesDao.delete(entity)
    }
}