package com.exercise.health_exercise.ui.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.view_models.AddViewModel
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.ui.custom_title.CustomTitleFragment
import com.exercise.health_exercise.ui.exercise.ExerciseFragment
import com.exercise.health_exercise.ui.fragments.GroupListFragment
import com.exercise.health_exercise.ui.fragments.SelectMenuFragment
import com.exercise.health_exercise.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_list_base.*

class ListAddActivity : BaseActivity(), View.OnClickListener {
    var title: String = ""
    var isEdit: Boolean = false
    var selectIndex: Long = 0
    var listType: String = "C"

    val listViewModel: AddViewModel by lazy {
        ViewModelProvider(this, AddViewModel.Factory(application)).get(AddViewModel::class.java)
    }

    val customListItemViewModel: CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(CustomExerciseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_base)

        if (intent.hasExtra(AppContents.INTENT_DATA_EDIT_MODE)) {
            isEdit = intent.getBooleanExtra(AppContents.INTENT_DATA_EDIT_MODE, false)
        }

        if (intent.hasExtra(AppContents.INTENT_DATA_LIST_INDEX)) {
            selectIndex = intent.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0)
        }

        var menuFragment: SelectMenuFragment = SelectMenuFragment()
        menuFragment.baseActivity = this
        pushFragment(R.id.layout_fragment, menuFragment)


        listViewModel.selectList.observe(this, Observer {
            if (it != null && it.size > 0) {
                btn_Next.visibility = View.VISIBLE
                btn_Next.setOnClickListener(this)
                listViewModel.setStep(1)
            } else {
                btn_Next.visibility = View.GONE
                listViewModel.setStep(0)
            }
        })

        listViewModel.menuTitle.observe(this, Observer {
            tvAddTitle.setText(it)
            llMenuTitle.visibility = View.VISIBLE
        })

        btn_Next.visibility = View.GONE
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {

        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onResume() {
        super.onResume()
        if(isEdit){
            listViewModel.setSelectList(selectIndex)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(currentFragment() is SelectMenuFragment){
            llMenuTitle.visibility = View.GONE
        }

    }

    fun setListFragment() {
        listViewModel.setMenuTitle(getString(R.string.select_menu_list))
        var groupListFragment: GroupListFragment = GroupListFragment.newInstance()
        groupListFragment.baseActivity = this
        pushFragment(R.id.layout_fragment, groupListFragment, "group_list")

//        btn_Next.visibility = View.VISIBLE
//        btn_Next.setOnClickListener(this)
    }

    fun setAllFragment() {
        listViewModel.setMenuTitle(getString(R.string.select_menu_all))
        var exerciseFrameLayout: ExerciseFragment = ExerciseFragment()
        var bundle: Bundle = Bundle()

        bundle.putBoolean(AppContents.INTENT_DATA_EDIT_MODE, isEdit)
        bundle.putLong(AppContents.INTENT_DATA_LIST_INDEX, selectIndex)

        exerciseFrameLayout.arguments = bundle
        exerciseFrameLayout.baseActivity = this
        pushFragment(R.id.layout_fragment, exerciseFrameLayout, "all_list")
//        btn_Next.visibility = View.VISIBLE

    }

    fun setButtonEnable(isEnabled: Boolean) {

        btn_Next.isEnabled = isEnabled
        if (isEnabled) {
            btn_Next.background = ContextCompat.getDrawable(this, R.drawable.bg_radius3_999999)
//            btn_Next.setTextColor(ContextCompat.getColor(this, R.color.font_color_black))
        } else {
            btn_Next.setBackgroundColor(ContextCompat.getColor(this, R.color.color_e5e5e5))
//            btn_Next.setTextColor(ContextCompat.getColor(this, R.color.font_color_black))
        }
    }

    override fun onClick(v: View?) {
        if (v == btn_Next) {
            if (listViewModel.getStep() == 1) {
                /** 운동리스트의 세부 셋팅으로 이동 **/
                if (listViewModel.getSelectList().size > 0) {
                    var nextFragment: CustomExerciseFragment =
                            CustomExerciseFragment.newInstance(listViewModel.getSelectList())
                    nextFragment.baseActivity = this;
                    pushFragment(R.id.layout_fragment, nextFragment, "list_detail")
                } else {
                    Toast.makeText(this, "운동을 1개 이상 선택 해주세요.", Toast.LENGTH_SHORT).show()
                }

            } else if (listViewModel.getStep() == 2) {
                /** 운동리스트의 Title을 입력 하는 화면으로 이동 **/
                var nextFragment: CustomTitleFragment = CustomTitleFragment()
                var bundle: Bundle = Bundle()
                bundle.putBoolean(AppContents.INTENT_DATA_EDIT_MODE, isEdit)
                bundle.putLong(AppContents.INTENT_DATA_LIST_INDEX, selectIndex)

                nextFragment.arguments = bundle
                nextFragment.baseActivity = this;
                pushFragment(R.id.layout_fragment, nextFragment, "list_title")

                btn_Next.text = getString(R.string.btn_confirm)
            } else if (listViewModel.getStep() == 3) {
                var listData: HealthListData = HealthListData(selectIndex, title, listType)

                var intentData: Intent = Intent()
                intentData.putExtra(AppContents.RESULT_DATA_LISTDATA, listData)
                intentData.putExtra(AppContents.RESULT_DATA_HEALTHLIST, listViewModel.getSelectList())
                intentData.putExtra(AppContents.INTENT_DATA_EDIT_MODE, isEdit)
                intentData.putExtra(AppContents.INTENT_DATA_LIST_INDEX, selectIndex)
                setResult(RESULT_OK, intentData)
                finish()
            }
        }
    }
}