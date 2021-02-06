package com.exercise.health_exercise.ui.custom_exercise

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemRepository
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.data.playExercise.PlayExerciseRepository
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemRepository
import com.exercise.health_exercise.ui.exercise.ExerciseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class CustomExerciseViewModel(application: Application) : AndroidViewModel(application) {
    val itemRepository:HealthList_ItemRepository by lazy {
        HealthList_ItemRepository(application)
    }

    val playExercisesRepository:PlayExerciseRepository by lazy {
        PlayExerciseRepository(application)
    }

    val playExerciseItemRepository:PlayExerciseItemRepository by lazy {
        PlayExerciseItemRepository(application)
    }

    var itemList:LiveData<List<HealthList_ItemsData>> ?= null
    var customList:LiveData<List<HealthList_ItemJoinData>> ?= null
    var exerciseList:MutableLiveData<ArrayList<ExercisesData>> ?= null

    init {
        itemList = itemRepository.getItemList()
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getItemAllList():LiveData<List<HealthList_ItemsData>>?{
        return itemList
    }

    fun getCustomAllList(index:Long):LiveData<List<HealthList_ItemJoinData>>?{
        customList = itemRepository.getCustomData(index)
        return customList
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

    fun insertPlayExercise(itemData:PlayExerciseData){
        playExercisesRepository.insertPlayData(itemData)
    }

    fun updatePlayExercise(itemData:PlayExerciseData){
        playExercisesRepository.insertPlayData(itemData)
    }

    class Factory(val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CustomExerciseViewModel(application) as T
        }
    }
}