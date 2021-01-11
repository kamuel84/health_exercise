package com.exercise.health_exercise.ui.custom_title

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import kotlinx.android.synthetic.main.fragment_custom_title.*

class CustomTitleFragment : BaseFragment(){
    companion object{
        @JvmStatic
        fun newInstance():CustomTitleFragment{
            var fragment : CustomTitleFragment = CustomTitleFragment()

            return fragment
        }
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
                    (ExerciseApplication.currentActivity as ListAddActivity).title = s.toString()
                }
            }

        })

        if (ExerciseApplication.currentActivity is ListAddActivity) {
            (ExerciseApplication.currentActivity as ListAddActivity).step = 3
        }
    }
}