package com.exercise.health_exercise.ui.custom_exercise

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseListAdapter
import com.exercise.health_exercise.adapters.SelectExerciseListAdapter
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.exercise.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class CustomExerciseFragment : BaseFragment(), SelectExerciseListAdapter.onSelectExerciseListener {

    var adapter:SelectExerciseListAdapter ?= null
    val viewModel:CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            CustomExerciseViewModel::class.java)
    }

    companion object{
        const val EXERCISES_DATA:String = "EXERCISES_DATA"
        @JvmStatic
        fun newInstance(selectExerciseList:ArrayList<ExercisesData>) : CustomExerciseFragment{
            var fragment:CustomExerciseFragment = CustomExerciseFragment()
            var bundle:Bundle = Bundle()
            bundle.putSerializable(EXERCISES_DATA, selectExerciseList)

            fragment.arguments = bundle
            return fragment
        }
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

        var selectExercisesItem:ArrayList<ExercisesData> = arguments?.getSerializable(EXERCISES_DATA) as ArrayList<ExercisesData>

        viewModel.setExerciseList(selectExercisesItem)

        viewModel.exerciseList?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = SelectExerciseListAdapter(mContext!!, this)
                listHome.adapter = adapter
                listHome.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
            }
//
////            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")
//
//            Toast.makeText(context, "Size ::: ${it.size}", Toast.LENGTH_SHORT).show()
            adapter!!.updateList(it)
        })

        return root
    }

    override fun onCountUp(data: ExercisesData, position: Int) {
        var count = viewModel.exerciseList!!.value!!.get(position).revert_count

        count = count+1
        if(count > 0) {
            viewModel.exerciseList!!.value!!.get(position).revert_count = count

            adapter!!.updateList(viewModel.exerciseList!!.value!!)
        }
    }

    override fun onCountDown(data: ExercisesData, position: Int) {
        var count = viewModel.exerciseList!!.value!!.get(position).revert_count

        count = count-1
        if(count > 0) {
            viewModel.exerciseList!!.value!!.get(position).revert_count = count
            adapter!!.updateList(viewModel.exerciseList!!.value!!)
        }


    }

    override fun onIntervalUp(data: ExercisesData, position: Int) {
        var time = viewModel.exerciseList!!.value!!.get(position).play_Time

        time = time + 1000
        if(time > 0) {
            viewModel.exerciseList!!.value!!.get(position).play_Time = time
            adapter!!.updateList(viewModel.exerciseList!!.value!!)
        }
    }

    override fun onIntervalDown(data: ExercisesData, position: Int) {
        var time = viewModel.exerciseList!!.value!!.get(position).play_Time

        time = time - 1000
        if(time > 0) {
            viewModel.exerciseList!!.value!!.get(position).play_Time = time
            adapter!!.updateList(viewModel.exerciseList!!.value!!)
        }
    }

    override fun onSortUp(data: ExercisesData, position: Int) {
        viewModel.exerciseList!!.value!!.add(position-1, data)
        viewModel.exerciseList!!.value!!.removeAt(position+1)

        adapter!!.updateList(viewModel!!.exerciseList!!.value!!)
    }

    override fun onSortDown(data: ExercisesData, position: Int) {
        viewModel.exerciseList!!.value!!.add(position+1, data)
        viewModel.exerciseList!!.value!!.removeAt(position-1)

        adapter!!.updateList(viewModel!!.exerciseList!!.value!!)
    }
}