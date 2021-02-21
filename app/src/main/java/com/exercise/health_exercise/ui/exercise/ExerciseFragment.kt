package com.exercise.health_exercise.ui.exercise

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseListAdapter
import com.exercise.health_exercise.adapters.HealthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import com.exercise.health_exercise.ui.itemDecoration.gridItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseFragment : BaseFragment(), ExerciseListAdapter.onExerciseListener {

    var adapter: ExerciseListAdapter? = null
    var listIndex : Int = -1
    var isEditMode = false
    var selectIndex:Long = -1L

    val exerciseViewModel by lazy {
        ViewModelProvider(this, ExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            ExerciseViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if(arguments != null ) {
            isEditMode = requireArguments().getBoolean(AppContents.INTENT_DATA_EDIT_MODE, false)
            selectIndex = requireArguments().getLong(AppContents.INTENT_DATA_LIST_INDEX, -1)
        }


        exerciseViewModel.getExerciseAllList(isEditMode, selectIndex)?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = ExerciseListAdapter(mContext!!, this)
                listHome.adapter = adapter
                var gridLayoutManager = GridLayoutManager(mContext, 2)
                listHome.layoutManager = gridLayoutManager
                listHome.addItemDecoration(gridItemDecoration(mContext!!))

            }

//            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")

            if(isEditMode){
                it.forEachIndexed { index, data ->
                    if (ExerciseApplication.currentActivity is ListAddActivity) {
                        var selectData: HashMap<Long, ExercisesData> = (ExerciseApplication.currentActivity as ListAddActivity).selectList

                        if (it.get(index).check) {
                            if (!selectData.containsKey(data.idx))
                                selectData.put(data.idx, it.get(index))
                        } else {
                            if(selectData.containsKey(data.idx))
                                selectData.remove(data.idx)
                        }
                    }
                }

            }

            Toast.makeText(context, "Size ::: ${it.size}", Toast.LENGTH_SHORT).show()
            adapter!!.updateList(it)
        })

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (ExerciseApplication.currentActivity is ListAddActivity) {
            (ExerciseApplication.currentActivity as ListAddActivity).step = 1
        }
    }

    override fun onChecked(data: ExercisesData, position: Int) {
        exerciseViewModel.getExerciseAllList(isEditMode, selectIndex)?.observe(viewLifecycleOwner, Observer {
            it.get(position).check = !data.check

            if (ExerciseApplication.currentActivity is ListAddActivity) {
                var selectData: HashMap<Long, ExercisesData> = (ExerciseApplication.currentActivity as ListAddActivity).selectList

                if (it.get(position).check) {
                    if (!selectData.containsKey(data.idx))
                        selectData.put(data.idx, it.get(position))
                } else {
                    if(selectData.containsKey(data.idx))
                        selectData.remove(data.idx)
                }


                selectData.keys.forEachIndexed { index, l ->
                    run hashKeys@{
                        it.forEachIndexed { index, exercisesData ->
                            if (exercisesData.idx == l) {
                                exercisesData.check = true
                                return@hashKeys
                            }
                        }
                    }
                }


            }

            adapter!!.updateList(it)
        })
    }
}