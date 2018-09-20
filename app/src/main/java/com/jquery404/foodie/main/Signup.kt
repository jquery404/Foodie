package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jquery404.foodie.R
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : BaseCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        init()
    }

    private fun init(){
        btnLogin.setOnClickListener(){
            Login.start(this)
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, Signup::class.java)
            intent.putExtra("flag", context.javaClass.getSimpleName())
            context.startActivity(intent)
        }
    }
}