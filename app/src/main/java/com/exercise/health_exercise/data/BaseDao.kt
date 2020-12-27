package com.exercise.health_exercise.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable

interface BaseDao<T> {
    @Insert
    fun insert(entity:T)

    @Update
    fun update(entity:T):Int

    @Delete
    fun delete(entity:T):Int
}