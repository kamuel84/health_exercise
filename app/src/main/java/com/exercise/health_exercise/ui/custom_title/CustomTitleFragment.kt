package com.exercise.health_exercise.ui.custom_title

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.HealthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import kotlinx.android.synthetic.main.fragment_custom_title.*
import kotlinx.android.synthetic.main.fragment_home.*

class CustomTitleFragment : BaseFragment(){
    companion object{
        @JvmStatic
        fun newInstance():CustomTitleFragment{
            var fragment : CustomTitleFragment = CustomTitleFragment()

            return fragment
        }
    }

    var isEditMode = false
    var selectIndex:Long = -1L

    val customExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(CustomExerciseViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView : View = inflater.inflate(R.layout.fragment_custom_title, container, false)

        if(arguments != null ) {
            isEditMode = requireArguments().getBoolean(AppContents.INTENT_DATA_EDIT_MODE, false)
            selectIndex = requireArguments().getLong(AppContents.INTENT_DATA_LIST_INDEX, -1)
        }

        if(isEditMode) {
            customExerciseViewModel.getTitleList(selectIndex)
                ?.observe(viewLifecycleOwner, Observer {
                    tvCustom_Title.setText(it.get(0).title)
                    (ExerciseApplication.currentActivity as ListAddActivity).listType =
                        it.get(0).list_type
                })
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvCustom_Title.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (ExerciseApplication.currentActivity is ListAddActivity) {
                    if(TextUtils.isEmpty(s.toString())) {
                        (ExerciseApplication.currentActivity as ListAddActivity).setButtonEnable(false)
                    } else {
                        (ExerciseApplication.currentActivity as ListAddActivity).setButtonEnable(true)
                        (ExerciseApplication.currentActivity as ListAddActivity).title = s.toString()
                    }
                }
            }

        })

        if (ExerciseApplication.currentActivity is ListAddActivity) {
            (ExerciseApplication.currentActivity as ListAddActivity).setButtonEnable(false)
            (ExerciseApplication.currentActivity as ListAddActivity).step = 3
        }
    }
}