package com.exercise.health_exercise.data.health_list

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "health_list")
data class HealthListData(
    @PrimaryKey(autoGenerate = true)
    var idx : Long,
    var title : String,
    var list_type : String /** C: 사용자 직접입력, D: Default List, A:Add **/
):Serializable