package com.exercise.health_exercise.ui.exercise

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.ExerciseListAdapter
import com.exercise.health_exercise.adapters.HealthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class ExerciseFragment : BaseFragment() {

    var adapter: ExerciseListAdapter? = null

    val exerciseViewModel by lazy {
        ViewModelProvider(this, ExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
            ExerciseViewModel::class.java)
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

        exerciseViewModel.getExerciseAllList()?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = ExerciseListAdapter(mContext!!)
                listHome.adapter = adapter
                listHome.layoutManager = GridLayoutManager(mContext, 2)
            }

//            var addData: HealthListData = HealthListData(-1, "Add your own workout", "A")

            Toast.makeText(context, "Size ::: ${it.size}", Toast.LENGTH_SHORT).show()
            adapter!!.updateList(it)
        })

        return root
    }
}