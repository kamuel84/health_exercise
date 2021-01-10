package com.exercise.health_exercise.ui.custom_exercise

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemRepository
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.ui.exercise.ExerciseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CustomExerciseViewModel(application: Application) : AndroidViewModel(application) {
    val itemRepository:HealthList_ItemRepository by lazy {
        HealthList_ItemRepository(application)
    }
    var itemList:LiveData<List<HealthList_ItemsData>> ?= null
    var exerciseList:MutableLiveData<ArrayList<ExercisesData>> ?= null

    init {
        itemList = itemRepository.getItemList()
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getItemAllList():LiveData<List<HealthList_ItemsData>>?{
        return itemList
    }

    fun setExerciseList(list:ArrayList<ExercisesData>){
        if(exerciseList == null)
            exerciseList = MutableLiveData<ArrayList<ExercisesData>>(list)
        else {
            exerciseList!!.value!!.clear()
            exerciseList!!.value!!.addAll(list)
        }
    }

    fun insertItem(itemData: HealthList_ItemsData){
        itemRepository.healthListItemInsert(itemData)
    }

    fun updateItem(itemData:HealthList_ItemsData){
        itemRepository.healthListItemUpdate(itemData)
    }

    fun deleteItem(itemData:HealthList_ItemsData){
        itemRepository.healthListItemDelete(itemData)
    }

    class Factory(val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CustomExerciseViewModel(application) as T
        }
    }
}