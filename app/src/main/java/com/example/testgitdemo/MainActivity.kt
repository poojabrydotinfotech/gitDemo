package com.example.testgitdemo


import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import com.google.android.material.floatingactionbutton.FloatingActionButton



class MainActivity : AppCompatActivity() {

    private lateinit var vm : NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        vm = ViewModelProviders.of(this)[NoteViewModel::class.java]

        viewModelOb()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NoteAdapter()
        recyclerView.adapter = adapter
        vm.getAllNotes().observe(this, Observer {
            Log.i("Notes observed", "$it")

            adapter.submitList(it)
       })
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, BaseActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null && requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {

               val  title = data.getStringExtra(EXTRA_TITLE)
               Log.e(TAG, "title: $title")
            vm.insert(title?.let { NoteModel(it) }!!)

            Toast.makeText(this, "Note inserted!", Toast.LENGTH_SHORT).show()

        }else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }
 private fun viewModelOb(){
     vm.getAllNotes().observe(this, Observer {
         Log.i("Notes observed", "$it")
     })
 }
    }
