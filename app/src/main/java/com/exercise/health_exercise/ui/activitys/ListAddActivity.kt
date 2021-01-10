package com.exercise.health_exercise.ui.activitys

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseFragment
import com.exercise.health_exercise.ui.exercise.ExerciseFragment
import kotlinx.android.synthetic.main.activity_list_base.*

class ListAddActivity : BaseActivity(), View.OnClickListener {
    var step:Int = 0
    var selectList : HashMap<Int, ExercisesData> = HashMap<Int, ExercisesData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_base)

        var exerciseFrameLayout:ExerciseFragment = ExerciseFragment()
        pushFragment(R.id.layout_fragment, exerciseFrameLayout)

        btn_Next.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == btn_Next){
            if(step == 1){
                /** 운동리스트의 세부 셋팅으로 이동 **/
                var selectExercisList:ArrayList<ExercisesData> = ArrayList<ExercisesData>()
                selectList.keys.forEachIndexed { index, key ->

                    if(selectList.get(key) != null)
                        selectExercisList.add(selectList.get(key)!!)
                }

                var nextFragment:CustomExerciseFragment = CustomExerciseFragment.newInstance(selectExercisList)
                pushFragment(R.id.layout_fragment, nextFragment)

            } else if(step == 2){
                /** 운동리스트의 Title을 입력 하는 화면으로 이동 **/
            }
        }
    }
}