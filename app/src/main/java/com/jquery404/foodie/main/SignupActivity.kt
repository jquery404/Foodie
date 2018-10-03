package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jquery404.foodie.R
import com.jquery404.foodie.helpers.ActivityHelper
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : BaseCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        init()
    }

    private fun init(){
        btnLogin.setOnClickListener(){
            ActivityHelper.start(this, LoginActivity::class.java)
        }
    }
}