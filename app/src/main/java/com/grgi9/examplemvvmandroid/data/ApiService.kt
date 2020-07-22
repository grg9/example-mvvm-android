package com.grgi9.examplemvvmandroid.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private fun initRetrofit(): Retrofit {
        val gson = GsonBuilder()
//            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val service: Api = initRetrofit().create(Api::class.java)
}