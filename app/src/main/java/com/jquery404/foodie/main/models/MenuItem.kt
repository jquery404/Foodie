package com.jquery404.foodie.main.models

import com.google.gson.annotations.SerializedName

class MenuItem {

    @SerializedName("cat_id")
    var catId: Int = 0
    @SerializedName("cat_title")
    var categoryTitle: String? = null
    @SerializedName("title")
    var title: String? = null
    var id: Int = 0
    var price: String? = null
    var description: String? = null
    var thumb: String? = null
}