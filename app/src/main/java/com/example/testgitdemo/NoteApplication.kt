package com.example.testgitdemo


import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication :Application(){

    /*private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { NoteDataBase.getDatabase(this , applicationScope) }
    val repository by lazy { NoteRepo(database.noteDao()) }*/

}

