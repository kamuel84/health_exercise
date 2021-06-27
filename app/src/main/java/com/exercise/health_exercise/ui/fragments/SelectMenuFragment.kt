package com.exercise.health_exercise.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.exercise.health_exercise.R
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.activitys.ListAddActivity

class SelectMenuFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_selectmenu, container, false)

        val llList:LinearLayout = root.findViewById(R.id.llMenu_List)
        val llAll:LinearLayout = root.findViewById(R.id.llMenu_All)

        llList.setOnClickListener {
            if(baseActivity is ListAddActivity)
                (baseActivity as ListAddActivity).setListFragment()
        }
        llAll.setOnClickListener {
            if(baseActivity is ListAddActivity)
                (baseActivity as ListAddActivity).setAllFragment()
        }
        return root
    }
}