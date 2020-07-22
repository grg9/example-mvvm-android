package com.grgi9.examplemvvmandroid.data.repository

import com.grgi9.examplemvvmandroid.data.ApiService
import com.grgi9.examplemvvmandroid.data.dto.PostsResult
import com.grgi9.examplemvvmandroid.data.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsApiDataSource : PostsRepository {
    override fun getPosts(postsResultCallback: (result: PostsResult) -> Unit) {
//        EspressoIdlingResource.increment()
        ApiService.service.getCapa().enqueue(object : Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
//                EspressoIdlingResource.decrement()
                when {
                    response.isSuccessful -> {
                        response.body()?.let { capaBodyResponse ->
                            postsResultCallback(PostsResult.Success(capaBodyResponse))
                        }
                    }
                    else -> postsResultCallback(PostsResult.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
//                EspressoIdlingResource.decrement()
                postsResultCallback(PostsResult.ServerError)
            }
        })
    }
}