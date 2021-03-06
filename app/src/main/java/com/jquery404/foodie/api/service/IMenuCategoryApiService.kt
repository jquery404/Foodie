package com.jquery404.foodie.api.service

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IMenuCategoryApiService {

    @GET("/sandbox/foodapp/api/food.php")
    fun getCategories(@Query("task") task: String): Observable<MenuCategoryResponse>


    companion object {
        fun create(): IMenuCategoryApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://grabgetgo.com")
                    .build()

            return retrofit.create(IMenuCategoryApiService::class.java)
        }

    }

}
