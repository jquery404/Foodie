package com.jquery404.foodie.main.models

import com.google.gson.annotations.SerializedName

class AnswerItem {
    @SerializedName("ans_id")
    var id: Int = 0
    var title: String? = null
    @SerializedName("is_right")
    var isRight: Int = 0

}