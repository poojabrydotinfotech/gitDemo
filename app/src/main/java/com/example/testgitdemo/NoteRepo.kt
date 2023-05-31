package com.example.testgitdemo

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

class NoteRepo(application: Application) {

    private var noteDao: NoteDao
    private var allNotes: LiveData<List<NoteModel>>

    private val database = NoteDataBase.getInstance(application)

    init {
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: NoteModel) {
        subscribeOnBackground {
            noteDao.insert(note)
        }
    }

    fun update(note: NoteModel) {
        subscribeOnBackground {
            noteDao.update(note)
        }
    }

    fun delete(note: NoteModel) {
        subscribeOnBackground {
            noteDao.delete(note)
        }
    }

    fun deleteAllNotes() {
        subscribeOnBackground {
            noteDao.deleteAllNotes()
        }
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return allNotes
    }
    /*val allWords: Flow<List<NoteModel>> = noteDao.getAllNotes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(noteModel: NoteModel) {
        noteDao.insert(noteModel)
    }*/
}