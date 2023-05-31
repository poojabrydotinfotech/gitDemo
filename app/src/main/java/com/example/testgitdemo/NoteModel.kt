package com.example.testgitdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteModel(val title:String, @PrimaryKey(autoGenerate = false) val id: Int? = null)
