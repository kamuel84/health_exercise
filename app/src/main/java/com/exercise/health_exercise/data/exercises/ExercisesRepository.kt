package com.exercise.health_exercise.data.exercises

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
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

    fun exerciseList(keyword:String): LiveData<List<ExercisesData>> {
        return exercisesDao.getSearchExercise(keyword)
    }

    fun getGroupSelectExerciseList(index:String):List<ExercisesData>{
        return exercisesDao.getGroupSelectExercise(index)
    }

    fun exerciseList(idx:Long):List<ExercisesData>{
        return exercisesDao.getEditMode(idx)
    }

    fun exerciseList(idx:Long, keyword:String):LiveData<List<ExercisesData>>{
        return exercisesDao.getEditMode(idx, keyword)
    }

    fun exerciseListInSearch(idx:Long, keyword:String):LiveData<List<ExercisesData>>{
        return exercisesDao.getEditModeInSearch(idx, keyword)
    }

    fun exerciseListInSearch(query: SupportSQLiteQuery):LiveData<List<ExercisesData>>{
        return exercisesDao.getEditModeInSearch(query)
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