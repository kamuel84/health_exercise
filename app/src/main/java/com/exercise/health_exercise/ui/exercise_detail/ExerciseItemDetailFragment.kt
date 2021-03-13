package com.exercise.health_exercise.ui.exercise_detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseDetailAdapter
import com.exercise.health_exercise.adapters.ExerciseDetailItemAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.activitys.ExerciseDetailActivity
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import kotlinx.android.synthetic.main.activity_exercise_detail.*
import kotlinx.android.synthetic.main.fragment_exercise_item_detail.*
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseItemDetailFragment:BaseFragment() {
    val viewModel:CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            CustomExerciseViewModel::class.java)
    }

    var adapter : ExerciseDetailItemAdapter ?= null

    companion object{
        @JvmStatic
        fun newInstance(index:Long, position:Int) : ExerciseItemDetailFragment {
            var fragment: ExerciseItemDetailFragment = ExerciseItemDetailFragment()
            var bundle : Bundle = Bundle()
            bundle.putLong(AppContents.INTENT_DATA_LIST_INDEX, index)
            bundle.putInt(AppContents.INTENT_DATA_LIST_POSITION, position)

            fragment.arguments = bundle

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
        var rootView:View = inflater.inflate(R.layout.fragment_exercise_item_detail, container, false)

        var idx:Long = 0
        var position:Int = 0
        if(arguments != null) {
            idx = requireArguments().getLong(AppContents.INTENT_DATA_LIST_INDEX, 0)
            position = requireArguments().getInt(AppContents.INTENT_DATA_LIST_POSITION, 0)
        }

        viewModel.getCustomAllList(idx)?.observe(viewLifecycleOwner, Observer {
            if(adapter == null){
                adapter = ExerciseDetailItemAdapter(mContext!!)
                vpItemDetail.adapter = adapter
                vpItemDetail.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)

                        if(ExerciseApplication.currentActivity is ExerciseDetailActivity){
                            (ExerciseApplication.currentActivity as ExerciseDetailActivity).toolbar.title = it.get(position).health_title
                        }
                    }
                })
            }

            adapter!!.updateList(it)

            vpItemDetail.setCurrentItem(position, false)
        })
        return rootView
    }
}