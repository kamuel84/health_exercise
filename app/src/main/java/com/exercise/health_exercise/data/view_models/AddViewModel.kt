package com.exercise.health_exercise.data.view_models

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list.HealthListRepository
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddViewModel(application: Application): ViewModel() {
    private var step:Int = 0
    private var saveSelectList : ArrayList<ExercisesData> = ArrayList()
    private var _selectList : MutableLiveData<LinkedHashMap<Long, ExercisesData>> = MutableLiveData<LinkedHashMap<Long, ExercisesData>>()
    val exercisesRepository: ExercisesRepository by lazy {
        ExercisesRepository(application)
    }

    private var _menuTitle:MutableLiveData<String> = MutableLiveData()
    val menuTitle:LiveData<String>
        get() = _menuTitle

    val selectList:LiveData<LinkedHashMap<Long, ExercisesData>>
        get() = _selectList

    fun setStep(step:Int){
        this.step = step
    }

    fun getStep():Int{
        return this.step
    }

    init {
        _selectList.postValue(LinkedHashMap())
    }

    fun setSelectList(idx:Long){
        var tempSelectData : LinkedHashMap<Long, ExercisesData> = LinkedHashMap<Long, ExercisesData>()
        CoroutineScope(Dispatchers.Main).launch {
            val checkList:List<ExercisesData> = async {
                exercisesRepository.exerciseList(idx)
            }.await()

            checkList.forEachIndexed { index, exercisesData ->
                tempSelectData.put(exercisesData.idx, exercisesData)
            }
            _selectList.value = tempSelectData
        }
    }

    fun setMenuTitle(title:String){
        _menuTitle.value = title
    }

    fun checkSelectList(idx:Long, data:ExercisesData, isCheck:Boolean){
        if(_selectList.value != null) {
            if(isCheck && !_selectList.value!!.containsKey(idx))
                _selectList.value!!.put(idx, data)
            else{
                if(!isCheck && _selectList.value!!.containsKey(idx))
                    _selectList.value!!.remove(idx)
            }
            _selectList.value = _selectList.value
        }
    }

    fun getSelectList():ArrayList<ExercisesData>{
        if(_selectList.value != null){
            saveSelectList.clear()

            _selectList.value!!.keys.forEachIndexed { index, key ->
                if (_selectList.value!!.get(key) != null)
                    saveSelectList.add(_selectList.value!!.get(key)!!)
            }
        }

        return saveSelectList
    }


    class Factory(val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AddViewModel(application) as T
        }
    }
}