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
            healthList.add(HealthListData(0L, "1 [시야안정]-머리눈-[GSHE]", "D"))
            /** idx = 2**/
            healthList.add(HealthListData(0L, "2 [시야안정]-눈-[GSEM]", "D"))
            /** idx = 3**/
            healthList.add(HealthListData(0L, "3 [시야안정]-보행-[GSHV]", "D"))
            /** idx = 4**/
            healthList.add(HealthListData(0L, "4 [자세안정]-자세전략-발목-[PSPA]", "D"))
            /** idx = 5**/
            healthList.add(HealthListData(0L, "5 [자세안정]-자세전략-고관절-다리고정-[PSPH-F]", "D"))
            /** idx = 6**/
            healthList.add(HealthListData(0L, "6 [자세안정]-자세전략-고관절-다리이동-[PSPH-M]", "D"))
            /** idx = 7**/
            healthList.add(HealthListData(0L, "7 [자세안정]-자세전략-발디딤-[PSPS]", "D"))
            /** idx = 8**/
            healthList.add(HealthListData(0L, "8 [습관화]-앉아-[HSI]", "D"))
            /** idx = 9**/
            healthList.add(HealthListData(0L, "9 [습관화]-서서-[HST]", "D"))
            /** idx = 10**/
            healthList.add(HealthListData(0L, "10 [워밍업-쿨다운]-[WC]", "D"))
            /** idx = 11**/
            healthList.add(HealthListData(0L, "11 [상체]-스트레칭-[US]", "D"))
            /** idx = 12**/
            healthList.add(HealthListData(0L, "12 [상체]-크로스-[UC]", "D"))
            /** idx = 13**/
            healthList.add(HealthListData(0L, "13 [하체]-스트레칭-의자-[LSC]", "D"))
            /** idx = 14**/
            healthList.add(HealthListData(0L, "14 [하체]-스트레칭-[LLS]", "D"))
            /** idx = 15**/
            healthList.add(HealthListData(0L, "15 [하체]-강화-의자-[LLSNC]", "D"))
            /** idx = 16**/
            healthList.add(HealthListData(0L, "16 [하체]-강화-[LLSN]", "D"))
            /** idx = 17**/
            healthList.add(HealthListData(0L, "17 [하체]-크로스-[LC]", "D"))

            //2022.02.04_han_나의운동 디폴트 리스트 -------------------------------------------------------------
            /** idx = 18**/
            healthList.add(HealthListData(0L, "1 어지럼 운동 초급A (앉아+기립)", "CD"))
            /** idx = 19**/
            healthList.add(HealthListData(0L, "2 어지럼 운동 초급B (앉아+기립)", "CD"))
            /** idx = 20**/
            healthList.add(HealthListData(0L, "3 어지럼 운동 중급A (앉아+기립)", "CD"))
            /** idx = 21**/
            healthList.add(HealthListData(0L, "4 어지럼 운동 중급B (기립)", "CD"))
            /** idx = 22**/
            healthList.add(HealthListData(0L, "5 어지럼 운동 고급 A (기립)", "CD"))
            /** idx = 23**/
            healthList.add(HealthListData(0L, "6 어지럼 운동 고급 B (기립)", "CD"))
            /** idx = 24**/
            healthList.add(HealthListData(0L, "7 이석증 잔여 어지럼 운동 초급", "CD"))
            /** idx = 25**/
            healthList.add(HealthListData(0L, "8 이석증 잔여 어지럼 운동 고급", "CD"))
            /** idx = 26**/
            healthList.add(HealthListData(0L, "9 복잡한 배경 VOR운동 (의자사용)", "CD"))
            /** idx = 27**/
            healthList.add(HealthListData(0L, "10 상하체 스트레칭 초급(기립,의자 사용)", "CD"))
            /** idx = 28**/
            healthList.add(HealthListData(0L, "11 일자목 운동 1(기립)", "CD"))
            /** idx = 29**/
            healthList.add(HealthListData(0L, "12 일자목 운동 2(기립)", "CD"))
            /** idx = 30**/
            healthList.add(HealthListData(0L, "13 상하체 스트레칭 중급(기립, 의자 사용)", "CD"))
            /** idx = 31**/
            healthList.add(HealthListData(0L, "14 하체 스트레칭과 근력 중급(기립,의자 사용)", "CD"))
            /** idx = 32**/
            healthList.add(HealthListData(0L, "15 하체 스트레칭 중급 (의자사용)", "CD"))
            /** idx = 33**/
            healthList.add(HealthListData(0L, "16 하체 스트레칭 고급 (기립)", "CD"))
            /** idx = 34**/
            healthList.add(HealthListData(0L, "17 하체 근력 중급 (의자사용)", "CD"))
            /** idx = 35**/
            healthList.add(HealthListData(0L, "18 요통 운동 (의자사용)", "CD"))
            //2022.02.04_han_나의운동 디폴트 리스트 -------------------------------------------------------------

            healthList.forEachIndexed { index, healthListData ->
                AppDataBase.getInstance(this, callback).healthListDao().insert(healthListData)
            }

            /** 운동 등록 부분 **/
            var exerciseList: ArrayList<ExercisesData> = ArrayList<ExercisesData>()
            /** ExercisesData(자동증가, 운동 Title, 운동 반복횟수, 운동 시간) **/
            /** 1 **/
            /** idx = 1**/
            exerciseList.add(ExercisesData(0L, "[GSHE-H]-시야안정머리눈-수평", 1, 30000, resources.getString(R.string.gshe_h), "ex01_01", -1, false))
            /** idx = 2**/
            exerciseList.add(ExercisesData(0L, "[GSHE-V]-시야안정머리눈-수직", 1, 30000, resources.getString(R.string.gshe_v), "ex01_02", -1, false))
            /** idx = 3**/
            exerciseList.add(ExercisesData(0L, "[GSHE-C]-시야안정머리눈-원", 1, 30000, resources.getString(R.string.gshe_c), "ex01_03", -1, false))
            /** idx = 4**/
            exerciseList.add(ExercisesData(0L, "[GSHE-C]-시야안정머리-움직이는물체따라보기", 1, 30000, resources.getString(R.string.gshe_ht), "ex01_04", -1, false))
            /** 2 **/
            /** idx = 5**/
            exerciseList.add(ExercisesData(0L, "[GSEM-P]-시야안정눈이동-원활추척", 1, 30000, resources.getString(R.string.gsem_p), "ex02_01", -1, false))
            /** idx = 6**/
            exerciseList.add(ExercisesData(0L, "[GSEM-S]-시야안정눈이동-충동", 1, 30000, resources.getString(R.string.gsem_s), "ex02_02", -1, false))
            /** idx = 7**/
            exerciseList.add(ExercisesData(0L, "[GSEM-I]-시야안정눈이동-마인드컨트롤", 1, 30000, resources.getString(R.string.gshe_h), "ex02_03", -1, false))
            /** 3 **/
            /** idx = 8**/
            exerciseList.add(ExercisesData(0L, "[GSHEW-FH]-시야안정-머리-보행중 머리-눈 운동, 수평, 시선고정", 1, 30000, resources.getString(R.string.gshew_fh), "ex03_01", -1, false))
            /** idx = 9**/
            exerciseList.add(ExercisesData(0L, "[GSHEW-FV]-시야안정-머리-보행중 머리-눈 운동, 수직, 시선고정", 1, 30000, resources.getString(R.string.gshew_fv), "ex03_02", -1, false))
            /** idx = 10**/
            exerciseList.add(ExercisesData(0L, "[GSHEW-MH]-시야안정-머리-보행중 머리-눈 운동, 수평, 시선 움직임", 1, 30000, resources.getString(R.string.gshew_mh), "ex03_03", -1, false))
            /** idx = 11**/
            exerciseList.add(ExercisesData(0L, "[GSHEW-MV]-시야안정-머리-보행중 머리-눈 운동, 수직, 시선 움직임", 1, 30000, resources.getString(R.string.gshew_mv), "ex03_04", -1, false))
            /** 4 **/
            /** idx = 12**/
            exerciseList.add(ExercisesData(0L, "[PSPA-OS]-자세안정-자세전략-발목-앞 뒤 흔들기", 1, 30000, resources.getString(R.string.pspa_os), "ex04_01", -1, false))
            /** idx = 13**/
            exerciseList.add(ExercisesData(0L, "[PSPA-SS]-자세안정-자세전략-발목-좌 우 흔들기", 1, 30000, resources.getString(R.string.pspa_ss), "ex04_02", -1, false))
            /** idx = 14**/
            exerciseList.add(ExercisesData(0L, "[PSPA-PH]-자세안정-자세전략-발목-발 앞꿈치 벌리기", 1, 30000, resources.getString(R.string.pspa_ph), "ex04_03", -1, false))
            /** idx = 15**/
            exerciseList.add(ExercisesData(0L, "[PSPA-PT]-자세안정-자세전략-발목-발 뒤꿈치 벌리기", 1, 30000, resources.getString(R.string.pspa_pt), "ex04_04", -1, false))
            /** idx = 16**/
            exerciseList.add(ExercisesData(0L, "[PSPA-SK]-자세안정-자세전략-발목-스키타기", 1, 30000, resources.getString(R.string.pspa_sk), "ex04_05", -1, false))
            /** idx = 17**/
            exerciseList.add(ExercisesData(0L, "[PSPA-360]-자세안정-자세전략-발목-360도 회전", 1, 30000, resources.getString(R.string.pspa_360), "ex04_06", -1, false))
            /** 5 **/
            /** idx = 18**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-HOI]-자세안정-자세전략-고관절-다리고정-팔과 같이 몸통 앞뒤로 흔들기", 1, 30000, resources.getString(R.string.psph_f_hoi), "ex05_01", -1, false))
            /** idx = 19**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-HOO]-자세안정-자세전략-고관절-다리고정-팔과 반대로, 몸통 앞뒤로 흔들기", 1, 30000, resources.getString(R.string.psph_f_hoo), "ex05_02", -1, false))
            /** idx = 20**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-SS]-자세안정-자세전략-고관절-다리고정-팔과 같이,몸통 좌우 흔들기", 1, 30000, resources.getString(R.string.psph_f_ss), "ex05_03", -1, false))
            /** idx = 21**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-SC]-자세안정-자세전략-고관절-다리고정-팔과 반대로, 몸통 좌우 흔들기", 1, 30000, resources.getString(R.string.psph_f_sc), "ex05_04", -1, false))
            /** idx = 22**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-WR]-자세안정-자세전략-고관절-다리고정-허리로 원 그리기", 1, 30000, resources.getString(R.string.psph_f_wr), "ex05_05", -1, false))
            /** idx = 23**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-TR]-자세안정-자세전략-고관절-다리고정-상체 원 그리기", 1, 30000, resources.getString(R.string.psph_f_tr), "ex05_06", -1, false))
            /** idx = 24**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-AF]-자세안정-자세전략-고관절-다리고정-앞으로 팔 뻗기", 1, 30000, resources.getString(R.string.psph_f_af), "ex05_07", -1, false))
            /** idx = 25**/
            exerciseList.add(ExercisesData(0L, "[PSPH-F-AS]-자세안정-자세전략-고관절-다리고정-옆으로 팔 뻗기", 1, 30000, resources.getString(R.string.psph_f_as), "ex05_08", -1, false))
            /** 6 **/
            /** idx = 26**/
            exerciseList.add(ExercisesData(0L, "[PSPH-M-FB]-자세안정-자세전략-고관절-다리이동-한 발 앞뒤 흔들기", 1, 30000, resources.getString(R.string.psph_m_fb), "ex06_01", -1, false))
            /** idx = 27**/
            exerciseList.add(ExercisesData(0L, "[PSPH-M-FS]-자세안정-자세전략-고관절-다리이동-몸 앞에서 한 발 좌우 흔들기", 1, 30000, resources.getString(R.string.psph_m_fs), "ex06_02", -1, false))
            /** idx = 28**/
            exerciseList.add(ExercisesData(0L, "[PSPH-M-RS-자세안정-자세전략-고관절-다리이동-몸 뒤에서 한 발 좌우 흔들기", 1, 30000, resources.getString(R.string.psph_m_rs), "ex06_03", -1, false))
            /** idx = 29**/
            exerciseList.add(ExercisesData(0L, "[PSPH-M-C]-자세안정-자세전략-고관절-다리이동-한발 수평 원 그리기", 1, 30000, resources.getString(R.string.psph_m_c), "ex06_04", -1, false))
            /** 7 **/
            /** idx = 30**/
            exerciseList.add(ExercisesData(0L, "[PSPS-FS]-자세안정-자세전략-발디딤-앞뒤 스텝", 1, 30000, resources.getString(R.string.psps_fs), "ex07_01", -1, false))
            /** idx = 31**/
            exerciseList.add(ExercisesData(0L, "[PSPS-SS]-자세안정-자세전략-발디딤-좌우 스텝", 1, 30000, resources.getString(R.string.psps_ss), "ex07_02", -1, false))
            /** idx = 32**/
            exerciseList.add(ExercisesData(0L, "[PSPS-SF]-자세안정-자세전략-발디딤-앞으로 좌우 교차 스텝", 1, 30000, resources.getString(R.string.psps_sf), "ex07_03", -1, false))
            /** idx = 33**/
            exerciseList.add(ExercisesData(0L, "[PSPS-SB]-자세안정-자세전략-발디딤-뒤로 교차 스텝", 1, 30000, resources.getString(R.string.psps_sb), "ex07_04", -1, false))
            /** idx = 34**/
            exerciseList.add(ExercisesData(0L, "[PSPS-SO]-자세안정-자세전략-발디딤-장애물 넘기 스텝", 1, 30000, resources.getString(R.string.psps_so), "ex07_05", -1, false))
            /** 8 **/
            /** idx = 35**/
            exerciseList.add(ExercisesData(0L, "[HSI-H]-습관화-앉아-머리 수평 돌리기-앉아서", 1, 30000, resources.getString(R.string.hsi_h), "ex08_01", -1, false))
            /** idx = 36**/
            exerciseList.add(ExercisesData(0L, "[HSI-V]-습관화-앉아-머리 수직 돌리기-앉아서", 1, 30000, resources.getString(R.string.hsi_v), "ex08_02", -1, false))
            /** idx = 37**/
            exerciseList.add(ExercisesData(0L, "[HSI-DR]-습관화-앉아-머리 대각선 휘두르기-우측-앉아서", 1, 30000, resources.getString(R.string.hsi_dr), "ex08_03", -1, false))
            /** idx = 38**/
            exerciseList.add(ExercisesData(0L, "[HSI-DL]-습관화-앉아-머리 대각선 휘두르기-좌측-앉아서", 1, 30000, resources.getString(R.string.hsi_dl), "ex08_04", -1, false))
            /** idx = 39**/
            exerciseList.add(ExercisesData(0L, "[HSI-B]-습관화-앉아-앉아서 몸통 회전, 시야고정(혼자)", 1, 30000, resources.getString(R.string.hsi_b), "ex08_05", -1, false))
            /** 9 **/
            /** idx = 40**/
            exerciseList.add(ExercisesData(0L, "[HST-BR]-습관화-서서-몸통 회전, 고정된 자세", 1, 30000, resources.getString(R.string.hst_br), "ex09_01", -1, false))
            /** idx = 41**/
            exerciseList.add(ExercisesData(0L, "[HST-HR]-습관화-서서-서서 목 회전", 1, 30000, resources.getString(R.string.hst_hr), "ex09_02", -1, false))
            /** idx = 42**/
            exerciseList.add(ExercisesData(0L, "[HST-HTR]-습관화-서서-목, 몸통 회전", 1, 30000, resources.getString(R.string.hst_htr), "ex09_03", -1, false))
            /** idx = 43**/
            exerciseList.add(ExercisesData(0L, "[HST-TBH]-습관화-서서-고개 돌려서, 시야고정", 1, 30000, resources.getString(R.string.hst_tbh), "ex09_04", -1, false))
            /** idx = 44**/
            exerciseList.add(ExercisesData(0L, "[HST-BCR]-습관화-서서-허리 숙여 몸통 돌리기", 1, 30000, resources.getString(R.string.hst_bcr), "ex09_05", -1, false))
            /** idx = 45**/
            exerciseList.add(ExercisesData(0L, "[HST-DHR]-습관화-서서-허리 대각선 휘두르기-우측", 1, 30000, resources.getString(R.string.hst_dhr), "ex09_06", -1, false))
            /** idx = 46**/
            exerciseList.add(ExercisesData(0L, "[HST-DHL]-습관화-서서-허리 대각선 휘두르기-좌측", 1, 30000, resources.getString(R.string.hst_dhl), "ex09_07", -1, false))
            /** idx = 47**/
            exerciseList.add(ExercisesData(0L, "[HST-S]-습관화-서서-감각의존성을 위한 습관화운동", 1, 30000, resources.getString(R.string.hst_s), "", -1, false))
            /** 10 **/
            /** idx = 48**/
            exerciseList.add(ExercisesData(0L, "[WC-WS]-워밍업쿨다운-무릎 옆으로 굽히기", 1, 30000, resources.getString(R.string.wc_ws), "ex10_01", -1, false))
            /** idx = 49**/
            exerciseList.add(ExercisesData(0L, "[WC-F]-워밍업쿨다운-폴더 동작", 1, 30000, resources.getString(R.string.wc_f), "ex10_02", -1, false))
            /** idx = 50**/
            exerciseList.add(ExercisesData(0L, "[WC-WM]-워밍업쿨다운-몸통 크게 돌리기", 1, 30000, resources.getString(R.string.wc_wm), "ex10_03", -1, false))
            /** idx = 51**/
            exerciseList.add(ExercisesData(0L, "[WC-BF]-워밍업쿨다운-허리 굽히기", 1, 30000, resources.getString(R.string.wc_bf), "ex10_04", -1, false))
            /** idx = 52**/
            exerciseList.add(ExercisesData(0L, "[WC-AL]-워밍업쿨다운-팔 다리 업", 1, 30000, resources.getString(R.string.wc_al), "ex10_05", -1, false))
            /** idx = 53**/
            exerciseList.add(ExercisesData(0L, "[WC-BT]-워밍업쿨다운-허리 트위스트", 1, 30000, resources.getString(R.string.wc_bt), "ex10_06", -1, false))
            /** 11 **/
            /** idx = 54**/
            exerciseList.add(ExercisesData(0L, "[US-SRB]-상체스트레칭-어깨 수직 돌리기-뒤로", 1, 30000, resources.getString(R.string.us_sr), "ex11_01", -1, false))
            /** idx = 55**/
            exerciseList.add(ExercisesData(0L, "[US-SRF]-상체스트레칭-어깨 수직 돌리기-앞으로", 1, 30000, resources.getString(R.string.us_sr), "ex11_02", -1, false))
            /** idx = 56**/
            exerciseList.add(ExercisesData(0L, "[US-CSSR]-상체스트레칭-팔 당기기-우측", 1, 30000, resources.getString(R.string.us_css), "ex11_03", -1, false))
            /** idx = 57**/
            exerciseList.add(ExercisesData(0L, "[US-CSSL]-상체스트레칭-팔 당기기-좌측", 1, 30000, resources.getString(R.string.us_css), "ex11_04", -1, false))
            /** idx = 58**/
            exerciseList.add(ExercisesData(0L, "[US-OC]-상체스트레칭-가슴 펴기", 1, 30000, resources.getString(R.string.us_oc), "ex11_05", -1, false))
            /** idx = 59**/
            exerciseList.add(ExercisesData(0L, "[US-CF]-상체스트레칭-뒤에서 양손잡기", 1, 30000, resources.getString(R.string.us_cf), "ex11_06", -1, false))
            /** idx = 60**/
            exerciseList.add(ExercisesData(0L, "[US-SSR]-상체스트레칭-옆구리 스트레칭-우측", 1, 30000, resources.getString(R.string.us_ss), "ex11_07", -1, false))
            /** idx = 61**/
            exerciseList.add(ExercisesData(0L, "[US-SSL]-상체스트레칭-옆구리 스트레칭-좌측", 1, 30000, resources.getString(R.string.us_ss), "ex11_08", -1, false))
            /** idx = 62**/
            exerciseList.add(ExercisesData(0L, "[US-HSRR]-상체스트레칭-어깨 수평 돌리기-우측", 1, 30000, resources.getString(R.string.us_hsr), "ex11_09", -1, false))
            /** idx = 63**/
            exerciseList.add(ExercisesData(0L, "[US-HSRL]-상체스트레칭-어깨 수평 돌리기-좌측", 1, 30000, resources.getString(R.string.us_hsr), "ex11_10", -1, false))
            /** idx = 64**/
            exerciseList.add(ExercisesData(0L, "[US-SSTR]-상체스트레칭-한쪽 어깨 트위스트-우측", 1, 30000, resources.getString(R.string.us_sst), "ex11_11", -1, false))
            /** idx = 65**/
            exerciseList.add(ExercisesData(0L, "[US-SSTL]-상체스트레칭-한쪽 어깨 트위스트-좌측", 1, 30000, resources.getString(R.string.us_sst), "ex11_12", -1, false))
            /** idx = 66**/
            exerciseList.add(ExercisesData(0L, "[US-DST]-상체스트레칭-양쪽 어깨 트위스트", 1, 30000, resources.getString(R.string.us_dst), "ex11_13", -1, false))
            /** 12 **/
            /** idx = 67**/
            exerciseList.add(ExercisesData(0L, "[UC-NFSN]-상체크로스-대흉근 이완, 목 굴곡근 강화", 1, 30000, resources.getString(R.string.uc_nfsn), "ex12_01", -1, false))
            /** idx = 68**/
            exerciseList.add(ExercisesData(0L, "[UC-NEST-Rt]-상체크로스-상 승모근 이완-우측", 1, 30000, resources.getString(R.string.uc_nest_rt), "ex12_02", -1, false))
            /** idx = 69**/
            exerciseList.add(ExercisesData(0L, "[UC-NEST-Lt]-상체크로스-상 승모근 이완-좌측", 1, 30000, resources.getString(R.string.uc_nest_lt), "ex12_03", -1, false))
            /** idx = 70**/
            exerciseList.add(ExercisesData(0L, "[UC-LCER]-상체크로스-하 승모근 강화-우측", 1, 30000, resources.getString(R.string.uc_lcer), "ex12_04", -1, false))
            /** idx = 71**/
            exerciseList.add(ExercisesData(0L, "[UC-LCEL]-상체크로스-하 승모근 강화-좌측", 1, 30000, resources.getString(R.string.uc_lcel), "ex12_05", -1, false))
            /** idx = 72**/
            exerciseList.add(ExercisesData(0L, "[UC-CFS]-상체크로스-가슴 굴곡근 이완", 1, 30000, resources.getString(R.string.uc_cfs), "ex12_06", -1, false))
            /** 13 **/
            /** idx = 73**/
            exerciseList.add(ExercisesData(0L, "[LLSC-HMR]-하체스트레칭의자-힙 스트레칭-우측", 1, 30000, resources.getString(R.string.llsc_hmr), "ex13_01", -1, false))
            /** idx = 74**/
            exerciseList.add(ExercisesData(0L, "[LLSC-HML]-하체스트레칭의자-힙 스트레칭-좌측", 1, 30000, resources.getString(R.string.llsc_hml), "ex13_02", -1, false))
            /** idx = 75**/
            exerciseList.add(ExercisesData(0L, "[LLSC-HP]-하체스트레칭의자-몸 흔들며 발 돌리기", 1, 30000, resources.getString(R.string.llsc_hp), "ex13_03", -1, false))
            /** idx = 76**/
            exerciseList.add(ExercisesData(0L, "[LLSC-CLR]-하체스트레칭의자-발 교차하여 멀리 딛기-우측", 1, 30000, resources.getString(R.string.llsc_clr), "ex13_04", -1, false))
            /** idx = 77**/
            exerciseList.add(ExercisesData(0L, "[LLSC-CLL]-하체스트레칭의자-발 교차하여 멀리 딛기-좌측", 1, 30000, resources.getString(R.string.llsc_cll), "ex13_05", -1, false))
            /** idx = 78**/
            exerciseList.add(ExercisesData(0L, "[LLSC-LLR]-하체스트레칭의자-무릎 앞으로 당기기-우측", 1, 30000, resources.getString(R.string.llsc_llr), "ex13_06", -1, false))
            /** idx = 79**/
            exerciseList.add(ExercisesData(0L, "[LLSC-LLL]-하체스트레칭의자-무릎 앞으로 당기기-좌측", 1, 30000, resources.getString(R.string.llsc_lll), "ex13_07", -1, false))
            /** idx = 80**/
            exerciseList.add(ExercisesData(0L, "[LLSC-RLR]-하체스트레칭의자-발 뒤로 당기기-우측", 1, 30000, resources.getString(R.string.llsc_rlr), "ex13_08", -1, false))
            /** idx = 81**/
            exerciseList.add(ExercisesData(0L, "[LLSC-RLL]-하체스트레칭의자-발 뒤로 당기기-좌측", 1, 30000, resources.getString(R.string.llsc_rll), "ex13_09", -1, false))
            /** idx = 82**/
            exerciseList.add(ExercisesData(0L, "[LLSC-FLR]-하체스트레칭의자-발 앞으로 당기기-우측", 1, 30000, resources.getString(R.string.llsc_flr), "ex13_10", -1, false))
            /** idx = 83**/
            exerciseList.add(ExercisesData(0L, "[LLSC-FLL]-하체스트레칭의자-발 앞으로 당기기-좌측", 1, 30000, resources.getString(R.string.llsc_fll), "ex13_11", -1, false))
            /** 14 **/
            /** idx = 84**/
            exerciseList.add(ExercisesData(0L, "[LLS-HM]-하체스트레칭-힙 스트레칭-우측", 1, 30000, resources.getString(R.string.lls_hm), "ex14_01", -1, false))
            /** idx = 85**/
            exerciseList.add(ExercisesData(0L, "[LLS-HM]-하체스트레칭-힙 스트레칭-좌측", 1, 30000, resources.getString(R.string.lls_hm), "ex14_02", -1, false))
            /** idx = 86**/
            exerciseList.add(ExercisesData(0L, "[LLS-RLL]-하체스트레칭-발 뒤로 당기기-우측", 1, 30000, resources.getString(R.string.lls_rll), "ex14_03", -1, false))
            /** idx = 87**/
            exerciseList.add(ExercisesData(0L, "[LLS-RLL]-하체스트레칭-발 뒤로 당기기-좌측", 1, 30000, resources.getString(R.string.lls_rll), "ex14_04", -1, false))
            /** idx = 88**/
            exerciseList.add(ExercisesData(0L, "[LLS-FLL]-하체스트레칭-발 앞으로 당기기-우측", 1, 30000, resources.getString(R.string.lls_fll), "ex14_05", -1, false))
            /** idx = 89**/
            exerciseList.add(ExercisesData(0L, "[LLS-FLL]-하체스트레칭-발 앞으로 당기기-좌측", 1, 30000, resources.getString(R.string.lls_fll), "ex14_06", -1, false))
            /** idx = 90**/
            exerciseList.add(ExercisesData(0L, "[LLS-FKL]-하체스트레칭-무릎 앞으로 당기기-우측", 1, 30000, resources.getString(R.string.lls_fkl), "ex14_07", -1, false))
            /** idx = 91**/
            exerciseList.add(ExercisesData(0L, "[LLS-FKL]-하체스트레칭-무릎 앞으로 당기기-좌측", 1, 30000, resources.getString(R.string.lls_fkl), "ex14_08", -1, false))
            /** idx = 92**/
            exerciseList.add(ExercisesData(0L, "[LLS-DS]-하체스트레칭-골반 스트레칭-우측", 1, 30000, resources.getString(R.string.lls_ds), "ex14_09", -1, false))
            /** idx = 93**/
            exerciseList.add(ExercisesData(0L, "[LLS-DS]-하체스트레칭-골반 스트레칭-좌측", 1, 30000, resources.getString(R.string.lls_ds), "ex14_10", -1, false))
            /** idx = 94**/
            exerciseList.add(ExercisesData(0L, "[LLS-CS]-하체스트레칭-아킬레스건 스트레칭-우측", 1, 30000, resources.getString(R.string.lls_cs), "ex14_11", -1, false))
            /** idx = 95**/
            exerciseList.add(ExercisesData(0L, "[LLS-CS]-하체스트레칭-아킬레스건 스트레칭-좌측", 1, 30000, resources.getString(R.string.lls_cs), "ex14_12", -1, false))
            /** 15 **/
            /** idx = 96**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-FL]-하체강화의자-다리 앞으로 올리기-우측", 1, 30000, resources.getString(R.string.llsnc_fl), "ex15_01", -1, false))
            /** idx = 97**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-FL]-하체강화의자-다리 앞으로 올리기-좌측", 1, 30000, resources.getString(R.string.llsnc_fl), "ex15_02", -1, false))
            /** idx = 98**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-SL]-하체강화의자-다리 옆으로 올리기-우측", 1, 30000, resources.getString(R.string.llsnc_sl), "ex15_03", -1, false))
            /** idx = 99**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-SL]-하체강화의자-다리 옆으로 올리기-좌측", 1, 30000, resources.getString(R.string.llsnc_sl), "ex15_04", -1, false))
            /** idx = 100**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-OB]-하체강화의자-페달 돌리기-우측", 1, 30000, resources.getString(R.string.llsnc_ob), "ex15_05", -1, false))
            /** idx = 101**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-OB]-하체강화의자-페달 돌리기-좌측", 1, 30000, resources.getString(R.string.llsnc_ob), "ex15_06", -1, false))
            /** idx = 102**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-L]-하체강화의자-앞 굽이 자세-우측", 1, 30000, resources.getString(R.string.llsnc_l), "ex15_07", -1, false))
            /** idx = 103**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-L]-하체강화의자-앞 굽이 자세-좌측", 1, 30000, resources.getString(R.string.llsnc_l), "ex15_08", -1, false))
            /** idx = 104**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-HL]-하체강화의자-뒤꿈치 들기", 1, 30000, resources.getString(R.string.llsnc_hl), "ex15_09", -1, false))
            /** idx = 105**/
            exerciseList.add(ExercisesData(0L, "[LLSNC-TL]-하체강화의자-앞꿈치 들기", 1, 30000, resources.getString(R.string.llsnc_tl), "ex15_10", -1, false))
            /** 16 **/
            /** idx = 106**/
            exerciseList.add(ExercisesData(0L, "[LLSN-FL]-하체강화-다리 앞으로 올리기-우측", 1, 30000, resources.getString(R.string.llsn_fl), "ex16_01", -1, false))
            /** idx = 107**/
            exerciseList.add(ExercisesData(0L, "[LLSN-FL]-하체강화-다리 앞으로 올리기-좌측", 1, 30000, resources.getString(R.string.llsn_fl), "ex16_02", -1, false))
            /** idx = 108**/
            exerciseList.add(ExercisesData(0L, "[LLSN-SL]-하체강화-다리 옆으로 올리기-우측", 1, 30000, resources.getString(R.string.llsn_sl), "ex16_03", -1, false))
            /** idx = 109**/
            exerciseList.add(ExercisesData(0L, "[LLSN-SL]-하체강화-다리 옆으로 올리기-좌측", 1, 30000, resources.getString(R.string.llsn_sl), "ex16_04", -1, false))
            /** idx = 110**/
            exerciseList.add(ExercisesData(0L, "[LLSN-OB]-하체강화-페달 돌리기-우측", 1, 30000, resources.getString(R.string.llsn_ob), "ex16_05", -1, false))
            /** idx = 111**/
            exerciseList.add(ExercisesData(0L, "[LLSN-OB]-하체강화-페달 돌리기-좌측", 1, 30000, resources.getString(R.string.llsn_ob), "ex16_06", -1, false))
            /** idx = 112**/
            exerciseList.add(ExercisesData(0L, "[LLSN-L]-하체강화-앞 굽이 자세-우측", 1, 30000, resources.getString(R.string.llsn_l), "ex16_07", -1, false))
            /** idx = 113**/
            exerciseList.add(ExercisesData(0L, "[LLSN-L]-하체강화-앞 굽이 자세-좌측", 1, 30000, resources.getString(R.string.llsn_l), "ex16_08", -1, false))
            /** idx = 114**/
            exerciseList.add(ExercisesData(0L, "[LLSN-HL]-하체강화-뒤꿈치 들기", 1, 30000, resources.getString(R.string.llsn_hl), "ex16_09", -1, false))
            /** idx = 115**/
            exerciseList.add(ExercisesData(0L, "[LLSN-TL]-하체강화-앞꿈치 들기", 1, 30000, resources.getString(R.string.llsn_tl), "ex16_10", -1, false))
            /** 17 **/
            /** idx = 116**/
            exerciseList.add(ExercisesData(0L, "[LC-BFSN]-하체크로스-허리 굴곡근 강화-우측", 1, 30000, resources.getString(R.string.lc_bfsn), "ex17_01", -1, false))
            /** idx = 117**/
            exerciseList.add(ExercisesData(0L, "[LC-BFSN]-하체크로스-허리 굴곡근 강화-좌측", 1, 30000, resources.getString(R.string.lc_bfsn), "ex17_02", -1, false))
            /** idx = 118**/
            exerciseList.add(ExercisesData(0L, "[LC-BEST]-하체크로스-허리 신전근 이완-우측", 1, 30000, resources.getString(R.string.lc_best), "ex17_03", -1, false))
            /** idx = 119**/
            exerciseList.add(ExercisesData(0L, "[LC-BEST]-하체크로스-허리 신전근 이완-좌측", 1, 30000, resources.getString(R.string.lc_best), "ex17_04", -1, false))
            /** idx = 120**/
            exerciseList.add(ExercisesData(0L, "[LC-HFST]-하체크로스-대퇴 굴곡근 이완-우측", 1, 30000, resources.getString(R.string.lc_hfst), "ex17_05", -1, false))
            /** idx = 121**/
            exerciseList.add(ExercisesData(0L, "[LC-HFST]-하체크로스-대퇴 굴곡근 이완-좌측", 1, 30000, resources.getString(R.string.lc_hfst), "ex17_06", -1, false))
            /** idx = 122**/
            exerciseList.add(ExercisesData(0L, "[LC-HESN]-하체크로스-대퇴 신전근 강화-우측", 1, 30000, resources.getString(R.string.lc_hesn), "ex17_07", -1, false))
            /** idx = 123**/
            exerciseList.add(ExercisesData(0L, "[LC-HESN]-하체크로스-대퇴 신전근 강화-좌측", 1, 30000, resources.getString(R.string.lc_hesn), "ex17_08", -1, false))

            exerciseList.forEachIndexed { index, exercisesData ->
                AppDataBase.getInstance(this, callback).exercisesDao().insert(exercisesData)
            }

            /** 리스트 세부 운동 추가 부분 **/
            var exerciseItemList: ArrayList<HealthList_ItemsData> = ArrayList<HealthList_ItemsData>()
            /** HealthList_ItemData(자동증가, list idx, 운동 idx, 운동 반복횟수, 운동 시간) **/
            /** 1 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 1, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 2, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 3, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 1, 4, 3, 1, 30000))
            /** 2 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 5, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 6, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 2, 7, 2, 1, 30000))
            /** 3 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 3, 8, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 3, 9, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 3, 10, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 3, 11, 3, 1, 30000))
            /** 4 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 12, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 13, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 14, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 15, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 16, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 4, 17, 6, 1, 30000))
            /** 5 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 18, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 19, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 20, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 21, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 22, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 23, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 24, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 5, 25, 8, 1, 30000))
            /** 6 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 6, 26, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 6, 27, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 6, 28, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 6, 29, 3, 1, 30000))
            /** 7 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 30, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 31, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 32, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 33, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 7, 34, 4, 1, 30000))
            /** 8 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 35, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 36, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 37, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 38, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 8, 39, 4, 1, 30000))
            /** 9 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 40, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 41, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 42, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 43, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 44, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 45, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 46, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 9, 47, 7, 1, 30000))
            /** 10 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 10, 48, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 10, 49, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 10, 50, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 10, 51, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 10, 52, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 10, 53, 5, 1, 30000))
            /** 11 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 54, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 55, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 56, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 57, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 58, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 59, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 60, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 61, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 62, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 63, 9, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 64, 10, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 65, 11, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 11, 66, 12, 1, 30000))
            /** 12 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 67, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 68, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 69, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 70, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 71, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 12, 72, 5, 1, 30000))
            /** 13 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 73, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 74, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 75, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 76, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 77, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 78, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 79, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 80, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 81, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 82, 9, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 13, 83, 10, 1, 30000))
            /** 14 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 84, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 85, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 86, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 87, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 88, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 89, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 90, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 91, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 92, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 93, 9, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 94, 10, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 14, 95, 11, 1, 30000))
            /** 15 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 96, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 97, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 98, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 99, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 100, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 101, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 102, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 103, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 104, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 15, 105, 9, 1, 30000))
            /** 16 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 106, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 107, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 108, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 109, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 110, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 111, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 112, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 113, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 114, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 16, 115, 9, 1, 30000))
            /** 17 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 116, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 117, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 118, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 119, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 120, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 121, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 122, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 17, 123, 7, 1, 30000))

            //2022.02.04_han_나의운동 디폴트 리스트 ---------------------------------------------------------------------------------------------
            /** 18 - 1 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 1, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 2, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 35, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 36, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 12, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 13, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 18, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 18, 20, 2, 1, 30000))
            /** 19 - 2 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 3, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 37, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 38, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 39, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 14, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 15, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 19, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 19, 21, 1, 1, 30000))
            /** 20 - 3 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 5, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 6, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 7, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 8, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 9, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 10, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 11, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 22, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 20, 23, 2, 1, 30000))
            /** 21 - 4 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 21, 40, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 21, 41, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 21, 42, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 21, 24, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 21, 25, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 21, 30, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 21, 31, 0, 1, 30000))
            /** 22 - 5 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 22, 16, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 22, 17, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 22, 26, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 22, 27, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 22, 28, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 22, 29, 2, 1, 30000))
            /** 23 - 6 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 23, 43, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 23, 44, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 23, 45, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 23, 46, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 23, 32, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 23, 33, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 23, 34, 0, 1, 30000))
            /** 24 - 7 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 24, 35, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 24, 36, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 24, 37, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 24, 38, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 24, 39, 4, 1, 30000))
            /** 25 - 8 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 40, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 41, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 42, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 43, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 44, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 45, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 46, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 25, 47, 7, 1, 30000))
            /** 26 - 9 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 26, 3, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 26, 37, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 26, 38, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 26, 38, 2, 1, 30000))
            /** 27 - 10 **/
