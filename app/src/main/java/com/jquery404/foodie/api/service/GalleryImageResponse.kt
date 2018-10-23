package com.jquery404.foodie.api.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jquery404.foodie.main.models.AnswerItem


class GalleryImageResponse {

    open class GalleryImage {
        open val record: List<GalleryItem>? = null
    }

    open class GalleryItem {
        val id: Int? = null
        val title: String? = null
        val thumb: String? = null
        val questions: String? = null
        @SerializedName("question")
        @Expose
        open val questionList: List<QuestionItem>? = null
    }

    open class QuestionItem {
        open val id: Int? = null
        open val title: String? = null
        @SerializedName("answer")
        @Expose
        open val answerList: List<AnswerItem>? = null
    }

}


