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
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemDao
import com.exercise.health_exercise.data.playExerciseItem.PlayExerciseItemData

@Database(entities = arrayOf(HealthListData::class, ExercisesData::class, HealthList_ItemsData::class, PlayExerciseData::class, PlayExerciseItemData::class), version = 3, exportSchema = true)
open abstract class AppDataBase : RoomDatabase() {

    abstract fun exercisesDao() : ExercisesDao
    abstract fun healthListItemDao() : HealthList_ItemDao
    abstract fun healthListDao() : HealthListDao
    abstract fun playExerciseDao() : PlayExerciseDao
    abstract fun playExerciseItemDao() : PlayExerciseItemDao


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

        val MIGRATION_2_3 = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                var query : String = "CREATE TABLE 'play_exercise_item' (" +
                        "'idx' LONG NOT NULL AUTO_INCREMENT PRIMARY_KEY, " +
                        "'play_exercise_index' LONG, " +
                        "'health_list_index' LONG, " +
                        "'health_item_index' LONG," +
                        "'play_time' LONG, " +
                        "'isComplete' BOOLEAN DEFAULT FALSE)"

                database.execSQL(query)
            }
        }

        open fun buildDataBase(context:Context):AppDataBase{
            mContext = context
            return Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .addMigrations(MIGRATION_2_3)
                .build()
        }
    }

}