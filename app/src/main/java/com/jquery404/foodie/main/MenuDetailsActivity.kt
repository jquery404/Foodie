package com.jquery404.foodie.main

import android.os.Bundle
import com.jquery404.foodie.R
import com.jquery404.foodie.helpers.ActivityHelper
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
            ActivityHelper.start(this, ViewCartActivity::class.java)
        }

    }

}