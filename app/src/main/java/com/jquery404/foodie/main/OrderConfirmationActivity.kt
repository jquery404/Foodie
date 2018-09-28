package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jquery404.foodie.R

class OrderConfirmationActivity : BaseCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)
    }

    companion object {
        fun start(context: Context, extras: Bundle? = null) {
            val intent = Intent(context, OrderConfirmationActivity::class.java)
            extras?.let {
                intent.putExtras(extras)
            } ?: run {
                intent.putExtra("flag", context.javaClass.getSimpleName())
            }

            context.startActivity(intent)
        }
    }
}