package com.exercise.health_exercise.data.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.health_exercise.data.exercises.ExercisesData

class AddViewModel: ViewModel() {
    private var step:Int = 0
    private var saveSelectList : ArrayList<ExercisesData> = ArrayList()
    private var _selectList : MutableLiveData<LinkedHashMap<Long, ExercisesData>> = MutableLiveData<LinkedHashMap<Long, ExercisesData>>()

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


    fun checkSelectList(idx:Long, data:ExercisesData, isCheck:Boolean){
        if(_selectList.value != null) {
            if(isCheck && !_selectList.value!!.containsKey(idx))
                _selectList.value!!.put(idx, data)
            else{
                if(!isCheck && _selectList.value!!.containsKey(idx))
                    _selectList.value!!.remove(idx)
            }
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
}