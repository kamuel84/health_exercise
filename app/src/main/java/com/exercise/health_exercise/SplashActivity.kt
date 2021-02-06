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
                AppDataBase.getInstance(this).healthListDao().insert(healthListData)
            }

            /** 운동 등록 부분 **/
            var exerciseList:ArrayList<ExercisesData> = ArrayList<ExercisesData>()
            /** ExercisesData(자동증가, 운동 Title, 운동 반복횟수, 운동 시간) **/
            /** idx = 1**/
            exerciseList.add(ExercisesData(0L, "1시야안정머리1수평", 1, 10000, "1시야안정머리1수평", "ex01_01", false))
            /** idx = 2**/
            exerciseList.add(ExercisesData(0L, "1시야안정머리2수직", 1, 10000, "1시야안정머리2수직", "ex01_02", false))
            /** idx = 3**/
            exerciseList.add(ExercisesData(0L, "1시야안정머리3원", 1, 10000, "1시야안정머리3원", "ex01_03", false))
            /** idx = 4**/
            exerciseList.add(ExercisesData(0L, "2시야안정눈1원활추적", 1, 10000, "2시야안정눈1원활추적", "ex02_01", false))
            /** idx = 5**/
            exerciseList.add(ExercisesData(0L, "2시야안정눈2충동", 1, 10000, "2시야안정눈2충동", "ex02_02", false))
            /** idx = 6**/
            exerciseList.add(ExercisesData(0L, "2시야안정눈3마인드컨트롤", 1, 10000, "2시야안정눈3마인드컨트롤", "ex02_03", false))
            /** idx = 7**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행1고정수평", 1, 10000, "3시야안정보행1고정수평", "ex03_01", false))
            /** idx = 8**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행2고정수직", 1, 10000, "3시야안정보행2고정수직", "ex03_02", false))
            /** idx = 9**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행3이동수평", 1, 10000, "3시야안정보행3이동수평", "ex03_03", false))
            /** idx = 10**/
            exerciseList.add(ExercisesData(0L, "3시야안정보행4이동수직", 1, 10000, "3시야안정보행4이동수직", "ex03_04", false))
            /** idx = 11**/
            exerciseList.add(ExercisesData(0L, "4발목전략1앞뒤", 1, 10000, "4발목전략1앞뒤", "ex04_01", false))
            /** idx = 12**/
            exerciseList.add(ExercisesData(0L, "4발목전략2좌우", 1, 10000, "4발목전략2좌우", "ex04_02", false))
            /** idx = 13**/
            exerciseList.add(ExercisesData(0L, "4발목전략3발앞꿈치", 1, 10000, "4발목전략3발앞꿈치", "ex04_03", false))
            /** idx = 14**/
            exerciseList.add(ExercisesData(0L, "4발목전략4발뒤꿈치", 1, 10000, "4발목전략4발뒤꿈치", "ex04_04", false))
            /** idx = 15**/
            exerciseList.add(ExercisesData(0L, "4발목전략5스키", 1, 10000, "4발목전략5스키", "ex04_05", false))
            /** idx = 16**/
            exerciseList.add(ExercisesData(0L, "4발목전략6회전", 1, 10000, "4발목전략6회전", "ex04_06", false))
            /** idx = 17**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정1팔과같이앞뒤", 1, 10000, "5고관절발고정1팔과같이앞뒤", "ex05_01", false))
            /** idx = 18**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정2팔과반대앞뒤", 1, 10000, "5고관절발고정2팔과반대앞뒤", "ex05_02", false))
            /** idx = 19**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정3팔과같이좌우", 1, 10000, "5고관절발고정3팔과같이좌우", "ex05_03", false))
            /** idx = 20**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정4팔과반대좌우", 1, 10000, "5고관절발고정4팔과반대좌우", "ex05_04", false))
            /** idx = 21**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정5허리원", 1, 10000, "5고관절발고정5허리원", "ex05_05", false))
            /** idx = 22**/
            exerciseList.add(ExercisesData(0L, "5고관절발고정6상체원", 1, 10000, "5고관절발고정6상체원", "ex05_06", false))
            /** idx = 23**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기1한발앞뒤", 1, 10000, "6고관절발흔들기1한발앞뒤", "ex06_01", false))
            /** idx = 24**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기2몸앞한발좌우", 1, 10000, "6고관절발흔들기2몸앞한발좌우", "ex06_02", false))
            /** idx = 25**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기3몸뒤한발좌우", 1, 10000, "6고관절발흔들기3몸뒤한발좌우", "ex06_03", false))
            /** idx = 26**/
            exerciseList.add(ExercisesData(0L, "6고관절발흔들기4한발수평원", 1, 10000, "6고관절발흔들기4한발수평원", "ex06_04", false))
            /** idx = 27**/
            exerciseList.add(ExercisesData(0L, "7발디딤1앞뒤스텝", 1, 10000, "7발디딤1앞뒤스텝", "ex07_01", false))
            /** idx = 28**/
            exerciseList.add(ExercisesData(0L, "7발디딤2좌우스텝", 1, 10000, "7발디딤2좌우스텝", "ex07_02", false))
            /** idx = 29**/
            exerciseList.add(ExercisesData(0L, "7발디딤3앞좌우", 1, 10000, "7발디딤3앞좌우", "ex07_03", false))
            /** idx = 30**/
            exerciseList.add(ExercisesData(0L, "7발디딤4뒤좌우", 1, 10000, "7발디딤4뒤좌우", "ex07_04", false))
            /** idx = 31**/
            exerciseList.add(ExercisesData(0L, "7발디딤5장애물", 1, 10000, "7발디딤5장애물", "ex07_05", false))
            /** idx = 32**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서1목", 1, 10000, "8습관화운동서서1목", "ex08_01", false))
            /** idx = 33**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서2몸통", 1, 10000, "8습관화운동서서2몸통", "ex08_02", false))
            /** idx = 34**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서3목몸통", 1, 10000, "8습관화운동서서3목몸통", "ex08_03", false))
            /** idx = 35**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서4허리숙여", 1, 10000, "8습관화운동서서4허리숙여", "ex08_04", false))
            /** idx = 36**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서5대각선2", 1, 10000, "8습관화운동서서5대각선2", "ex08_05", false))
            /** idx = 37**/
            exerciseList.add(ExercisesData(0L, "8습관화운동서서5대각선", 1, 10000, "8습관화운동서서5대각선", "ex08_06", false))
            /** idx = 38**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서1수평", 1, 10000, "9습관운동앉아서1수평", "ex09_01", false))
            /** idx = 39**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서2수직", 1, 10000, "9습관운동앉아서2수직", "ex09_02", false))
            /** idx = 40**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서3대각선2", 1, 10000, "9습관운동앉아서3대각선2", "ex09_03", false))
            /** idx = 41**/
            exerciseList.add(ExercisesData(0L, "9습관운동앉아서3대각선", 1, 10000, "9습관운동앉아서3대각선", "ex09_04", false))
            /** idx = 42**/
            exerciseList.add(ExercisesData(0L, "10경부성어지럼1서서", 1, 10000, "10경부성어지럼1서서", "ex10_01", false))
            /** idx = 43**/
            exerciseList.add(ExercisesData(0L, "10경부성어지럼2앉아서", 1, 10000, "10경부성어지럼2앉아서", "ex10_02", false))
            /** idx = 44**/
            exerciseList.add(ExercisesData(0L, "11워밍업1수평", 1, 10000, "11워밍업1수평", "ex11_01", false))
            /** idx = 45**/
            exerciseList.add(ExercisesData(0L, "11워밍업2수직", 1, 10000, "11워밍업2수직", "ex11_02", false))
            /** idx = 46**/
            exerciseList.add(ExercisesData(0L, "11워밍업3대각선", 1, 10000, "11워밍업3대각선", "ex11_03", false))
            /** idx = 47**/
            exerciseList.add(ExercisesData(0L, "12쿨링1허리굽히기", 1, 10000, "12쿨링1허리굽히기", "ex12_01", false))
            /** idx = 48**/
            exerciseList.add(ExercisesData(0L, "12쿨링2팔다리업", 1, 10000, "12쿨링2팔다리업", "ex12_02", false))
            /** idx = 49**/
            exerciseList.add(ExercisesData(0L, "12쿨링3허리트위스트", 1, 10000, "12쿨링3허리트위스트", "ex12_03", false))
            /** idx = 50**/
            exerciseList.add(ExercisesData(0L, "13상교차1대흉근이완", 1, 10000, "13상교차1대흉근이완", "ex13_01", false))
            /** idx = 51**/
            exerciseList.add(ExercisesData(0L, "13상교차2상승모근이완", 1, 10000, "13상교차2상승모근이완", "ex13_02", false))
            /** idx = 52**/
            exerciseList.add(ExercisesData(0L, "13상교차2상승모근이완", 1, 10000, "13상교차2상승모근이완", "ex13_03", false))
            /** idx = 53**/
            exerciseList.add(ExercisesData(0L, "13상교차3하승모근강화", 1, 10000, "13상교차3하승모근강화", "ex13_04", false))
            /** idx = 54**/
            exerciseList.add(ExercisesData(0L, "13상교차4하승모근이완", 1, 10000, "13상교차4하승모근이완", "ex13_05", false))
            /** idx = 55**/
            exerciseList.add(ExercisesData(0L, "13상교차4하승모근이완", 1, 10000, "13상교차4하승모근이완", "ex13_06", false))
            /** idx = 56**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭1어깨수직", 1, 10000, "14어깨스트레칭1어깨수직", "ex14_01", false))
            /** idx = 57**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭1어깨수직", 1, 10000, "14어깨스트레칭1어깨수직", "ex14_02", false))
            /** idx = 58**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭2팔당기기", 1, 10000, "14어깨스트레칭2팔당기기", "ex14_03", false))
            /** idx = 59**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭2팔당기기", 1, 10000, "14어깨스트레칭2팔당기기", "ex14_04", false))
            /** idx = 60**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭3가슴펴기", 1, 10000, "14어깨스트레칭3가슴펴기", "ex14_05", false))
            /** idx = 61**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭4뒤에서양손", 1, 10000, "14어깨스트레칭4뒤에서양손", "ex14_06", false))
            /** idx = 62**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭5옆구리", 1, 10000, "14어깨스트레칭5옆구리", "ex14_07", false))
            /** idx = 63**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭5옆구리", 1, 10000, "14어깨스트레칭5옆구리", "ex14_08", false))
            /** idx = 64**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭6어깨수평", 1, 10000, "14어깨스트레칭6어깨수평", "ex14_09", false))
            /** idx = 65**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭6어깨수평", 1, 10000, "14어깨스트레칭6어깨수평", "ex14_10", false))
            /** idx = 66**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭7한쪽어깨", 1, 10000, "14어깨스트레칭7한쪽어깨", "ex14_11", false))
            /** idx = 67**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭7한쪽어깨", 1, 10000, "14어깨스트레칭7한쪽어깨", "ex14_12", false))
            /** idx = 68**/
            exerciseList.add(ExercisesData(0L, "14어깨스트레칭8양쪽어깨", 1, 10000, "14어깨스트레칭8양쪽어깨", "ex14_13", false))
            /** idx = 69**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭1힙", 1, 10000, "15하지스트레칭1힙", "ex15_01", false))
            /** idx = 70**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭1힙", 1, 10000, "15하지스트레칭1힙", "ex15_02", false))
            /** idx = 71**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭2발뒤로", 1, 10000, "15하지스트레칭2발뒤로", "ex15_03", false))
            /** idx = 72**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭3발앞으로", 1, 10000, "15하지스트레칭3발앞으로", "ex15_04", false))
            /** idx = 73**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭3발앞으로", 1, 10000, "15하지스트레칭3발앞으로", "ex15_05", false))
            /** idx = 74**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭4무릎앞으로", 1, 10000, "15하지스트레칭4무릎앞으로", "ex15_06", false))
            /** idx = 75**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭4무릎앞으로", 1, 10000, "15하지스트레칭4무릎앞으로", "ex15_07", false))
            /** idx = 76**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭5골반", 1, 10000, "15하지스트레칭5골반", "ex15_08", false))
            /** idx = 77**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭5골반", 1, 10000, "15하지스트레칭5골반", "ex15_09", false))
            /** idx = 78**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭6아킬레스건", 1, 10000, "15하지스트레칭6아킬레스건", "ex15_10", false))
            /** idx = 79**/
            exerciseList.add(ExercisesData(0L, "15하지스트레칭6아킬레스건", 1, 10000, "15하지스트레칭6아킬레스건", "ex15_11", false))
            /** idx = 80**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자1힙", 1, 10000, "16하지스트레칭의자1힙", "ex16_01", false))
            /** idx = 81**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자1힙", 1, 10000, "16하지스트레칭의자1힙", "ex16_02", false))
            /** idx = 82**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자2몸흔들며발", 1, 10000, "16하지스트레칭의자2몸흔들며발", "ex16_03", false))
            /** idx = 83**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자3발교차", 1, 10000, "16하지스트레칭의자3발교차", "ex16_04", false))
            /** idx = 84**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자3발교차", 1, 10000, "16하지스트레칭의자3발교차", "ex16_05", false))
            /** idx = 85**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자4팔내리며", 1, 10000, "16하지스트레칭의자4팔내리며", "ex16_06", false))
            /** idx = 86**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자4팔내리며", 1, 10000, "16하지스트레칭의자4팔내리며", "ex16_07", false))
            /** idx = 87**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자5발뒤로", 1, 10000, "16하지스트레칭의자5발뒤로", "ex16_08", false))
            /** idx = 88**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자5발뒤로", 1, 10000, "16하지스트레칭의자5발뒤로", "ex16_09", false))
            /** idx = 89**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자6발앞으로", 1, 10000, "16하지스트레칭의자6발앞으로", "ex16_10", false))
            /** idx = 90**/
            exerciseList.add(ExercisesData(0L, "16하지스트레칭의자6발앞으로", 1, 10000, "16하지스트레칭의자6발앞으로", "ex16_11", false))
            /** idx = 91**/
            exerciseList.add(ExercisesData(0L, "17하지근력1다리앞으로", 1, 10000, "17하지근력1다리앞으로", "ex17_01", false))
            /** idx = 92**/
            exerciseList.add(ExercisesData(0L, "17하지근력1다리앞으로", 1, 10000, "17하지근력1다리앞으로", "ex17_02", false))
            /** idx = 93**/
            exerciseList.add(ExercisesData(0L, "17하지근력2다리옆으로", 1, 10000, "17하지근력2다리옆으로", "ex17_03", false))
            /** idx = 94**/
            exerciseList.add(ExercisesData(0L, "17하지근력2다리옆으로", 1, 10000, "17하지근력2다리옆으로", "ex17_04", false))
            /** idx = 95**/
            exerciseList.add(ExercisesData(0L, "17하지근력3페달", 1, 10000, "17하지근력3페달", "ex17_05", false))
            /** idx = 96**/
            exerciseList.add(ExercisesData(0L, "17하지근력3페달", 1, 10000, "17하지근력3페달", "ex17_06", false))
            /** idx = 97**/
            exerciseList.add(ExercisesData(0L, "17하지근력4앞굽이", 1, 10000, "17하지근력4앞굽이", "ex17_07", false))
            /** idx = 98**/
            exerciseList.add(ExercisesData(0L, "17하지근력4앞굽이", 1, 10000, "17하지근력4앞굽이", "ex17_08", false))
            /** idx = 99**/
            exerciseList.add(ExercisesData(0L, "17하지근력5뒤꿈치", 1, 10000, "17하지근력5뒤꿈치", "ex17_09", false))
            /** idx = 100**/
            exerciseList.add(ExercisesData(0L, "17하지근력6앞꿈치", 1, 10000, "17하지근력6앞꿈치", "ex17_10", false))
            /** idx = 101**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자1다리앞으로", 1, 10000, "18하지근력의자1다리앞으로", "ex18_01", false))
            /** idx = 102**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자1다리앞으로", 1, 10000, "18하지근력의자1다리앞으로", "ex18_02", false))
            /** idx = 103**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자2다리옆으로", 1, 10000, "18하지근력의자2다리옆으로", "ex18_03", false))
            /** idx = 104**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자2다리옆으로", 1, 10000, "18하지근력의자2다리옆으로", "ex18_04", false))
            /** idx = 105**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자3페달돌리기", 1, 10000, "18하지근력의자3페달돌리기", "ex18_05", false))
            /** idx = 106**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자3페달돌리기", 1, 10000, "18하지근력의자3페달돌리기", "ex18_06", false))
            /** idx = 107**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자4앞굽이", 1, 10000, "18하지근력의자4앞굽이", "ex18_07", false))
            /** idx = 108**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자4앞굽이", 1, 10000, "18하지근력의자4앞굽이", "ex18_08", false))
            /** idx = 109**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자5뒤꿈치", 1, 10000, "18하지근력의자5뒤꿈치", "ex18_09", false))
            /** idx = 110**/
            exerciseList.add(ExercisesData(0L, "18하지근력의자5앞꿈치", 1, 10000, "18하지근력의자5앞꿈치", "ex18_10", false))
            /** idx = 111**/
            exerciseList.add(ExercisesData(0L, "19하교차1허리굴곡근", 1, 10000, "19하교차1허리굴곡근", "ex19_01", false))
            /** idx = 112**/
            exerciseList.add(ExercisesData(0L, "19하교차2허리신전근", 1, 10000, "19하교차2허리신전근", "ex19_02", false))
            /** idx = 113**/
            exerciseList.add(ExercisesData(0L, "19하교차2허리신전근", 1, 1000, "19하교차2허리신전근", "ex19_03", false))
            /** idx = 114**/
            exerciseList.add(ExercisesData(0L, "19하교차3대퇴굴곡근", 1, 1000, "19하교차3대퇴굴곡근", "ex19_04", false))
            /** idx = 115**/
            exerciseList.add(ExercisesData(0L, "19하교차4대퇴신전근", 1, 1000, "19하교차4대퇴신전근", "ex19_05", false))

            exerciseList.forEachIndexed { index, exercisesData ->
                AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
            }

            /** 리스트 세부 운동 추가 부분 **/
            var exerciseItemList:ArrayList<HealthList_ItemsData> = ArrayList<HealthList_ItemsData>()
            /** HealthList_ItemData(자동증가, list idx, 운동 idx, 운동 반복횟수, 운동 시간) **/
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 1, 1, 1000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 2, 1, 1000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 3, 1, 1000))

            exerciseItemList.add(HealthList_ItemsData(0L, 2, 4, 1, 1000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 5, 1, 1000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 6, 1, 1000))

            prefUtils.setBoolean("default_data", true)
        }



//
//        var exercisesData : ExercisesData = ExercisesData( 0L, "테스트 운동1", 3, 30, "운동 설명 입니다.", "")
//        AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
    }
}