package com.example.testgitdemo

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val ADD_NOTE_REQUEST = 1
const val EXTRA_TITLE = "com.TestGitDemo.EXTRA_TITLE"
const val DELETE_NOTE = 2
fun subscribeOnBackground(function: () -> Unit) {
        Single.fromCallable {
            function()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
