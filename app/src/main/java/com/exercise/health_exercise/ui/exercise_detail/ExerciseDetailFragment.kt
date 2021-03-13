package com.exercise.health_exercise.ui.exercise_detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseDetailAdapter
import com.exercise.health_exercise.adapters.SelectExerciseListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemJoinData
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.ui.itemDecoration.gridItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseDetailFragment:BaseFragment(), ExerciseDetailAdapter.onExerciseDetailListener {
    val viewModel: CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            CustomExerciseViewModel::class.java)
    }
    var adapter : ExerciseDetailAdapter ?= null
    var idx:Long = 0

    interface onExerciseDetailListener{
        fun onItemSelect(idx:Long, position:Int)
    }

    companion object{
        var listener:ExerciseDetailFragment.onExerciseDetailListener ?= null

        @JvmStatic
        fun newInstance(index:Long, listener:ExerciseDetailFragment.onExerciseDetailListener) : ExerciseDetailFragment {
            var fragment: ExerciseDetailFragment = ExerciseDetailFragment()
            var bundle : Bundle = Bundle()
            bundle.putLong(AppContents.INTENT_DATA_LIST_INDEX, index)
            fragment.arguments = bundle

            this.listener = listener

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
        var rootView:View = inflater.inflate(R.layout.fragment_home, container, false)

        if(arguments != null)
            idx = requireArguments().getLong(AppContents.INTENT_DATA_LIST_INDEX, 0)

        viewModel.getCustomAllList(idx)?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = ExerciseDetailAdapter(mContext!!, this)
                listHome.adapter = adapter
                listHome.layoutManager = GridLayoutManager(mContext, 2)
                listHome.addItemDecoration(gridItemDecoration(mContext!!))
            }
//
////            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")
//
//            Toast.makeText(context, "Size ::: ${it.size}", Toast.LENGTH_SHORT).show()
            adapter!!.updateList(it)
        })

        return rootView
    }

    override fun onResume() {
        super.onResume()
        Log.d("kameul", "-----------------")
    }

    override fun onItemSelect(data: HealthList_ItemJoinData, position: Int) {
        if(listener != null)
            listener!!.onItemSelect(idx, position)
    }
}