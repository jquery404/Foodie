package com.jquery404.foodie.api.model

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("id") var id : Int = 0,
        var title : String? = null,
        var year : String? = null,
        var genre : String? = null,
        var poster : String? = null
)
