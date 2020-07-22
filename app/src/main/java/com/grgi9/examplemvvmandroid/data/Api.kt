package com.grgi9.examplemvvmandroid.data

import com.grgi9.examplemvvmandroid.data.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("posts")
    fun getCapa(): Call<List<Post>>
}