package com.example.restapips17154.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrifitAPI {

    @GET("posts")
    fun getPosts() : Call<List<Post>>

    @GET("comments")
    fun getComments() : Call<List<Comment>>

    companion object {

        var BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create() : RetrifitAPI {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrifitAPI::class.java)

        }
    }
}