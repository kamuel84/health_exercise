package com.exercise.health_exercise.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.GroupListAdapter
import com.exercise.health_exercise.adapters.HealthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.home.HomeFragment
import com.exercise.health_exercise.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class GroupListFragment:BaseFragment(), GroupListAdapter.onGroupListListener  {

    val homeViewModel by lazy {
        ViewModelProvider(this, HomeViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(HomeViewModel::class.java)
    }

    var adapter: GroupListAdapter? = null

    companion object{
        @JvmStatic
        fun newInstance(): GroupListFragment {
            var fragment = GroupListFragment()

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.getAllHealthListJoinItem()?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = GroupListAdapter(mContext!!, this)
                listHome.adapter = adapter
                listHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                listHome.setHasFixedSize(true)
            }

//            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")
            adapter!!.updateList(it)
        })

        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onSelectItem(data: HealthListWithItemData, position: Int) {
        var fragment:GroupListDetailFragment = GroupListDetailFragment()
        var bundle:Bundle = Bundle()

        bundle.putLong(AppContents.INTENT_DATA_GROUP_INDEX, data.idx)

        fragment.arguments = bundle
        fragment.baseActivity = baseActivity
        baseActivity?.pushFragment(R.id.layout_fragment, fragment, "group_list_detail")
    }
}