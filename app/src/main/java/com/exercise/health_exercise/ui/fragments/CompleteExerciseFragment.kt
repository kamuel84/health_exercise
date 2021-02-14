package com.exercise.health_exercise.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.CompleteListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.data.playExercise.PlayExerciseListData
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemHeaderData
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import kotlinx.android.synthetic.main.activity_complete.*

class CompleteExerciseFragment : BaseFragment() {

    val viewModel: CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(
                CustomExerciseViewModel::class.java)
    }

    var adapter:CompleteListAdapter ?= null
    companion object{
        @JvmStatic
        fun newInstance():CompleteExerciseFragment{
            var fragment : CompleteExerciseFragment = CompleteExerciseFragment()

            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view:View = inflater.inflate(R.layout.activity_complete, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var month:String = calComplete_Calendar.getMonth()
        var year:String = calComplete_Calendar.getYear()
        setData(year+month)

    }

    fun setData(date:String){

        viewModel.getGroupPlayList(date)?.observe(baseActivity!!, Observer {
            var list:ArrayList<PlayExerciseData> = ArrayList<PlayExerciseData>()
            list.addAll(it)

            calComplete_Calendar.setReservationData(list)
        })

        viewModel.getPlayItemExercise(date)?.observe(baseActivity!!, Observer {
            var list:ArrayList<PlayExerciseListData> = ArrayList<PlayExerciseListData>()
            if(it != null && it.size > 0){
                it.forEachIndexed { index, playExerciseItemHeaderData ->
                    list.add(PlayExerciseListData(AppContents.VIEWTYPE_PLAYITEM, playExerciseItemHeaderData))
                }

                adapter = CompleteListAdapter(mContext!!)
                rvComplete_list.adapter = adapter
                rvComplete_list.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)

                adapter!!.update(list)
            }

        })

//        if(list != null && list.size > 0){
//            var detailList : ArrayList<PlayExerciseListData> = ArrayList<PlayExerciseListData>()
//            list.forEachIndexed { index, playExerciseData ->
//                detailList.add(PlayExerciseListData(AppContents.VIEWTYPE_DATEHADER, playExerciseData))
//
//                viewModel.getPlayList(playExerciseData.strDate)?.observe(baseActivity!!, Observer {
//                    if(it != null && it.size > 0) {
//                        Log.d("kamuel", "groupList.value.size ::: " + it.size)
//                        it.forEachIndexed { index, playExerciseData ->
//                            var itemList : LiveData<List<PlayExerciseItemHeaderData>> ?= viewModel.playExerciseItemRepository.getItemList(playExerciseData.idx.toInt())
//                            if(itemList != null && itemList.value != null && itemList.value!!.size > 0){
//                                itemList.value!!.forEachIndexed { index, playExerciseItemHeaderData ->
//                                    detailList.add(PlayExerciseListData(AppContents.VIEWTYPE_PLAYITEM, playExerciseItemHeaderData))
//                                }
//                            }
//                        }
//
//                        adapter = CompleteListAdapter(mContext!!)
//                        rvComplete_list.adapter = adapter
//                        rvComplete_list.layoutManager = LinearLayoutManager(mContext, RecyclerView.VERTICAL, false)
//
//                        adapter!!.update(detailList)
//
//                    }else
//                        Log.d("kamuel", "groupList.value.size ::: nul!!!!")
//                })
//            }
//        }

    }
}