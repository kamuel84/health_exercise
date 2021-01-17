package com.exercise.health_exercise.ui.activitys

import android.os.Bundle
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.exercise.ExerciseFragment
import com.exercise.health_exercise.ui.exercise_detail.ExerciseDetailFragment
import com.exercise.health_exercise.ui.exercise_detail.ExerciseItemDetailFragment

class ExerciseDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        var exerciseDetailFragment: ExerciseDetailFragment = ExerciseDetailFragment.newInstance(intent.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0))
        pushFragment(R.id.flContent, exerciseDetailFragment)
    }
}