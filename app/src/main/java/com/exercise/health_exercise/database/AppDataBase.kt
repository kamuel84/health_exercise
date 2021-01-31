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
import com.exercise.health_exercise.data.playExercise.PlayExerciseDao

@Database(entities = arrayOf(HealthListData::class, ExercisesData::class, HealthList_ItemsData::class), version = 1, exportSchema = true)
open abstract class AppDataBase : RoomDatabase() {

    abstract fun exercisesDao() : ExercisesDao
    abstract fun healthListItemDao() : HealthList_ItemDao
    abstract fun healthListDao() : HealthListDao
    abstract fun playExerciseDao() : PlayExerciseDao

    companion object{
        private val DB_NAME = "health_exercise-db"
        @Volatile private var instance:AppDataBase ? = null

        @JvmStatic
        fun getInstance(context:Context):AppDataBase{
            return instance ?: synchronized(this){
                instance ?: buildDataBase(context)
            }
        }

        open fun buildDataBase(context:Context):AppDataBase{
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }

}