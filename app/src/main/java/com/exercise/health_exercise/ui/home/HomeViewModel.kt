package com.exercise.health_exercise.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(private val healthListRepository:HealthListRepository) : ViewModel() {

    var healthList:LiveData<List<HealthListData>> = healthListRepository.healthListAll()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    fun insertHealthList(healthList:HealthListData){
        viewModelScope.launch { healthListRepository.healthListInsert(healthList) }
    }
}