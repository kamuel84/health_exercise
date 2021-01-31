package com.exercise.health_exercise.data.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.data.playExercise.PlayExerciseRepository
import com.exercise.health_exercise.ui.exercise.ExerciseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class PlayExerciseViewModel(application: Application) : AndroidViewModel(application) {
    val playExerciseRepository:PlayExerciseRepository by lazy {
        PlayExerciseRepository(application)
    }
    var playList: LiveData<List<PlayExerciseData>>?= null

    init {
        playList = playExerciseRepository.getItemList()
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getPlayAllList() : LiveData<List<PlayExerciseData>>?{
        return playList
    }


    class Factory(val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PlayExerciseViewModel(application) as T
        }
    }

}