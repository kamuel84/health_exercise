package com.exercise.health_exercise.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseListAdapter
import com.exercise.health_exercise.adapters.ExerciseListAdapter.onExerciseListener
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.view_models.GroupListViewModel
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import com.exercise.health_exercise.ui.exercise.ExerciseViewModel
import com.exercise.health_exercise.ui.itemDecoration.gridItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class GroupListDetailFragment : BaseFragment(), onExerciseListener{

    var adapter: ExerciseListAdapter? = null

    val groupListViewModel by lazy {
        ViewModelProvider(this, GroupListViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
                GroupListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var root = inflater.inflate(R.layout.fragment_home, container, false)

        groupListViewModel.groupList.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = ExerciseListAdapter(mContext!!, this)
                listHome.adapter = adapter
                adapter!!.updateList(it)
                var gridLayoutManager = GridLayoutManager(mContext, 3)
                listHome.layoutManager = gridLayoutManager
                listHome.addItemDecoration(gridItemDecoration(mContext!!))

                groupListViewModel.setItemCheck(baseActivity!!)
            } else {
                Log.e("kamuel", "groupList observe!!!!")
                adapter!!.updateList(it)
            }
        })

        groupListViewModel.groupIndex.observe(viewLifecycleOwner, Observer {
            groupListViewModel.getGroupList()
        })

        if(arguments != null){
            var index:Long = requireArguments().getLong(AppContents.INTENT_DATA_GROUP_INDEX, 0)
            groupListViewModel.setGroupIndex(index)
        }

        return root
    }

    override fun onChecked(data: ExercisesData, position: Int) {
        groupListViewModel.checkExerciseList(baseActivity!!, position, data, !data.check)
    }
}