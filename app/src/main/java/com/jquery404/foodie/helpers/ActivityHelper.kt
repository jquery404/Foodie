package com.jquery404.foodie.helpers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jquery404.foodie.main.BaseCompatActivity

object ActivityHelper {

    fun start(context: Context, activity: Class<out BaseCompatActivity>, extras: Bundle? = null) {
        val intent = Intent(context, activity)
        extras?.let {
            intent.putExtras(extras)
        } ?: run {
            intent.putExtra("flag", context.javaClass.getSimpleName())
        }
        context.startActivity(intent)
    }
}