package com.exercise.health_exercise.data.playExerciseItem

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.exercise.health_exercise.data.BaseDao

@Dao
interface PlayExerciseItemDao : BaseDao<PlayExerciseItemData> {
    @Query("SELECT * FROM play_exercise_item")
    fun getAll() : LiveData<List<PlayExerciseItemData>>

    @Query("SELECT play_exercise_index, " +
            "(SELECT strDate FROM play_exercise WHERE play_exercise.idx = play_exercise_index) AS playDate, " +
            "(SELECT title FROM health_list WHERE idx = health_list_index) AS title, " +
            "(SELECT count(*) FROM health_list_items GROUP BY health_list_index) AS playTotalCount, " +
            "(SELECT count(*) FROM play_exercise_item WHERE isComplete = 1 GROUP BY play_exercise_index) AS completeCount, " +
            "sum(play_time) as playTime FROM play_exercise_item WHERE isComplete=1 AND play_exercise_index IN (SELECT idx FROM play_exercise WHERE substr(strDate,0,7) = :date) GROUP BY play_exercise_index, title, playTotalCount, completeCount")
    fun getSumPlayList(date : String) :LiveData<List<PlayExerciseItemHeaderData>>
}