//            exerciseItemList.add(HealthList_ItemsData(0L, 27, 47, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 27, 53, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 27, 54, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 27, 55, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 27, 56, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 27, 72, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 27, 73, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 27, 51, 1, 1, 30000))
            /** 28 - 11 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 48, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 57, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 58, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 61, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 62, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 63, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 64, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 28, 52, 1, 1, 30000))
            /** 29 - 12 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 29, 67, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 29, 68, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 29, 69, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 29, 70, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 29, 71, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 29, 72, 5, 1, 30000))
            /** 30 - 13 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 49, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 59, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 60, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 74, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 75, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 76, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 77, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 78, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 30, 50, 2, 1, 30000))
            /** 31 - 14 **/
//            exerciseItemList.add(HealthList_ItemsData(0L, 31, 47, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 31, 79, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 31, 80, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 31, 81, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 31, 82, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 31, 95, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 31, 96, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 31, 48, 1, 1, 30000))
            /** 32 - 15 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 73, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 74, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 75, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 76, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 77, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 78, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 79, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 80, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 81, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 82, 9, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 32, 83, 10, 1, 30000))
            /** 33 - 16 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 84, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 85, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 86, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 87, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 88, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 89, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 90, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 91, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 92, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 93, 9, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 94, 10, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 33, 95, 11, 1, 30000))
            /** 34 - 17 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 96, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 97, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 98, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 99, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 100, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 101, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 102, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 103, 7, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 104, 8, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 34, 105, 9, 1, 30000))
            /** 35 - 18 **/
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 116, 0, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 117, 1, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 118, 2, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 119, 3, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 120, 4, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 121, 5, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 122, 6, 1, 30000))
            exerciseItemList.add(HealthList_ItemsData(0L, 35, 123, 7, 1, 30000))
            //2022.02.04_han_나의운동 디폴트 리스트 ---------------------------------------------------------------------------------------------

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