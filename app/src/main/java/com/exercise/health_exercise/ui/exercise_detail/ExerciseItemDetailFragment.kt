package com.exercise.health_exercise.ui.exercise_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseDetailAdapter
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseItemDetailFragment:BaseFragment() {
    val viewModel:CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            CustomExerciseViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** 페이저 넣어야 함 **/
        var rootView:View = inflater.inflate(R.layout.fragment_exercise_item_detail, container, false)

        viewModel.customList?.observe(viewLifecycleOwner, Observer {
        })

        return rootView
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}