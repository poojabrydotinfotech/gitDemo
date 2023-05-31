package com.example.testgitdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.annotations.NonNull
import kotlinx.coroutines.launch

class NoteViewModel (app: Application) : AndroidViewModel(app) {

    private val repository = NoteRepo(app)
    private val allNotes = repository.getAllNotes()

    fun insert(note: NoteModel) {
        repository.insert(note)
    }

    fun update(note: NoteModel) {
        repository.update(note)
    }

    fun delete(note: NoteModel) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return allNotes
    }


}

