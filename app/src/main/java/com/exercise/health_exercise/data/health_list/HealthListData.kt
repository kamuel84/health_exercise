package com.exercise.health_exercise.data.health_list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_list")
data class HealthListData(
    @PrimaryKey(autoGenerate = true)
    val idx : Long,
    val title : String,
    val list_type : String /** C: 사용자 직접입력, D: Default List **/
) {
}