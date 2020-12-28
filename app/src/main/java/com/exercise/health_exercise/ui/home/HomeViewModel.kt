package com.exercise.health_exercise.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListRepository
import com.exercise.health_exercise.utils.ArrayUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val healthListRepository by lazy {
        HealthListRepository(application)
    }
    var healthList:LiveData<List<HealthListData>> ?= null

    init {
        healthList = healthListRepository.healthListAll()
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    fun insertHealthList(healthList:HealthListData){
        healthListRepository.healthListInsert(healthList)
    }

    fun getAllHealthList() : LiveData<List<HealthListData>>?{
        return healthList
    }

    class Factory(val application: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(application) as T
        }
    }
}