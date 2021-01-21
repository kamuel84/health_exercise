package com.exercise.health_exercise.ui.activitys

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseDetailAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.utils.ArrayUtils
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseActivity :BaseActivity(){
    val handler:Handler by lazy {
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg:Message){
                if(msg.what == 0) {
                    if (!isPause) {
                        if(isReady) {
                            playCount -= 1000

                            if(playCount == 0) {

                                isReady = false
                                playCount = 0

                                handler.sendEmptyMessageDelayed(0, 1000)
                            }

                        } else {
                            playCount += 1000

                            if(maxCount == playCount)
                                handler.sendEmptyMessage(1)

                        }

                    }
                } else if(msg.what == 1){
                    /** next 운동
                     * 다음 운동이 없을 경우 종료 **/

                    isReady = true

                    if(ArrayUtils().hasValue(exerciseList)){
                        currentPos ++
                        setExerciseInfo(currentPos)
                    }


                }
            }
        }
    }

    var maxCount : Int = 0
    var playCount : Int = 10000
    var isPause : Boolean = false
    var isReady : Boolean = true
    var idx:Long = 0
    var currentPos : Int = 0

    val viewModel: CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            CustomExerciseViewModel::class.java)
    }
    var exerciseList:List<HealthList_ItemJoinData> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        ExerciseApplication.currentActivity = this
        if(intent != null){
            idx = intent.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0)
        }

        viewModel.getCustomAllList(idx)?.observe(this, Observer {
            exerciseList = it
            setExerciseInfo(currentPos)
        })

    }

    fun setExerciseInfo(position:Int){
        /** 운동 이미지 바인딩 **/
    }
}