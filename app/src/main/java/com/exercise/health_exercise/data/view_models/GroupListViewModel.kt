package com.exercise.health_exercise.data.view_models

import android.app.Application
import androidx.lifecycle.*
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.repositorys.GroupListRepository
import com.exercise.health_exercise.ui.activitys.BaseActivity
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import com.exercise.health_exercise.ui.exercise.ExerciseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class GroupListViewModel(application: Application) : AndroidViewModel(application) {

    val groupListRepositofy by lazy {
        GroupListRepository(application)
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var _groupList: MutableLiveData<List<ExercisesData>> = MutableLiveData()
    private var _groupIndex: MutableLiveData<Long> = MutableLiveData()

    private var checkList: ArrayList<ExercisesData>? = null

    val groupList: LiveData<List<ExercisesData>>
        get() = _groupList

    val groupIndex: LiveData<Long>
        get() = _groupIndex

    fun getGroupList() {
        if (_groupIndex.value != null && _groupIndex.value!! > 0)
            _groupList.value = groupListRepositofy.groupList(_groupIndex.value!!)
    }

    fun getGroupIndex(): Long? {
        return _groupIndex.value
    }

    fun setGroupIndex(index: Long) {
        this._groupIndex.value = index
    }

    fun setItemCheck(baseActivity: BaseActivity){
        if (baseActivity is ListAddActivity) {
            val addActivity: ListAddActivity = baseActivity as ListAddActivity

            if(_groupList != null && _groupList!!.value != null){
                _groupList!!.value!!.forEachIndexed { index, exercisesData ->
                    addActivity.listViewModel.getSelectList()!!.forEachIndexed { index, checkedData ->
                        if (checkedData.idx == exercisesData.idx) {
                            exercisesData.check = true
                            exercisesData.checkIndex = index + 1
                        }
                    }
                }
            }

            _groupList.value = _groupList.value
        }

    }

    fun checkExerciseList(baseActivity: BaseActivity, position: Int, data: ExercisesData, isCheck: Boolean) {
        if (baseActivity is ListAddActivity) {
            val addActivity:ListAddActivity = baseActivity as ListAddActivity

            addActivity.listViewModel.checkSelectList(data.idx, data, isCheck)

            if(_groupList != null && _groupList!!.value != null){
                run check@{
                    _groupList!!.value!!.forEachIndexed { index, exercisesData ->
                        if (exercisesData.idx == data.idx) {
                            exercisesData.check = isCheck
//                        return@check
                        }

                        if (!exercisesData.check)
                            exercisesData.checkIndex = -1

                        addActivity.listViewModel.getSelectList()!!.forEachIndexed { index, checkedData ->
                            if (checkedData.idx == exercisesData.idx) {
                                exercisesData.checkIndex = index + 1
                            }
                        }

                    }
                }

                _groupList.value = _groupList.value

            }
        }
    }

//    fun checkExerciseList(data: ExercisesData, position: Int, isCheck: Boolean): LiveData<List<ExercisesData>>? {
//        if (checkList == null)
//            checkList = ArrayList<ExercisesData>()
//
//        if (isCheck)
//            checkList?.add(data)
//        else
//            checkList?.remove(data)
//
//        if (groupList != null && groupList!!.value != null) {
//            run check@{
//                groupList!!.value!!.forEachIndexed { index, exercisesData ->
//                    if (exercisesData.idx == data.idx) {
//                        exercisesData.check = isCheck
////                        return@check
//                    }
//
//                    if (!exercisesData.check)
//                        exercisesData.checkIndex = -1
//
//                    checkList!!.forEachIndexed { index, checkedData ->
//                        if (checkedData.idx == exercisesData.idx) {
//                            exercisesData.checkIndex = index + 1
//                        }
//                    }
//                }
//            }
//        }
//        return groupList
//    }

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GroupListViewModel(application) as T
        }
    }
}