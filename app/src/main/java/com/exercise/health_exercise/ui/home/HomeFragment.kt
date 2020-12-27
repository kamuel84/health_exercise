package com.exercise.health_exercise.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.healthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.health_list.HealthListData

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
            }

//            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")

            adapter!!.updateList(it)
        })

//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}