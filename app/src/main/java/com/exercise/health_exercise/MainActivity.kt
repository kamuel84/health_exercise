package com.exercise.health_exercise

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.ArrayRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.ui.activitys.BaseActivity
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.ui.fragments.CompleteExerciseFragment
import com.exercise.health_exercise.ui.fragments.CustomListFragment
import com.exercise.health_exercise.ui.home.HomeFragment
import com.exercise.health_exercise.utils.DialogUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import kotlin.collections.LinkedHashMap

class MainActivity : BaseActivity(), View.OnClickListener, HomeFragment.onHomeFragmentListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    val listViewModel:CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(CustomExerciseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        ExerciseApplication.currentActivity = this

        /** 플로팅 버튼 **/
//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        toolbar.setNavigationIcon(R.drawable.ic_side_menu)
        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(navView)
        }

        navView.setNavigationItemSelectedListener {

            when (it.itemId){
                R.id.nav_home -> {
                    toolbar.title = resources.getString(R.string.menu_home)
                    var fragment : HomeFragment = HomeFragment()
                    pushFragment(R.id.nav_host_fragment, fragment)
                }

                R.id.nav_custom_list -> {
                    toolbar.title = resources.getString(R.string.menu_custom_list)
                    var fragment : CustomListFragment = CustomListFragment()
                    pushFragment(R.id.nav_host_fragment, fragment)
                }

                R.id.nav_complete -> {
                    toolbar.title = resources.getString(R.string.menu_complete_list)
                    var fragment : CompleteExerciseFragment = CompleteExerciseFragment.newInstance()
                    pushFragment(R.id.nav_host_fragment, fragment)
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)

            return@setNavigationItemSelectedListener true
        }

//        val db:AppDataBase = Room.databaseBuilder(this, AppDataBase::class.java, "health_exercise-db")
//                .allowMainThreadQueries() /** 이값은 MainThread에서도 돌도록 만들어진 함수 **/
//                .build()

        var fragment: HomeFragment = HomeFragment.newInstance(this)
        pushFragment(R.id.nav_host_fragment, fragment)
        clMain_BottomMenu1.setOnClickListener(this)
        clMain_BottomMenu2.setOnClickListener(this)
        clMain_BottomMenu3.setOnClickListener(this)

        toolbar.title = resources.getString(R.string.menu_home)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        currentFragment().let {
            it!!.onActivityResult(requestCode, resultCode, data)

            var fragment : CustomListFragment = CustomListFragment()
            pushFragment(R.id.nav_host_fragment, fragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v){
            clMain_BottomMenu1 -> {
                toolbar.title = resources.getString(R.string.menu_home)
                var fragment : HomeFragment = HomeFragment()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
            clMain_BottomMenu2 -> {
                toolbar.title = resources.getString(R.string.menu_custom_list)
                var fragment : CustomListFragment = CustomListFragment()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
            clMain_BottomMenu3 -> {
                toolbar.title = resources.getString(R.string.menu_complete_list)
                var fragment : CompleteExerciseFragment = CompleteExerciseFragment.newInstance()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
        }
    }



    override fun onListMore(position: Int, data: HealthListWithItemData) {

    }
}