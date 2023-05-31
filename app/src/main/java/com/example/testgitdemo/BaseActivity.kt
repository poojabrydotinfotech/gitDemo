package com.example.testgitdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)



        val button = findViewById<Button>(R.id.button_save)
        val editNote = findViewById<EditText>(R.id.edit_word)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNote.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val note = editNote.text.toString()
                replyIntent.putExtra(EXTRA_TITLE, note)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }


}