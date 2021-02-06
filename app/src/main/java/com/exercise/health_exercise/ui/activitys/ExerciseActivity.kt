package com.exercise.health_exercise.ui.activitys

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.annotation.MainThread
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
import com.exercise.health_exercise.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseActivity :BaseActivity(){
    val handler:Handler by lazy {
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg:Message){
                if(msg.what == 0) {
                    if (!isPause) {
                        if(isReady) {
                            runOnUiThread {
                                //카운트 다운 소리 시작
                                media = MediaPlayer.create(this@ExerciseActivity, R.raw.countdown)
                                media!!.start()

                                playCount -= 1
                                Log.d("kamuel", "playCount ::: $playCount")
                                tvExercise_Count.text = "$playCount sec"
                                pbExercise_PlayTime.progress = playCount.toInt()
                                if(playCount == 0) {
                                    isReady = false
                                    playCount = 0

                                    pbExercise_PlayTime.max = maxCount.toInt()
                                }
                                handler.sendEmptyMessageDelayed(0, 1000)
                            }
                        } else {
                            runOnUiThread {
                                playCount += 1000

                                pbExercise_PlayTime.progress = playCount.toInt()

                                var timeCount : Int = playCount / exercisePlayTime
                                timeCount = exerciseCount - timeCount

                                if(preTimeCount == 0 || timeCount != preTimeCount) {
                                    preTimeCount = timeCount
                                    media = MediaPlayer.create(this@ExerciseActivity, R.raw.exercisecount)
                                    media!!.start()
                                    tvExercise_Count.text = "$timeCount ea"
                                }

                                if(maxCount == playCount)
                                    handler.sendEmptyMessage(1)
                                else
                                    handler.sendEmptyMessageDelayed(0, 1000)
                            }

                        }

                    }
                } else if(msg.what == 1){
                    /** next 운동
                     * 다음 운동이 없을 경우 종료 **/

                    isPause = true
                    isReady = true

                    if(ArrayUtils().hasValue(exerciseList)){
                        currentPos ++
                        setExerciseInfo(currentPos)

                        isPause = false
                        handler.sendEmptyMessageDelayed(0, 1000)
                    }


                }
            }
        }
    }

    var media : MediaPlayer ? = null

    var maxCount : Int = 0
    var playCount : Int = 10
    var isPause : Boolean = true
    var isReady : Boolean = true

    var exerciseCount : Int = 0
    var exercisePlayTime : Int = 0
    var idx:Long = 0
    var currentPos : Int = 0
    var preTimeCount : Int = 0

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

        ivExercise_Pre.setOnClickListener {
            if(currentPos > 0) {
                currentPos -= 1

                if (currentPos < 0)
                    currentPos = 0

                isPause = true
                ivExercise_Play.setImageResource(R.drawable.ic_play)

                setExerciseInfo(currentPos)

                /** currentPos 가 0이 되면 Pre 버튼 회색 처리 해야 할 듯 **/
            }
        }

        ivExercise_Next.setOnClickListener {
            if(currentPos < exerciseList!!.size-1){
                currentPos += 1
                isPause = true
                ivExercise_Play.setImageResource(R.drawable.ic_play)
                setExerciseInfo(currentPos)
            }
        }

        ivExercise_Play.setOnClickListener {
            if(!isPause){
                ivExercise_Play.setImageResource(R.drawable.ic_play)
            } else {
                ivExercise_Play.setImageResource(R.drawable.ic_pause)
                handler.sendEmptyMessage(0)
            }

            isPause = !isPause
        }
    }

    fun setExerciseInfo(position:Int){
        /** 운동 이미지 바인딩 **/
        var itemData : HealthList_ItemJoinData = exerciseList!!.get(position)
        ViewUtils.loadGifImage(itemData.health_image_url, null).into(ivExercise_Image)

        pbExercise_PlayTime.progress = 0
        playCount = 10

        exerciseCount = itemData.custom_count
        exercisePlayTime = itemData.custom_play_time.toInt()
        maxCount = itemData.custom_count * itemData.custom_play_time.toInt()
    }
}