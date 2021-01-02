package com.exercise.health_exercise.ui.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.ui.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    val exercisesRepository by lazy {
        ExercisesRepository(application)
    }
    var exerciseList:LiveData<List<ExercisesData>>?= null

    init {
        exerciseList = exercisesRepository.exerciseList()
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getExerciseAllList() : LiveData<List<ExercisesData>>?{
        return exerciseList
    }

    fun insertExercise(exercisesData: ExercisesData){
        exercisesRepository.exerciseInsert(exercisesData)
    }

    fun updateExercise(exercisesData: ExercisesData){
        exercisesRepository.exerciseUpdate(exercisesData)
    }

    fun deleteExercise(exercisesData: ExercisesData){
        exercisesRepository.exerciseDelete(exercisesData)
    }


    class Factory(val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ExerciseViewModel(application) as T
        }
    }
}