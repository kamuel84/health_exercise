package com.exercise.health_exercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exercise.health_exercise.data.exercises.ExercisesDao
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListDao
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemDao
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData

@Database(entities = arrayOf(ExercisesData::class, HealthListData::class, HealthList_ItemsData::class), version = 1)
abstract class AppDataBase private constructor() : RoomDatabase() {
    abstract fun exercisesDao() : ExercisesDao
    abstract fun healthListItemDao() : HealthList_ItemDao
    abstract fun healthListDao() : HealthListDao

    companion object{
        private val DB_NAME = "health_exercise-db"
        @Volatile private var instance:AppDataBase ? = null

        @JvmName("getInstance1")
        fun getInstance(context:Context):AppDataBase{
            return instance ?: synchronized(this){
                instance ?: buildDataBase(context)
            }
        }

        private fun buildDataBase(context:Context):AppDataBase{
            return Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DB_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    }).build()
        }
    }

}