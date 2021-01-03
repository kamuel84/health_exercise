package com.exercise.health_exercise.ui.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

open class BaseActivity:FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun pushFragment(layoutID: Int, fragment: Fragment){
        var manager:FragmentManager = supportFragmentManager
        var transaction:FragmentTransaction = manager.beginTransaction()
        transaction.replace(layoutID, fragment, "fragment")
        transaction.commitAllowingStateLoss()
    }

    protected fun popFragment(){
        val fm = supportFragmentManager
        val popped = fm.popBackStackImmediate()
    }
}