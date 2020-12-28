package com.exercise.health_exercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListRepository
import com.exercise.health_exercise.database.AppDataBase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SplashActivity : AppCompatActivity() {
    val healthListRepository by lazy {
        HealthListRepository(application)
    }

    val exercisesRepository by lazy {
        ExercisesRepository(application)
    }

    val healthItemListRepository by lazy {
        HealthListRepository(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        init()

        var intent:Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun init(){
        /** DB 생성 **/
        AppDataBase.getInstance(this)

        /** DB Data Insert **/
//        var executor:Executor = Executors.newSingleThreadExecutor()
//        executor.execute(Runnable {
//
//        })

        var healthListData : HealthListData = HealthListData(0L, "운동리스트 1", "D")
        AppDataBase.getInstance(this).healthListDao().insert(healthListData)
//
//        var exercisesData : ExercisesData = ExercisesData( 0L, "테스트 운동1", 3, 30, "운동 설명 입니다.", "")
//        AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
    }
}