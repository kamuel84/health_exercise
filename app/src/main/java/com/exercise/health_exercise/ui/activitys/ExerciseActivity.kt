package com.exercise.health_exercise.ui.activitys

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseDetailAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemData
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.ui.exercise_detail.ExerciseDetailFragment
import com.exercise.health_exercise.ui.exercise_detail.ExerciseItemDetailFragment
import com.exercise.health_exercise.utils.ArrayUtils
import com.exercise.health_exercise.utils.DateUtils
import com.exercise.health_exercise.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseActivity :BaseActivity(){
    var exerciseMedia : MediaPlayer ?= null
    val handler:Handler by lazy {
        object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg:Message){
                if(msg.what == 0) {
                    if (!isPause) {
                        if(isReady) {
                            runOnUiThread {
                                //카운트 다운 소리 시작
                                var media = MediaPlayer.create(this@ExerciseActivity, R.raw.countdown)
                                media!!.start()

                                playCount += 1
                                readyCount -= 1
                                tvExercise_Time.visibility = View.GONE
                                tvExercise_Count.text = "Ready Count : $readyCount sec"
                                tvExercise_Count.setTextColor(ContextCompat.getColor(this@ExerciseActivity, R.color.color_70AB34))
                                if(playCount == 3) {
                                    isReady = false
                                    playCount = 0

                                    pbExercise_PlayTime.max = maxCount.toInt()
                                }
                                handler.sendEmptyMessageDelayed(0, 1000)
                            }
                        } else {
                            runOnUiThread {
                                playCount += 1000

                                tvExercise_Time.visibility = View.VISIBLE
                                tvExercise_Time.text = (playCount / 1000).toString() + " sec"

                                pbExercise_PlayTime.progress = playCount.toInt()

                                var timeCount : Int = playCount / exercisePlayTime
                                timeCount = exerciseCount - timeCount

                                if(preTimeCount == 0 || timeCount != preTimeCount) {
                                    preTimeCount = timeCount
                                    if(exerciseMedia != null && exerciseMedia!!.isPlaying){
                                        exerciseMedia!!.stop()
                                        exerciseMedia = null
                                    }

                                    var media = MediaPlayer.create(this@ExerciseActivity, R.raw.exercisecount)
                                    media!!.start()
                                    tvExercise_Count.text = "Repeat Count : $timeCount ea"
                                    tvExercise_Count.setTextColor(ContextCompat.getColor(this@ExerciseActivity, R.color.font_color_black))
                                }

                                if(maxCount == playCount) {
                                    var playItemData: PlayExerciseItemData = PlayExerciseItemData(
                                            0L,
                                            playId,
                                            idx,
                                            exerciseItemID,
                                            playCount.toLong(),
                                            true
                                    )
                                    viewModel.insertPlayItemExercise(playItemData)

                                    if(!isReady) {
                                        handler.sendEmptyMessage(1)
                                    }
                                } else {

                                    if(exerciseMedia == null) {
                                        exerciseMedia = MediaPlayer.create(this@ExerciseActivity, R.raw.countdown_exercise)
                                        exerciseMedia!!.start()
                                        exerciseMedia!!.isLooping = true
                                    }

                                    var playItemData : PlayExerciseItemData = PlayExerciseItemData(
                                            0L,
                                            playId,
                                            idx,
                                            exerciseItemID,
                                            playCount.toLong(),
                                            false
                                    )
                                    viewModel.insertPlayItemExercise(playItemData)
                                    if(!isReady)
                                        handler.sendEmptyMessageDelayed(0, 1000)
                                }
                            }

                        }

                    } else {
                        if (exerciseMedia != null) {
                            exerciseMedia!!.stop()
                            exerciseMedia = null
                        }
                    }
                } else if(msg.what == 1) {
                    /** next 운동
                     * 다음 운동이 없을 경우 종료 **/

                    isPause = true
                    isReady = false

                    if(ArrayUtils().hasValue(exerciseList)){
                        currentPos ++

                        if(currentPos < exerciseList!!.size) {
                            setExerciseInfo(currentPos)

                            isPause = false
                            isReady = true

                            handler.sendEmptyMessageDelayed(0, 1000)
                        } else {
                            finish()
                        }
                    }
                }
            }
        }
    }

    val defaultPlayCount:Int = 0
    var defaultReadyCount:Int = 4
    var media : MediaPlayer ? = null

    var maxCount : Int = 0
    var playCount : Int = defaultPlayCount
    var readyCount : Int = defaultReadyCount
    var isPause : Boolean = true
    var isReady : Boolean = true

    var exerciseCount : Int = 0
    var exercisePlayTime : Int = 0
    var idx:Long = 0
    var currentPos : Int = 0
    var preTimeCount : Int = 0
    var playId:Long = -1
    var exerciseItemID:Long = -1

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

        ivExercise_Info.setOnClickListener {
            var intent:Intent = Intent(this, ExerciseItemDetailActivity::class.java)
            intent.putExtra(AppContents.INTENT_DATA_LIST_INDEX, idx)
            intent.putExtra(AppContents.INTENT_DATA_LIST_POSITION, currentPos)

            startActivity(intent)
        }

        ivExercise_Pre.setOnClickListener {
            if(currentPos > 0) {
                currentPos -= 1

                if (currentPos < 0)
                    currentPos = 0

                isPause = true
                isReady = true
                ivExercise_Play.setImageResource(R.drawable.ic_play)

                setExerciseInfo(currentPos)

                tvExercise_Time.visibility = View.GONE
                tvExercise_Count.text = "Ready Count : $readyCount sec"
                tvExercise_Count.setTextColor(ContextCompat.getColor(this@ExerciseActivity, R.color.color_70AB34))
            }
        }

        ivExercise_Next.setOnClickListener {
            if(currentPos < exerciseList!!.size-1){

                isPause = true
                isReady = true

                currentPos ++
                ivExercise_Play.setImageResource(R.drawable.ic_play)

                setExerciseInfo(currentPos)

                tvExercise_Time.visibility = View.GONE
                tvExercise_Count.text = "Ready Count : $readyCount sec"
                tvExercise_Count.setTextColor(ContextCompat.getColor(this@ExerciseActivity, R.color.color_70AB34))
            }
        }

        ivExercise_Play.setOnClickListener {
            if(!isPause){
                ivExercise_Play.setImageResource(R.drawable.ic_play)
            } else {
                ivExercise_Play.setImageResource(R.drawable.ic_pause)

                if(viewModel.playExercisesRepository.getItemList(DateUtils.getNowDate("yyyyMMdd")) == 0){
                    var playExerciseData : PlayExerciseData = PlayExerciseData(0L, DateUtils.getNowDate("yyyyMMdd"))
                    playId = viewModel.playExercisesRepository.insertPlayData(playExerciseData)
                }

                handler.sendEmptyMessage(0)
            }

            isPause = !isPause
        }

        tvExercise_Count.text = "$playCount sec"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        isPause = true

        if(exerciseMedia != null){
            exerciseMedia!!.stop()
            exerciseMedia = null
        }
    }

    override fun onPause() {
        super.onPause()

        ivExercise_Play.setImageResource(R.drawable.ic_play)
        isPause = true

        if(exerciseMedia != null){
            exerciseMedia!!.stop()
            exerciseMedia = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(exerciseMedia != null){
            exerciseMedia!!.stop()
        }
    }

    fun setExerciseInfo(position:Int){
        /** 운동 이미지 바인딩 **/
        if(position < exerciseList!!.size) {
            var itemData: HealthList_ItemJoinData = exerciseList!!.get(position)
            ViewUtils.loadGifImage(itemData.health_image_url, null).into(ivExercise_Image)

            exerciseItemID = itemData.health_index

            pbExercise_PlayTime.progress = 0
            pbExercise_PlayTime.max = 0
            playCount = defaultPlayCount
            readyCount = defaultReadyCount

            tvExercise_Title.text = itemData.health_title
            exerciseCount = itemData.custom_count
            exercisePlayTime = itemData.custom_play_time.toInt()
            maxCount = itemData.custom_count * itemData.custom_play_time.toInt()
        } else {
            finish()
        }
    }
}