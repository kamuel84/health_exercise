package com.exercise.health_exercise.ui.activitys

import android.app.Activity
import android.os.Bundle
import com.exercise.health_exercise.R
import com.exercise.health_exercise.ui.exercise.ExerciseFragment

class ListAddActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_base)

        var exerciseFrameLayout:ExerciseFragment = ExerciseFragment()
        pushFragment(R.id.layout_fragment, exerciseFrameLayout)
    }
}