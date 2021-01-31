package com.exercise.health_exercise.ui.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.exercise.ExerciseFragment
import com.exercise.health_exercise.ui.exercise_detail.ExerciseDetailFragment
import com.exercise.health_exercise.ui.exercise_detail.ExerciseItemDetailFragment
import kotlinx.android.synthetic.main.activity_exercise_detail.*

class ExerciseDetailActivity : BaseActivity(), ExerciseDetailFragment.onExerciseDetailListener {

    var longExerciseIndex : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)
        setSupportActionBar(toolbar)

        ExerciseApplication.currentActivity = this

        longExerciseIndex = intent.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0)

        btnExerciseDetail_Start.setOnClickListener {
            var exerciseStartItent : Intent = Intent(this@ExerciseDetailActivity, ExerciseActivity::class.java)
            exerciseStartItent.putExtra(AppContents.INTENT_DATA_LIST_INDEX, longExerciseIndex)
            startActivity(exerciseStartItent)

        }

        var exerciseDetailFragment: ExerciseDetailFragment = ExerciseDetailFragment.newInstance(longExerciseIndex, this)
        pushFragment(R.id.flContent, exerciseDetailFragment)

        toolbar.title = resources.getString(R.string.menu_exercise_detail_list)
    }

    override fun onItemSelect(idx: Long) {
        var nextFragment:ExerciseItemDetailFragment = ExerciseItemDetailFragment.newInstance(idx)
        pushFragment(R.id.flContent, nextFragment)
        toolbar.title = resources.getString(R.string.menu_exercise_detail_item)
    }
}