package com.exercise.health_exercise.data.health_list_item

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.health_list.HealthListData
import java.io.Serializable

@Entity(
    tableName = "health_list_items",
    foreignKeys = arrayOf(
                    ForeignKey(
                            entity = HealthListData::class,
                            parentColumns = arrayOf("idx"),
                            childColumns = arrayOf("health_list_index"),
                            onDelete = ForeignKey.CASCADE
                    ),
                    ForeignKey(
                            entity = ExercisesData::class,
                            parentColumns = arrayOf("idx"),
                            childColumns = arrayOf("health_index")
                    )
                ),
    indices = [
        Index("idx"),
        Index("health_list_index"),
        Index("health_index")
    ]
)
data class HealthList_ItemsData(
    @PrimaryKey(autoGenerate = true)
    val idx : Long,
    val health_list_index:Long,
    val health_index:Long,
    val health_sort:Int,
    val revert_count:Int,
    val play_time:Long
) : Serializable {
}