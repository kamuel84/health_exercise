package com.exercise.health_exercise.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.healthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.database.AppDataBase
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var adapter: healthListAdapter? = null
    var mContext: Context? = null

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
                adapter = healthListAdapter(mContext!!)
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