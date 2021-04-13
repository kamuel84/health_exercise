package com.exercise.health_exercise

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.room.RoomDatabase
import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListRepository
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.database.AppDataBase
import com.exercise.health_exercise.utils.PreferenceUtils

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
        startMain()
    }

    var callback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            //DO AS NEEDED
            Log.d("kamuel", "onCreate!!!!")
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            //DO AS NEEDED
            Log.d("kamuel", "onOpen!!!!")
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)
            Log.d("kamuel", "onDestructiveMigration!!!!")
        }
    }

    fun startMain(){
        Handler().postDelayed({
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    fun init() {
        /** DB 생성 **/
        AppDataBase.getInstance(this, callback)

        /** DB Data Insert **/
//        var executor:Executor = Executors.newSingleThreadExecutor()
//        executor.execute(Runnable {
//
//        })
        var prefUtils: PreferenceUtils = PreferenceUtils.getInstance(this)

        if (!prefUtils.getBoolean("default_data", false)) {
            var healthList: ArrayList<HealthListData> = ArrayList<HealthListData>()
            /** 운동 리스트 등록 부분 **/
            /** HealthListData(자동증가, 운동리스트 title, 사용자가 만든 list인지 구분(D=Default, C=사용자))**/
            /** idx = 1**/
            healthList.add(HealthListData(0L, "1 시야안정머리", "D"))
            /** idx = 2**/
            healthList.add(HealthListData(0L, "2 시야안정눈", "D"))
            /** idx = 3**/
            healthList.add(HealthListData(0L, "3 시야안정보행", "D"))
            /** idx = 4**/
            healthList.add(HealthListData(0L, "4 발목전략", "D"))
            /** idx = 5**/
            healthList.add(HealthListData(0L, "5 고관절발고정", "D"))
            /** idx = 6**/
            healthList.add(HealthListData(0L, "6 고관절발흔들기", "D"))
            /** idx = 7**/
            healthList.add(HealthListData(0L, "7 발디딤", "D"))
            /** idx = 8**/
            healthList.add(HealthListData(0L, "8 습관화운동서서", "D"))
            /** idx = 9**/
            healthList.add(HealthListData(0L, "9 습관운동앉아서", "D"))
            /** idx = 10**/
            healthList.add(HealthListData(0L, "10 경부성어지럼", "D"))
            /** idx = 11**/
            healthList.add(HealthListData(0L, "11 워밍업", "D"))
            /** idx = 12**/
            healthList.add(HealthListData(0L, "12 쿨링", "D"))
            /** idx = 13**/
            healthList.add(HealthListData(0L, "13 상교차", "D"))
            /** idx = 14**/
            healthList.add(HealthListData(0L, "14 어깨스트레칭", "D"))
            /** idx = 15**/
            healthList.add(HealthListData(0L, "15 하지스트레칭", "D"))
            /** idx = 16**/
            healthList.add(HealthListData(0L, "16 하지스트레칭의자", "D"))
            /** idx = 17**/
            healthList.add(HealthListData(0L, "17 하지근력", "D"))
            /** idx = 18**/
            healthList.add(HealthListData(0L, "18 하지근력의자", "D"))
            /** idx = 19**/
            healthList.add(HealthListData(0L, "19 하교차", "D"))

            healthList.forEachIndexed { index, healthListData ->
                AppDataBase.getInstance(this, callback).healthListDao().insert(healthListData)
            }

            /** 운동 등록 부분 **/
            var exerciseList: ArrayList<ExercisesData> = ArrayList<ExercisesData>()
            /** ExercisesData(자동증가, 운동 Title, 운동 반복횟수, 운동 시간) **/
            /** idx = 1**/
            exerciseList.add(ExercisesData(0L, "1시야안정머리1수평", 1, 20000, "1시야안정머리1수평", "ex01_01", -1, false))
            /** idx = 2**/
            exerciseList.add(ExercisesData(0L, "1시야안정머리2수직", 1, 20000, "1시야안정머리2수직", "ex01_02", -1, false))
            /** idx = 3**/
            exerciseList.add(ExercisesData(0L, "1시야안정머리3원", 1, 20000, "1시야안정머리3원", "ex01_03", -1, false))
            /** idx = 4**/
            exerciseList.add(ExercisesData(0L, "2시야안정눈1원활추적", 1, 20000, "2시야안정눈1원활추적", "ex02_01", -1, false))
            /** idx = 5**/
            exerciseList.add(ExercisesData(0L, "2시야안정눈2충동", 1, 20000, "2시야안정눈2충동", "ex02_02", -1, false))
            /** idx = 6**/
            exerciseList.add(ExercisesData(0L, "2시야안정눈3마인드컨트롤", 1, 20000, "2시야안정눈3마인드컨트롤", "ex02_03", -1, false))
            /** idx = 7**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행1고정수평", 1, 20000, "3시야안정보행1고정수평", "ex03_01", -1, false))
            /** idx = 8**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행2고정수직", 1, 20000, "3시야안정보행2고정수직", "ex03_02", -1, false))
            /** idx = 9**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행3이동수평", 1, 20000, "3시야안정보행3이동수평", "ex03_03", -1, false))
            /** idx = 10**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행4이동수직", 1, 20000, "3시야안정보행4이동수직", "ex03_04", -1, false))
            /** idx = 11**/
            exerciseList.add(ExercisesData(0L, "4발목전략1앞뒤", 1, 20000, "4발목전략1앞뒤", "ex04_01", -1, false))
            /** idx = 12**/
            exerciseList.add(ExercisesData(0L, "4발목전략2좌우", 1, 20000, "4발목전략2좌우", "ex04_02", -1, false))
            /** idx = 13**/
            exerciseList.add(ExercisesData(0L, "4발목전략3발앞꿈치", 1, 20000, "4발목전략3발앞꿈치", "ex04_03", -1, false))
            /** idx = 14**/
            exerciseList.add(ExercisesData(0L, "4발목전략4발뒤꿈치", 1, 20000, "4발목전략4발뒤꿈치", "ex04_04", -1, false))
            /** idx = 15**/
            exerciseList.add(ExercisesData(0L, "4발목전략5스키", 1, 20000, "4발목전략5스키", "ex04_05", -1, false))
            /** idx = 16**/
            exerciseList.add(ExercisesData(0L, "4발목전략6회전", 1, 20000, "4발목전략6회전", "ex04_06", -1, false))
            /** idx = 17**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정1팔과같이앞뒤", 1, 20000, "5고관절발고정1팔과같이앞뒤", "ex05_01", -1, false))
            /** idx = 18**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정2팔과반대앞뒤", 1, 20000, "5고관절발고정2팔과반대앞뒤", "ex05_02", -1, false))
            /** idx = 19**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정3팔과같이좌우", 1, 20000, "5고관절발고정3팔과같이좌우", "ex05_03", -1, false))
            /** idx = 20**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정4팔과반대좌우", 1, 20000, "5고관절발고정4팔과반대좌우", "ex05_04", -1, false))
            /** idx = 21**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정5허리원", 1, 20000, "5고관절발고정5허리원", "ex05_05", -1, false))
            /** idx = 22**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정6상체원", 1, 20000, "5고관절발고정6상체원", "ex05_06", -1, false))
            /** idx = 23**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기1한발앞뒤", 1, 20000, "6고관절발흔들기1한발앞뒤", "ex06_01", -1, false))
            /** idx = 24**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기2몸앞한발좌우", 1, 20000, "6고관절발흔들기2몸앞한발좌우", "ex06_02", -1, false))
            /** idx = 25**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기3몸뒤한발좌우", 1, 20000, "6고관절발흔들기3몸뒤한발좌우", "ex06_03", -1, false))
            /** idx = 26**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기4한발수평원", 1, 20000, "6고관절발흔들기4한발수평원", "ex06_04", -1, false))
            /** idx = 27**/
            exerciseList.add(ExercisesData(0L, "7발디딤1앞뒤스텝", 1, 20000, "7발디딤1앞뒤스텝", "ex07_01", -1, false))
            /** idx = 28**/
            exerciseList.add(ExercisesData(0L, "7발디딤2좌우스텝", 1, 20000, "7발디딤2좌우스텝", "ex07_02", -1, false))
            /** idx = 29**/
            exerciseList.add(ExercisesData(0L, "7발디딤3앞좌우", 1, 20000, "7발디딤3앞좌우", "ex07_03", -1, false))
            /** idx = 30**/
            exerciseList.add(ExercisesData(0L, "7발디딤4뒤좌우", 1, 20000, "7발디딤4뒤좌우", "ex07_04", -1, false))
            /** idx = 31**/
            exerciseList.add(ExercisesData(0L, "7발디딤5장애물", 1, 20000, "7발디딤5장애물", "ex07_05", -1, false))
            /** idx = 32**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서1목", 1, 20000, "8습관화운동서서1목", "ex08_01", -1, false))
            /** idx = 33**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서2몸통", 1, 20000, "8습관화운동서서2몸통", "ex08_02", -1, false))
            /** idx = 34**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서3목몸통", 1, 20000, "8습관화운동서서3목몸통", "ex08_03", -1, false))
            /** idx = 35**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서4허리숙여", 1, 20000, "8습관화운동서서4허리숙여", "ex08_04", -1, false))
            /** idx = 36**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서5대각선2", 1, 20000, "8습관화운동서서5대각선2", "ex08_05", -1, false))
            /** idx = 37**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서5대각선", 1, 20000, "8습관화운동서서5대각선", "ex08_06", -1, false))
            /** idx = 38**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서1수평", 1, 20000, "9습관운동앉아서1수평", "ex09_01", -1, false))
            /** idx = 39**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서2수직", 1, 20000, "9습관운동앉아서2수직", "ex09_02", -1, false))
            /** idx = 40**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서3대각선2", 1, 20000, "9습관운동앉아서3대각선2", "ex09_03", -1, false))
            /** idx = 41**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서3대각선", 1, 20000, "9습관운동앉아서3대각선", "ex09_04", -1, false))
            /** idx = 42**/
            exerciseList.add(ExercisesData(0L, "10경부성어지럼1서서", 1, 20000, "10경부성어지럼1서서", "ex10_01", -1, false))
            /** idx = 43**/
            exerciseList.add(ExercisesData(0L, "10경부성어지럼2앉아서", 1, 20000, "10경부성어지럼2앉아서", "ex10_02", -1, false))
            /** idx = 44**/
            exerciseList.add(ExercisesData(0L, "11워밍업1수평", 1, 20000, "11워밍업1수평", "ex11_01", -1, false))
            /** idx = 45**/
            exerciseList.add(ExercisesData(0L, "11워밍업2수직", 1, 20000, "11워밍업2수직", "ex11_02", -1, false))
            /** idx = 46**/
            exerciseList.add(ExercisesData(0L, "11워밍업3대각선", 1, 20000, "11워밍업3대각선", "ex11_03", -1, false))
            /** idx = 47**/
            exerciseList.add(ExercisesData(0L, "12쿨링1허리굽히기", 1, 20000, "12쿨링1허리굽히기", "ex12_01", -1, false))
            /** idx = 48**/
            exerciseList.add(ExercisesData(0L, "12쿨링2팔다리업", 1, 20000, "12쿨링2팔다리업", "ex12_02", -1, false))
            /** idx = 49**/
            exerciseList.add(ExercisesData(0L, "12쿨링3허리트위스트", 1, 20000, "12쿨링3허리트위스트", "ex12_03", -1, false))
            /** idx = 50**/
            exerciseList.add(ExercisesData(0L, "13상교차1대흉근이완", 1, 20000, "13상교차1대흉근이완", "ex13_01", -1, false))
            /** idx = 51**/
            exerciseList.add(ExercisesData(0L, "13상교차2상승모근이완", 1, 20000, "13상교차2상승모근이완", "ex13_02", -1, false))
            /** idx = 52**/
            exerciseList.add(ExercisesData(0L, "13상교차2상승모근이완", 1, 20000, "13상교차2상승모근이완", "ex13_03", -1, false))
            /** idx = 53**/
            exerciseList.add(ExercisesData(0L, "13상교차3하승모근강화", 1, 20000, "13상교차3하승모근강화", "ex13_04", -1, false))
            /** idx = 54**/
            exerciseList.add(ExercisesData(0L, "13상교차4하승모근이완", 1, 20000, "13상교차4하승모근이완", "ex13_05", -1, false))
            /** idx = 55**/
            exerciseList.add(ExercisesData(0L, "13상교차4하승모근이완", 1, 20000, "13상교차4하승모근이완", "ex13_06", -1, false))
            /** idx = 56**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭1어깨수직", 1, 20000, "14어깨스트레칭1어깨수직", "ex14_01", -1, false))
            /** idx = 57**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭1어깨수직", 1, 20000, "14어깨스트레칭1어깨수직", "ex14_02", -1, false))
            /** idx = 58**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭2팔당기기", 1, 20000, "14어깨스트레칭2팔당기기", "ex14_03", -1, false))
            /** idx = 59**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭2팔당기기", 1, 20000, "14어깨스트레칭2팔당기기", "ex14_04", -1, false))
            /** idx = 60**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭3가슴펴기", 1, 20000, "14어깨스트레칭3가슴펴기", "ex14_05", -1, false))
            /** idx = 61**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭4뒤에서양손", 1, 20000, "14어깨스트레칭4뒤에서양손", "ex14_06", -1, false))
            /** idx = 62**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭5옆구리", 1, 20000, "14어깨스트레칭5옆구리", "ex14_07", -1, false))
            /** idx = 63**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭5옆구리", 1, 20000, "14어깨스트레칭5옆구리", "ex14_08", -1, false))
            /** idx = 64**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭6어깨수평", 1, 20000, "14어깨스트레칭6어깨수평", "ex14_09", -1, false))
            /** idx = 65**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭6어깨수평", 1, 20000, "14어깨스트레칭6어깨수평", "ex14_10", -1, false))
            /** idx = 66**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭7한쪽어깨", 1, 20000, "14어깨스트레칭7한쪽어깨", "ex14_11", -1, false))
            /** idx = 67**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭7한쪽어깨", 1, 20000, "14어깨스트레칭7한쪽어깨", "ex14_12", -1, false))
            /** idx = 68**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭8양쪽어깨", 1, 20000, "14어깨스트레칭8양쪽어깨", "ex14_13", -1, false))
            /** idx = 69**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭1힙", 1, 20000, "15하지스트레칭1힙", "ex15_01", -1, false))
            /** idx = 70**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭1힙", 1, 20000, "15하지스트레칭1힙", "ex15_02", -1, false))
            /** idx = 71**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭2발뒤로", 1, 20000, "15하지스트레칭2발뒤로", "ex15_03", -1, false))
            /** idx = 72**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭3발앞으로", 1, 20000, "15하지스트레칭3발앞으로", "ex15_04", -1, false))
            /** idx = 73**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭3발앞으로", 1, 20000, "15하지스트레칭3발앞으로", "ex15_05", -1, false))
            /** idx = 74**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭4무릎앞으로", 1, 20000, "15하지스트레칭4무릎앞으로", "ex15_06", -1, false))
            /** idx = 75**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭4무릎앞으로", 1, 20000, "15하지스트레칭4무릎앞으로", "ex15_07", -1, false))
            /** idx = 76**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭5골반", 1, 20000, "15하지스트레칭5골반", "ex15_08", -1, false))
            /** idx = 77**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭5골반", 1, 20000, "15하지스트레칭5골반", "ex15_09", -1, false))
            /** idx = 78**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭6아킬레스건", 1, 20000, "15하지스트레칭6아킬레스건", "ex15_10", -1, false))
            /** idx = 79**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭6아킬레스건", 1, 20000, "15하지스트레칭6아킬레스건", "ex15_11", -1, false))
            /** idx = 80**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자1힙", 1, 20000, "16하지스트레칭의자1힙", "ex16_01", -1, false))
            /** idx = 81**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자1힙", 1, 20000, "16하지스트레칭의자1힙", "ex16_02", -1, false))
            /** idx = 82**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자2몸흔들며발", 1, 20000, "16하지스트레칭의자2몸흔들며발", "ex16_03", -1, false))
            /** idx = 83**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자3발교차", 1, 20000, "16하지스트레칭의자3발교차", "ex16_04", -1, false))
            /** idx = 84**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자3발교차", 1, 20000, "16하지스트레칭의자3발교차", "ex16_05", -1, false))
            /** idx = 85**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자4팔내리며", 1, 20000, "16하지스트레칭의자4팔내리며", "ex16_06", -1, false))
            /** idx = 86**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자4팔내리며", 1, 20000, "16하지스트레칭의자4팔내리며", "ex16_07", -1, false))
            /** idx = 87**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자5발뒤로", 1, 20000, "16하지스트레칭의자5발뒤로", "ex16_08", -1, false))
            /** idx = 88**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자5발뒤로", 1, 20000, "16하지스트레칭의자5발뒤로", "ex16_09", -1, false))
            /** idx = 89**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자6발앞으로", 1, 20000, "16하지스트레칭의자6발앞으로", "ex16_10", -1, false))
            /** idx = 90**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자6발앞으로", 1, 20000, "16하지스트레칭의자6발앞으로", "ex16_11", -1, false))
            /** idx = 91**/
            exerciseList.add(ExercisesData(0L, "17하지근력1다리앞으로", 1, 20000, "17하지근력1다리앞으로", "ex17_01", -1, false))
            /** idx = 92**/
            exerciseList.add(ExercisesData(0L, "17하지근력1다리앞으로", 1, 20000, "17하지근력1다리앞으로", "ex17_02", -1, false))
            /** idx = 93**/
            exerciseList.add(ExercisesData(0L, "17하지근력2다리옆으로", 1, 20000, "17하지근력2다리옆으로", "ex17_03", -1, false))
            /** idx = 94**/
            exerciseList.add(ExercisesData(0L, "17하지근력2다리옆으로", 1, 20000, "17하지근력2다리옆으로", "ex17_04", -1, false))
            /** idx = 95**/
            exerciseList.add(ExercisesData(0L, "17하지근력3페달", 1, 20000, "17하지근력3페달", "ex17_05", -1, false))
            /** idx = 96**/
            exerciseList.add(ExercisesData(0L, "17하지근력3페달", 1, 20000, "17하지근력3페달", "ex17_06", -1, false))
            /** idx = 97**/
            exerciseList.add(ExercisesData(0L, "17하지근력4앞굽이", 1, 20000, "17하지근력4앞굽이", "ex17_07", -1, false))
            /** idx = 98**/
            exerciseList.add(ExercisesData(0L, "17하지근력4앞굽이", 1, 20000, "17하지근력4앞굽이", "ex17_08", -1, false))
            /** idx = 99**/
            exerciseList.add(ExercisesData(0L, "17하지근력5뒤꿈치", 1, 20000, "17하지근력5뒤꿈치", "ex17_09", -1, false))
            /** idx = 100**/
            exerciseList.add(ExercisesData(0L, "17하지근력6앞꿈치", 1, 20000, "17하지근력6앞꿈치", "ex17_10", -1, false))
            /** idx = 101**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자1다리앞으로", 1, 20000, "18하지근력의자1다리앞으로", "ex18_01", -1, false))
            /** idx = 102**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자1다리앞으로", 1, 20000, "18하지근력의자1다리앞으로", "ex18_02", -1, false))
            /** idx = 103**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자2다리옆으로", 1, 20000, "18하지근력의자2다리옆으로", "ex18_03", -1, false))
            /** idx = 104**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자2다리옆으로", 1, 20000, "18하지근력의자2다리옆으로", "ex18_04", -1, false))
            /** idx = 105**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자3페달돌리기", 1, 20000, "18하지근력의자3페달돌리기", "ex18_05", -1, false))
            /** idx = 106**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자3페달돌리기", 1, 20000, "18하지근력의자3페달돌리기", "ex18_06", -1, false))
            /** idx = 107**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자4앞굽이", 1, 20000, "18하지근력의자4앞굽이", "ex18_07", -1, false))
            /** idx = 108**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자4앞굽이", 1, 20000, "18하지근력의자4앞굽이", "ex18_08", -1, false))
            /** idx = 109**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자5뒤꿈치", 1, 20000, "18하지근력의자5뒤꿈치", "ex18_09", -1, false))
            /** idx = 110**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자5앞꿈치", 1, 20000, "18하지근력의자5앞꿈치", "ex18_10", -1, false))
            /** idx = 111**/
            exerciseList.add(ExercisesData(0L, "19하교차1허리굴곡근", 1, 20000, "19하교차1허리굴곡근", "ex19_01", -1, false))
            /** idx = 112**/
            exerciseList.add(ExercisesData(0L, "19하교차2허리신전근", 1, 20000, "19하교차2허리신전근", "ex19_02", -1, false))
            /** idx = 113**/
            exerciseList.add(ExercisesData(0L, "19하교차2허리신전근", 1, 20000, "19하교차2허리신전근", "ex19_03", -1, false))
            /** idx = 114**/
            exerciseList.add(ExercisesData(0L, "19하교차3대퇴굴곡근", 1, 20000, "19하교차3대퇴굴곡근", "ex19_04", -1, false))
            /** idx = 115**/
            exerciseList.add(ExercisesData(0L, "19하교차4대퇴신전근", 1, 20000, "19하교차4대퇴신전근", "ex19_05", -1, false))

            exerciseList.forEachIndexed { index, exercisesData ->
                AppDataBase.getInstance(this, callback).exercisesDao().insert(exercisesData)
            }

            /** 리스트 세부 운동 추가 부분 **/
            var exerciseItemList: ArrayList<HealthList_ItemsData> = ArrayList<HealthList_ItemsData>()
            /** HealthList_ItemData(자동증가, list idx, 운동 idx, 운동 반복횟수, 운동 시간) **/
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 1, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 2, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 3, 2, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 2, 4, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 5, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 6, 2, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 3, 7, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 3, 8, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 3, 9, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 3, 10, 3, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 4, 11, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 12, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 13, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 14, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 15, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 16, 5, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 5, 17, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 18, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 19, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 20, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 21, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 22, 5, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 6, 23, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 6, 24, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 6, 25, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 6, 26, 3, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 7, 27, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 28, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 29, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 30, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 31, 4, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 8, 32, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 33, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 34, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 35, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 36, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 37, 5, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 9, 38, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 39, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 40, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 41, 3, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 10, 42, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 10, 43, 1, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 11, 44, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 45, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 46, 2, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 12, 47, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 48, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 49, 2, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 13, 50, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 51, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 52, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 53, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 54, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 55, 5, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 14, 56, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 57, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 58, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 59, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 60, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 61, 5, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 62, 6, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 63, 7, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 64, 8, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 65, 9, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 66, 10, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 67, 11, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 68, 12, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 15, 69, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 70, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 71, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 72, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 73, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 74, 5, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 75, 6, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 76, 7, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 77, 8, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 78, 9, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 79, 10, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 16, 80, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 81, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 82, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 83, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 84, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 85, 5, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 86, 6, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 87, 7, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 88, 8, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 89, 9, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 90, 10, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 17, 91, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 92, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 93, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 94, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 95, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 96, 5, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 97, 6, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 98, 7, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 99, 8, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 100, 9, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 18, 101, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 102, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 103, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 104, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 105, 4, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 106, 5, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 107, 6, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 108, 7, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 109, 8, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 110, 9, 1, 20000))

            exerciseItemList.add(HealthList_ItemsData(0L, 19, 111, 0, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 112, 1, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 113, 2, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 114, 3, 1, 20000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 115, 4, 1, 20000))

            exerciseItemList.forEachIndexed { index, exercisesData ->
                AppDataBase.getInstance(this, callback).healthListItemDao().insert(exercisesData)
            }

            prefUtils.setBoolean("default_data", true)
        }


//
//        var exercisesData : ExercisesData = ExercisesData( 0L, "테스트 운동1", 3, 30, "운동 설명 입니다.", "")
//        AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
    }
}