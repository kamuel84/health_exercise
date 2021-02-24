package com.exercise.health_exercise.ui.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.ui.custom_title.CustomTitleFragment
import com.exercise.health_exercise.ui.exercise.ExerciseFragment
import com.exercise.health_exercise.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_list_base.*

class ListAddActivity : BaseActivity(), View.OnClickListener {
    var step:Int = 0
    var selectList : HashMap<Long, ExercisesData> = HashMap<Long, ExercisesData>()
    var title : String = ""
    var isEdit : Boolean = false
    var selectIndex : Long = 0
    var listType : String = "C"

    val listViewModel : HomeViewModel by lazy {
        ViewModelProvider(this, HomeViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(HomeViewModel::class.java)
    }

    val customListItemViewModel : CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(CustomExerciseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_base)

        var exerciseFrameLayout:ExerciseFragment = ExerciseFragment()
        var bundle:Bundle = Bundle()
        if(intent.hasExtra(AppContents.INTENT_DATA_EDIT_MODE)) {
            isEdit = intent.getBooleanExtra(AppContents.INTENT_DATA_EDIT_MODE, false)
            bundle.putBoolean(AppContents.INTENT_DATA_EDIT_MODE, isEdit)
        }

        if(intent.hasExtra(AppContents.INTENT_DATA_LIST_INDEX)) {
            selectIndex = intent.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0)
            bundle.putLong(AppContents.INTENT_DATA_LIST_INDEX, selectIndex)
        }

        exerciseFrameLayout.arguments = bundle
        pushFragment(R.id.layout_fragment, exerciseFrameLayout)

        btn_Next.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == btn_Next){
            if(step == 1){
                /** 운동리스트의 세부 셋팅으로 이동 **/
                if(selectList != null && selectList!!.size > 0) {
                    var selectExercisList: ArrayList<ExercisesData> = ArrayList<ExercisesData>()
                    selectList.keys.forEachIndexed { index, key ->

                        if (selectList.get(key) != null)
                            selectExercisList.add(selectList.get(key)!!)
                    }

                    var nextFragment: CustomExerciseFragment =
                        CustomExerciseFragment.newInstance(selectExercisList)
                    pushFragment(R.id.layout_fragment, nextFragment)
                } else {
                    Toast.makeText(this, "운동을 1개 이상 선택 해주세요.", Toast.LENGTH_SHORT).show()
                }

            } else if(step == 2){
                /** 운동리스트의 Title을 입력 하는 화면으로 이동 **/
                var nextFragment:CustomTitleFragment = CustomTitleFragment()
                var bundle:Bundle = Bundle()
                bundle.putBoolean(AppContents.INTENT_DATA_EDIT_MODE, isEdit)
                bundle.putLong(AppContents.INTENT_DATA_LIST_INDEX, selectIndex)

                nextFragment.arguments = bundle
                pushFragment(R.id.layout_fragment, nextFragment)

                btn_Next.text = getString(R.string.btn_confirm)
            } else if(step == 3){
                var listData : HealthListData = HealthListData(selectIndex, title, listType)
                var healthList : ArrayList<ExercisesData> = ArrayList<ExercisesData>()

                selectList.keys.forEachIndexed { index, key ->
                    var exerciseData : ExercisesData = selectList.get(key)!!
                    healthList.add(exerciseData)
                }

                var intentData : Intent = Intent()
                intentData.putExtra(AppContents.RESULT_DATA_LISTDATA, listData)
                intentData.putExtra(AppContents.RESULT_DATA_HEALTHLIST, healthList)
                intentData.putExtra(AppContents.INTENT_DATA_EDIT_MODE, isEdit)
                intentData.putExtra(AppContents.INTENT_DATA_LIST_INDEX, selectIndex)
                setResult(RESULT_OK, intentData)
                finish()
            }
        }
    }
}