package com.jquery404.foodie.api.service

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface IMenuCategoryApiService {

    @GET("/filippella/Sample-API-Files/master/json/movies-api.json")
    fun getMovies(): Observable<MenuCategoryResponse>


    companion object {
        fun create(): IMenuCategoryApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://raw.githubusercontent.com")
                    .build()

            return retrofit.create(IMenuCategoryApiService::class.java)
        }

    }

}
