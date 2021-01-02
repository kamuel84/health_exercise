package com.exercise.health_exercise.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.HealthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    var adapter: HealthListAdapter? = null

    val homeViewModel by lazy {
        ViewModelProvider(this, HomeViewModel.Factory(AppContents.currentActivity!!.application)).get(HomeViewModel::class.java)
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

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.getAllHealthList()?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = HealthListAdapter(mContext!!)
                listHome.adapter = adapter
                listHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

//            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")

            Toast.makeText(context, "Size ::: ${it.size}", Toast.LENGTH_SHORT).show()
            adapter!!.updateList(it)
        })

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        var healthListData : HealthListData = HealthListData(0L, "운동리스트 2", "D")
//        homeViewModel.insertHealthList(healthListData)
    }
}