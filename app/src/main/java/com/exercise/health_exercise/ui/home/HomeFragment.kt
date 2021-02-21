package com.exercise.health_exercise.ui.home

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.health_exercise.ExerciseApplication
import com.exercise.health_exercise.R
import com.exercise.health_exercise.adapters.HealthListAdapter
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.ui.BaseFragment
import com.exercise.health_exercise.ui.activitys.ExerciseDetailActivity
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.utils.DialogUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.async

class HomeFragment : BaseFragment(), HealthListAdapter.onHealthListListener {

    interface onHomeFragmentListener{
        fun onListMore(position:Int, data:HealthListWithItemData)
    }

    var adapter: HealthListAdapter? = null
    private var listener: onHomeFragmentListener ?= null

    private var bottomDialog: BottomSheetDialog? = null
    var selectData : HealthListWithItemData ?= null

    val homeViewModel by lazy {
        ViewModelProvider(this, HomeViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(HomeViewModel::class.java)
    }

    val customExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(CustomExerciseViewModel::class.java)
    }

    companion object{
        @JvmStatic
        fun newInstance(listener: onHomeFragmentListener?):HomeFragment{
            var fragment = HomeFragment()
            fragment.listener = listener

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
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeViewModel.getAllHealthListJoinItem()?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) {
                adapter = HealthListAdapter(mContext!!, this)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == AppContents.REQUEST_CODE_ADDLIST){
            if(resultCode == RESULT_OK){
                if(data != null){
                    var listData : HealthListData = data.getSerializableExtra(AppContents.RESULT_DATA_LISTDATA) as HealthListData
                    var healthList : ArrayList<ExercisesData> = data.getSerializableExtra(AppContents.RESULT_DATA_HEALTHLIST) as ArrayList<ExercisesData>
                    var selectIndex : Long = data.getLongExtra(AppContents.INTENT_DATA_LIST_INDEX, 0)
                    var isEdit : Boolean = data.getBooleanExtra(AppContents.INTENT_DATA_EDIT_MODE, false)
                    homeViewModel.insertHealthList(listData)


                    var listIndex : Long = homeViewModel.getLastIndex()

                    if(isEdit){
//                        customExerciseViewModel.get
//                        customExerciseViewModel.deleteItemAll(selectIndex)
                        customExerciseViewModel.getItemAllList(selectIndex)?.observe(viewLifecycleOwner, Observer {
                            it.forEachIndexed { index, healthlistItemsdata ->
                                customExerciseViewModel.deleteItem(healthlistItemsdata)
                            }
                        })
                        listIndex = selectIndex
                    }
                    healthList.forEachIndexed { index, exercisesData ->
                        var customExerciseItem : HealthList_ItemsData = HealthList_ItemsData(0L, listIndex, exercisesData.idx, exercisesData.revert_count, exercisesData.play_Time)
                        customExerciseViewModel.insertItem(customExerciseItem)
                    }
                }
            }
        }
    }

    override fun onAdd() {
        var intent : Intent = Intent(ExerciseApplication.currentActivity, ListAddActivity::class.java)
        startActivityForResult(intent, AppContents.REQUEST_CODE_ADDLIST)
    }

    override fun onSelectItem(data: HealthListWithItemData, position: Int) {
        var intent:Intent = Intent(ExerciseApplication.currentActivity, ExerciseDetailActivity::class.java)
        intent.putExtra(AppContents.INTENT_DATA_LIST_INDEX, data.idx)

        startActivityForResult(intent, AppContents.REQUEST_CODE_LISTDETAIL)
    }

    override fun onMore(data: HealthListWithItemData, position: Int) {

        selectData = data
        showBottomSheetDialog()
    }

    /**
     * 하단 BottomSheetDialog Show
     */
    private fun showBottomSheetDialog() {
        if (bottomDialog != null && bottomDialog!!.isShowing()) {
            bottomDialog!!.dismiss()
            bottomDialog = null
        }

        var hsMenu: LinkedHashMap<String, String> = LinkedHashMap<String, String>()

        hsMenu.put("Edit", "edit")
        hsMenu.put("Delete", "delete")
        hsMenu.put("Cancel", "cancel")

        DialogUtils.showBottomSheetDialog(baseActivity!!, hsMenu, null, R.color.design_default_color_secondary, true, object : DialogUtils.OnBottomSheetSelectedListener{
            override fun onSelected(index: Int, text: String, value: String) {
                if(value == "edit"){
                    Log.d("kamuel", "selectData!!.idx :: "+selectData!!.idx)
                    var intent : Intent = Intent(baseActivity!!, ListAddActivity::class.java)
                    intent.putExtra(AppContents.INTENT_DATA_EDIT_MODE, true)
                    intent.putExtra(AppContents.INTENT_DATA_LIST_INDEX, selectData!!.idx)
                    Log.d("kamuel", "selectData!!.idx 2 :: "+selectData!!.idx)

                    baseActivity!!.startActivityForResult(intent, AppContents.REQUEST_CODE_ADDLIST)
                } else if(value == "delete"){
//                    Toast.makeText(this@MainActivity, "작업 중 입니다. ㅠㅠ", Toast.LENGTH_SHORT).show()
                    homeViewModel.healthListDelete(selectData!!.idx)
                }
            }
        })
    }

}