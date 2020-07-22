package com.grgi9.examplemvvmandroid.data.dto

import com.grgi9.examplemvvmandroid.data.model.Post

sealed class PostsResult {
    class Success(val posts: List<Post>) : PostsResult()
    class ApiError(val statusCode: Int) : PostsResult()
    object ServerError : PostsResult()
}