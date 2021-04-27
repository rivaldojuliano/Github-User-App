package com.rivzdev.githubuserapp.model.api

import com.rivzdev.githubuserapp.BuildConfig
import com.rivzdev.githubuserapp.model.data.UserResponse
import com.rivzdev.githubuserapp.model.data.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getSearch(@Query("q") query: String): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getUserDetail(@Path("username") username: String): Call<Users>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<Users>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<Users>>
}