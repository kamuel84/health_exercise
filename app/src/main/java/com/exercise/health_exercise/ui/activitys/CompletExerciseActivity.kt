package com.exercise.health_exercise.ui.activitys

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import kotlinx.android.synthetic.main.activity_complete.*

class CompletExerciseActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)



    }
}