package com.exercise.health_exercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exercise.health_exercise.data.exercises.ExercisesDao
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListDao
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemDao

@Database(entities = arrayOf(ExercisesData::class), version = 1)
abstract class AppDataBase private constructor() : RoomDatabase() {
    abstract fun exercisesDao() : ExercisesDao
    abstract fun healthListItemDao() : HealthList_ItemDao
    abstract fun healthListDao() : HealthListDao

    companion object{
        @Volatile private var instance:AppDataBase ? = null

        @Synchronized
        fun getInstance(context : Context) : AppDataBase ? {
            if(instance == null){
                instance = Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "weknot_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance
        }
    }
}