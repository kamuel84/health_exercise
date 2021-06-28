package com.exercise.health_exercise.ui.exercise

import android.app.Application
import androidx.lifecycle.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.ui.activitys.BaseActivity
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class ExerciseViewModel(application: Application) : AndroidViewModel(application) {
    val exercisesRepository by lazy {
        ExercisesRepository(application)
    }
    var exerciseList: LiveData<List<ExercisesData>>? = null
    var filter = MutableLiveData<String>("%")
    var idx: Long = -1
    var isInSearch: Boolean = false

    init {
        exerciseList = Transformations.switchMap(filter) { filter ->
            if (isInSearch) {
                var queryString: String = "SELECT exercise.idx, exercise.title, exercise.revert_count, exercise.play_time, exercise.health_notice, exercise.health_photo, "
                queryString += "CASE WHEN health_list_items.idx not null THEN 1 else 0 end AS \'check\', "
                queryString += "CASE WHEN health_list_items.idx not null THEN health_list_items.health_sort else -1 end AS \'checkIndex\' from exercise "
                queryString += "LEFT JOIN health_list_items ON health_index = exercise.idx AND health_list_index = ? WHERE "
                var args: ArrayList<Any> = ArrayList()
                args.add(idx)

                var commaFilter = filter.split(",")

                commaFilter.forEachIndexed { index, s ->
                    queryString += "exercise.title LIKE ? "
                    args.add("%$s%")
                    if (index < commaFilter.size-1)
                        queryString += "OR "
                }

                val query = SimpleSQLiteQuery(queryString, args.toArray())
                exercisesRepository.exerciseListInSearch(query)
            } else exercisesRepository.exerciseList(idx, filter)
        }
    }

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var checkList: ArrayList<ExercisesData>? = null

    fun setKeyword(isInSearch: Boolean, keyword: String) {
        this.isInSearch = isInSearch
        val f = when {
            keyword.isEmpty() -> "%"
            else -> {
                if (isInSearch) {
                    keyword
                } else {
                    "%$keyword%"
                }
            }
        }
        filter.postValue(f)
    }

    fun setCheckData(listData: List<ExercisesData>) {
        if (checkList == null)
            checkList = ArrayList<ExercisesData>()
        else
            checkList!!.clear()

        listData.forEachIndexed { index, exercisesData ->
            if (exercisesData.check) {
                var index = exercisesData.checkIndex

                if (checkList!!.size > index) {
                    checkList!!.add(index, exercisesData)
                } else
                    checkList!!.add(exercisesData)
            }
        }


    }

    fun checkData(exerciseData: List<ExercisesData>) {
        if (checkList != null && checkList!!.size > 0) {

            checkList!!.forEachIndexed { checkIndex, checkData ->
                run checkData@{
                    if (exerciseData != null && exerciseData!!.size > 0) {
                        exerciseData.forEachIndexed { index, listData ->
                            if (listData.idx == checkData.idx) {
                                listData.check = true
                                listData.checkIndex = checkIndex + 1
                                return@checkData
                            }
                        }
                    }
                }
            }
        }
    }

    fun getExerciseAllList(editMode: Boolean, idx: Long): LiveData<List<ExercisesData>>? {
        if (!editMode)
            return exerciseList
        else {
            this.idx = idx
            return exerciseList
        }
    }

    fun getExerciseSearchList(editMode: Boolean, idx: Long, keyword: String): LiveData<List<ExercisesData>>? {
        if (checkList == null)
            checkList = ArrayList<ExercisesData>()

        if (!editMode) {
            exerciseList = exercisesRepository.exerciseList(keyword)
        } else {
            exerciseList = exercisesRepository.exerciseList(idx, keyword)
        }
//        if(exerciseList != null && exerciseList!!.value != null) {
//            exerciseList!!.value!!.forEachIndexed { index, exercisesData ->
//                if (exercisesData.check) {
//                    checkList?.add(exercisesData)
//                }
//            }
//        }

        if (checkList != null && checkList!!.size > 0) {
            run checkData@{
                checkList!!.forEachIndexed { index, checkData ->
                    if (exerciseList != null && exerciseList!!.value != null && exerciseList!!.value!!.size > 0) {
                        exerciseList!!.value!!.forEachIndexed { index, exercisesData ->
                            if (exercisesData.idx == checkData.idx) {
                                exercisesData.check = true
                                exercisesData.checkIndex = index + 1
                                return@checkData
                            }
                        }
                    }
                }
            }
        }

        return exerciseList
    }

    fun checkExerciseList(baseActivity: BaseActivity, position: Int, data: ExercisesData, isCheck: Boolean): LiveData<List<ExercisesData>>? {
        if (baseActivity is ListAddActivity) {
            val addActivity: ListAddActivity = baseActivity as ListAddActivity

            addActivity.listViewModel.checkSelectList(data.idx, data, isCheck)

            if(exerciseList != null && exerciseList!!.value != null){
                run check@{
                    exerciseList!!.value!!.forEachIndexed { index, exercisesData ->
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
            }
        }

        return exerciseList
    }

    fun setItemCheck(baseActivity: BaseActivity): LiveData<List<ExercisesData>>?{
        if (baseActivity is ListAddActivity) {
            val addActivity: ListAddActivity = baseActivity as ListAddActivity

            if(exerciseList != null && exerciseList!!.value != null){
                exerciseList!!.value!!.forEachIndexed { index, exercisesData ->
                    addActivity.listViewModel.getSelectList()!!.forEachIndexed { index, checkedData ->
                        if (checkedData.idx == exercisesData.idx) {
                            exercisesData.check = true
                            exercisesData.checkIndex = index + 1
                        }
                    }
                }
            }
        }

        return exerciseList
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
//        if (exerciseList != null && exerciseList!!.value != null) {
//            run check@{
//                exerciseList!!.value!!.forEachIndexed { index, exercisesData ->
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
//        return exerciseList
//    }

    fun insertExercise(exercisesData: ExercisesData) {
        exercisesRepository.exerciseInsert(exercisesData)
    }

    fun updateExercise(exercisesData: ExercisesData) {
        exercisesRepository.exerciseUpdate(exercisesData)
    }

    fun deleteExercise(exercisesData: ExercisesData) {
        exercisesRepository.exerciseDelete(exercisesData)
    }


    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ExerciseViewModel(application) as T
        }
    }
}