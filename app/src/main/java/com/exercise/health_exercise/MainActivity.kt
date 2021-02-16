package com.exercise.health_exercise

import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.database.AppDataBase
import com.exercise.health_exercise.ui.activitys.BaseActivity
import com.exercise.health_exercise.ui.fragments.CompleteExerciseFragment
import com.exercise.health_exercise.ui.home.HomeFragment
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

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
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        val db:AppDataBase = Room.databaseBuilder(this, AppDataBase::class.java, "health_exercise-db")
//                .allowMainThreadQueries() /** 이값은 MainThread에서도 돌도록 만들어진 함수 **/
//                .build()

        var fragment:HomeFragment = HomeFragment()
        pushFragment(R.id.nav_host_fragment, fragment)
        clMain_BottomMenu1.setOnClickListener(this)
        clMain_BottomMenu3.setOnClickListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v){
            clMain_BottomMenu1 -> {
                var fragment : HomeFragment = HomeFragment()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
            clMain_BottomMenu3 -> {
                var fragment : CompleteExerciseFragment = CompleteExerciseFragment.newInstance()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
        }
    }
}