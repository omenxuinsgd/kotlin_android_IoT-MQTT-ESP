package com.example.ghapi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.github.com/")
    .build()
//    .addConverterFactory(ScalarsConverterFactory.create())

interface GithubService {
    @GET("users/laravel/laravel/contributors")
    suspend fun showList():
//            Call<String>

//            Call<List<GithubData>>
            List<GithubData>

    @GET("users/{user}")
    suspend fun showUser(@Path("user") user: String):
            GithubData
}

object GithubApi {
    val retrofitService = retrofit.create(GithubService::class.java)
}