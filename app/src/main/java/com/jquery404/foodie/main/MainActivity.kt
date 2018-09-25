package com.jquery404.foodie.main

import android.os.Bundle
import com.jquery404.foodie.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        btnLogin.setOnClickListener {
            //Login.start(this)
            MenuCategoryActivity.start(this)
        }

        btnSignup.setOnClickListener {
            Signup.start(this)
        }
    }


}
