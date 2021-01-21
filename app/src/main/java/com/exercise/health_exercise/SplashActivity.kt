package com.exercise.health_exercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListRepository
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.database.AppDataBase
import com.exercise.health_exercise.utils.PreferenceUtils
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
        var prefUtils : PreferenceUtils = PreferenceUtils.getInstance(this)

        if(!prefUtils.getBoolean("default_data", false)) {
            var healthList:ArrayList<HealthListData> = ArrayList<HealthListData>()
            /** 운동 리스트 등록 부분 **/
            /** HealthListData(자동증가, 운동리스트 title, 사용자가 만든 list인지 구분(D=Default, C=사용자))**/
            /** idx = 1**/
            healthList.add(HealthListData(0L, "운동리스트 1", "D"))
            /** idx = 2**/
            healthList.add(HealthListData(0L, "운동리스트 2", "D"))
            /** idx = 3**/
            healthList.add(HealthListData(0L, "운동리스트 3", "D"))
            /** idx = 4**/
            healthList.add(HealthListData(0L, "운동리스트 4", "D"))
            /** idx = 5**/
            healthList.add(HealthListData(0L, "운동리스트 5", "D"))
            /** idx = 6**/
            healthList.add(HealthListData(0L, "운동리스트 6", "D"))

            healthList.forEachIndexed { index, healthListData ->
                AppDataBase.getInstance(this).healthListDao().insert(healthListData)
            }

            /** 운동 등록 부분 **/
            var exerciseList:ArrayList<ExercisesData> = ArrayList<ExercisesData>()
            /** ExercisesData(자동증가, 운동 Title, 운동 반복횟수, 운동 시간) **/
            /** idx = 1**/
            exerciseList.add(ExercisesData(0L, "운동 1", 3, 30000, "운동설명!!!!", "i_11", false))
            /** idx = 2**/
            exerciseList.add(ExercisesData(0L, "운동 2", 5, 60000, "운동설명22222!!!!", "i_12", false))
            /** idx = 3**/
            exerciseList.add(ExercisesData(0L, "운동 3", 4, 20000, "운동설명333333!!!!", "i_13", false))
            /** idx = 4**/
            exerciseList.add(ExercisesData(0L, "운동 4", 2, 25000, "운동설명444444!!!!", "i_14", false))
            /** idx = 5**/
            exerciseList.add(ExercisesData(0L, "운동 5", 6, 40000, "운동설명55555!!!!", "i_15", false))
            /** idx = 6**/
            exerciseList.add(ExercisesData(0L, "운동 6", 10, 35000, "운동설명66666!!!!", "i_16", false))

            exerciseList.forEachIndexed { index, exercisesData ->
                AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
            }

            /** 리스트 세부 운동 추가 부분 **/
            var exerciseItemList:ArrayList<HealthList_ItemsData> = ArrayList<HealthList_ItemsData>()
            /** HealthList_ItemData(자동증가, list idx, 운동 idx, 운동 반복횟수, 운동 시간) **/
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 1, 3, 3000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 2, 5, 6000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 3, 4, 2000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 4, 2, 2500))

            exerciseItemList.add(HealthList_ItemsData(0L, 2, 5, 6, 4000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 6, 10, 3500))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 3, 4, 2000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 4, 2, 2500))

            prefUtils.setBoolean("default_data", true)
        }



//
//        var exercisesData : ExercisesData = ExercisesData( 0L, "테스트 운동1", 3, 30, "운동 설명 입니다.", "")
//        AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
    }
}