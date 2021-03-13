package com.exercise.health_exercise.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.exercise_detail.ExerciseDetailFragment
import com.exercise.health_exercise.ui.exercise_detail.ExerciseItemDetailFragment
import kotlinx.android.synthetic.main.activity_exercise_detail.*

class ExerciseItemDetailActivity: BaseActivity() {
    var longExerciseIndex : Long = 0
    var position : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)
        setSupportActionBar(toolbar)

        ExerciseApplication.currentActivity = this
        btnExerciseDetail_Start.visibility = View.GONE
        btnExerciseDetail_Detail.visibility = View.GONE

        longExerciseIndex = intent.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0L)
        position = intent.getIntExtra(AppContents.INTENT_DATA_LIST_POSITION, 0)

        var nextFragment: ExerciseItemDetailFragment = ExerciseItemDetailFragment.newInstance(longExerciseIndex, position)
        pushFragment(R.id.flContent, nextFragment, nextFragment::class.simpleName.toString(), ExerciseDetailFragment::class.simpleName.toString())
        toolbar.title = resources.getString(R.string.menu_exercise_detail_item)
    }

    override fun onBackPressed() {
        finish()
    }
}