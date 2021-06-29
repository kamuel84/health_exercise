package com.exercise.health_exercise.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListRepository
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.utils.ArrayUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val healthListRepository by lazy {
        HealthListRepository(application)
    }

    val exercisesRepository: ExercisesRepository by lazy {
        ExercisesRepository(application)
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

    fun getAllHealthListJoinItem(index:Long) : String{
        var strCode = ""
        exercisesRepository.exerciseList(index).forEachIndexed { index, itemData ->
            var strTemp = itemData.title

            if(strCode != "")
                strCode += ", "
            strCode += strTemp.substring(strTemp.indexOf("[")+1, strTemp.indexOf("]"))
        }
        return strCode
    }

    fun getAllHealthListJoinItem() : LiveData<List<HealthListWithItemData>>?{
        return healthListRepository.healthListWithItem()
    }

    fun getCustomHealthListJoinItem():LiveData<List<HealthListWithItemData>>?{
        return healthListRepository.healthCustomListWithItem()
    }

    fun getLastIndex():Long{
        return healthListRepository.healthLastIndex()
    }

    fun healthListDelete(index:Long){
        healthListRepository.healthListDelete(index)
    }

    fun getTopHealthList() : HealthListData?{
        var size : Int = getAllHealthList()!!.value!!.size
        if(size > 0)
            size = size-1

        return getAllHealthList()!!.value!!.get(size)
    }

    class Factory(val application: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(application) as T
        }
    }
}