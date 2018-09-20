package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jquery404.foodie.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : BaseCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init(){
        btnSignup.setOnClickListener(){
            Signup.start(this)
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, Login::class.java)
            intent.putExtra("flag", context.javaClass.getSimpleName())
            context.startActivity(intent)
        }
    }

}