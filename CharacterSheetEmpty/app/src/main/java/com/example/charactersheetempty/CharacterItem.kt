package com.example.charactersheetempty

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterItem(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name="race") var race:String,
    @ColumnInfo(name="class") var _class:String,
    @ColumnInfo(name="level") var level:Int
)