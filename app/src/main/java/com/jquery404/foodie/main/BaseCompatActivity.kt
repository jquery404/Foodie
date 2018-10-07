package com.jquery404.foodie.main

import android.app.Activity
import android.support.v7.app.AppCompatActivity

abstract class BaseCompatActivity : AppCompatActivity() {

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}