package com.exercise.health_exercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exercise.health_exercise.data.daos.GroupListDao
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

@Database(entities = arrayOf(HealthListData::class, ExercisesData::class, HealthList_ItemsData::class,
        PlayExerciseData::class, PlayExerciseItemData::class), version = 6, exportSchema = true)
open abstract class AppDataBase : RoomDatabase() {

    abstract fun groupListDao() : GroupListDao
    abstract fun exercisesDao() : ExercisesDao
    abstract fun healthListItemDao() : HealthList_ItemDao
    abstract fun healthListDao() : HealthListDao
    abstract fun playExerciseDao() : PlayExerciseDao
    abstract fun playExerciseItemDao() : PlayExerciseItemDao


    companion object{
        var mContext:Context ?= null
        var listener:RoomDatabase.Callback ?= null
        private val DB_NAME = "health_exercise-db"
        @Volatile private var instance:AppDataBase ?= null

        @JvmStatic
        fun getInstance(context:Context, callback:RoomDatabase.Callback):AppDataBase{
            listener = callback
            return instance ?: synchronized(this){
                instance ?: buildDataBase(context)
            }
        }

        @JvmStatic
        fun getInstance(context:Context):AppDataBase{
            listener = null
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

        val MIGRATION_3_4 = object : Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {


            }
        }

        val MIGRATION_4_5 = object :Migration(4,5){
            override fun migrate(database: SupportSQLiteDatabase) {
                var query:String = "ALTER TABLE 'exercise' add column 'checkIndex' INTEGER"

                database.execSQL(query)
            }
        }

        val MIGRATION_5_6 = object : Migration(5,6){
            override fun migrate(database: SupportSQLiteDatabase) {
                var query:String = "ALTER TABLE 'health_list_items' add column 'health_sort' INTEGER"
                database.execSQL(query)
            }
        }

        open fun buildDataBase(context:Context):AppDataBase{
            mContext = context
            var builder = Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME)
            builder.fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .addMigrations(MIGRATION_4_5)
                    .addMigrations(MIGRATION_5_6)

            if(listener != null )
                builder.addCallback(listener!!)

            var dataBase : AppDataBase = builder.build()
            return dataBase
        }
    }

}