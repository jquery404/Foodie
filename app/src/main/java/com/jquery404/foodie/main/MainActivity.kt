package com.jquery404.foodie.main

import android.os.Bundle
import com.jquery404.foodie.R
import com.jquery404.foodie.helpers.ActivityHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        btnLogin.setOnClickListener {
            ActivityHelper.start(this, MenuCategoryActivity::class.java)
        }

        btnSignup.setOnClickListener {
            ActivityHelper.start(this, SignupActivity::class.java)
        }

        ivLogo.setOnClickListener {
            ActivityHelper.start(this, ImageGalleryActivity::class.java)
        }

    }

}
