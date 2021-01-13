package com.exercise.health_exercise.ui.exercise_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.SelectExerciseListAdapter
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseDetailFragment:BaseFragment() {
    val viewModel: CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            CustomExerciseViewModel::class.java)
    }
    companion object{
        @JvmStatic
        fun newInstance() : ExerciseDetailFragment {
            var fragment: ExerciseDetailFragment = ExerciseDetailFragment()
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
        var rootView:View = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel.customList?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = SelectExerciseListAdapter(mContext!!, this)
                listHome.adapter = adapter
                listHome.layoutManager = GridLayoutManager(mContext, 2)
            }
//
////            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")
//
//            Toast.makeText(context, "Size ::: ${it.size}", Toast.LENGTH_SHORT).show()
            adapter!!.updateList(it)
        })

        return rootView
    }
}