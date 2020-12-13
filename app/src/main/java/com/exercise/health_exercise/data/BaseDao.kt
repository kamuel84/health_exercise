package com.exercise.health_exercise.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.rxjava3.core.Completable

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity:T) : Completable

    @Update
    fun update(entity:T) : Completable

    @Delete
    fun delete(entity:T) : Completable
}