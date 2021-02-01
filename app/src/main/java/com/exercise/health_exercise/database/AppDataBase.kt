package com.exercise.health_exercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exercise.health_exercise.data.exercises.ExercisesDao
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListDao
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemDao
import com.exercise.health_exercise.data.health_list_item.HealthList_ItemsData
import com.exercise.health_exercise.data.playExercise.PlayExerciseDao
import com.exercise.health_exercise.data.playExercise.PlayExerciseData
import com.exercise.health_exercise.utils.PreferenceUtils

@Database(entities = arrayOf(HealthListData::class, ExercisesData::class, HealthList_ItemsData::class, PlayExerciseData::class), version = 2, exportSchema = true)
open abstract class AppDataBase : RoomDatabase() {

    abstract fun exercisesDao() : ExercisesDao
    abstract fun healthListItemDao() : HealthList_ItemDao
    abstract fun healthListDao() : HealthListDao
    abstract fun playExerciseDao() : PlayExerciseDao


    companion object{
        var mContext:Context ?= null
        private val DB_NAME = "health_exercise-db"
        @Volatile private var instance:AppDataBase ?= null

        @JvmStatic
        fun getInstance(context:Context):AppDataBase{
            return instance ?: synchronized(this){
                instance ?: buildDataBase(context)
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                var prefUtils : PreferenceUtils = PreferenceUtils.getInstance(mContext!!)
//                prefUtils.setBoolean("default_data", false)
                var query : String = "CREATE TABLE 'play_exercise' (" +
                        "'idx' LONG NOT NULL AUTO_INCREMENT PRIMARY_KEY, " +
                        "'strDate text, " +
                        "'healthListItemIdx' LONG)"
                database.execSQL(query)
            }
        }

        open fun buildDataBase(context:Context):AppDataBase{
            mContext = context
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }

}