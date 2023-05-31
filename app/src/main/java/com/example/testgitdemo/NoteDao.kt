package com.example.testgitdemo


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    fun insert(note: NoteModel)

    @Update
    fun update(note: NoteModel)

    @Delete
    fun delete(note: NoteModel)

    @Query("delete from note_table")
    fun deleteAllNotes()

    @Query("select * from note_table order by title")
    fun getAllNotes(): LiveData<List<NoteModel>>

}