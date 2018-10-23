package com.jquery404.foodie.main

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

abstract class BaseCompatActivity : AppCompatActivity() {

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    inline fun showQuizAlertDialog(func: QuizDialogHelper.() -> Unit): AlertDialog =
            QuizDialogHelper(this).apply {
                func()
            }.create()
}