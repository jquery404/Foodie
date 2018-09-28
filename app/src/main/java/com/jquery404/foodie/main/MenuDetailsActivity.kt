package com.jquery404.foodie.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jquery404.foodie.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_menu_details.*
import kotlinx.android.synthetic.main.layout_add_to_cart.*

class MenuDetailsActivity : BaseCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_details)

        init()
    }

    private fun init() {
        val path: String = intent.extras.getString("IMAGE_PATH", "")
        val desc: String = intent.extras.getString("DESCRIPTION", "")

        Picasso.get().load(path).into(ivCollapseThumbnail)
        tvFoodDescription.text = desc

        btnAddToCart.setOnClickListener {
            ViewCartActivity.start(this)
        }


    }

    companion object {
        fun start(context: Context, extras: Bundle? = null) {
            val intent = Intent(context, MenuDetailsActivity::class.java)
            extras?.let {
                intent.putExtras(extras)
            } ?: run {
                intent.putExtra("flag", context.javaClass.getSimpleName())
            }

            context.startActivity(intent)
        }
    }
}