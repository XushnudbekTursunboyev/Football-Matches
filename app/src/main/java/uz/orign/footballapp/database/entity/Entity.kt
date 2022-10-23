package uz.orign.footballapp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class Entity(
    @PrimaryKey
    val id:Int,
    val name:String
)
