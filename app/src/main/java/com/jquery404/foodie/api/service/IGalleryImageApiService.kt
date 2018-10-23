package com.jquery404.foodie.api.service

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IGalleryImageApiService {
    @GET("/sandbox/foodapp/api/food.php")
    fun getGallery(@Query("task") task: String): Observable<GalleryImageResponse.GalleryImage>


    companion object {
        fun create(): IGalleryImageApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://grabgetgo.com")
                    .build()

            return retrofit.create(IGalleryImageApiService::class.java)
        }

    }
}