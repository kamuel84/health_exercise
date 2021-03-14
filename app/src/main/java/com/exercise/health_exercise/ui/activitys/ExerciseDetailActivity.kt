package com.exercise.health_exercise.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.exercise_detail.ExerciseDetailFragment
import com.exercise.health_exercise.ui.exercise_detail.ExerciseItemDetailFragment
import kotlinx.android.synthetic.main.activity_exercise_detail.*

class ExerciseDetailActivity : BaseActivity(), ExerciseDetailFragment.onExerciseDetailListener {

    var longExerciseIndex : Long = 0
    var strTitle : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)
        setSupportActionBar(toolbar)

        ExerciseApplication.currentActivity = this

        longExerciseIndex = intent.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0)
        strTitle = intent.getStringExtra(AppContents.INTENT_DATA_LIST_TITLE)!!

        btnExerciseDetail_Start.setOnClickListener {
            var exerciseStartItent : Intent = Intent(this@ExerciseDetailActivity, ExerciseActivity::class.java)
            exerciseStartItent.putExtra(AppContents.INTENT_DATA_LIST_INDEX, longExerciseIndex)
            startActivity(exerciseStartItent)
        }

        btnExerciseDetail_Detail.setOnClickListener {
            var nextFragment:ExerciseItemDetailFragment = ExerciseItemDetailFragment.newInstance(longExerciseIndex,0)
            pushFragment(R.id.flContent, nextFragment, nextFragment::class.simpleName.toString(), ExerciseDetailFragment::class.simpleName.toString())
            btnExerciseDetail_Detail.visibility = View.GONE
            btnExerciseDetail_Start.visibility = View.GONE
        }

        var exerciseDetailFragment: ExerciseDetailFragment = ExerciseDetailFragment.newInstance(longExerciseIndex, this)
        pushFragment(R.id.flContent, exerciseDetailFragment, exerciseDetailFragment::class.simpleName.toString())

    }

    override fun onResume() {
        super.onResume()
        toolbar.title = strTitle
    }

    override fun onBackPressed() {
        var countFragment : Int = supportFragmentManager.backStackEntryCount
        if(countFragment == 1) {
            finish()
        }else{
            super.onBackPressed()
            if (currentFragment() is ExerciseDetailFragment) {
                toolbar.title = strTitle
                btnExerciseDetail_Detail.visibility = View.VISIBLE
                btnExerciseDetail_Start.visibility = View.VISIBLE
            }
        }
    }

    override fun onItemSelect(idx: Long, position:Int) {
        var nextFragment:ExerciseItemDetailFragment = ExerciseItemDetailFragment.newInstance(idx, position)
        pushFragment(R.id.flContent, nextFragment, nextFragment::class.simpleName.toString(), ExerciseDetailFragment::class.simpleName.toString())
        btnExerciseDetail_Detail.visibility = View.GONE
        btnExerciseDetail_Start.visibility = View.GONE
    }
}