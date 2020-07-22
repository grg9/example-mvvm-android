package com.grgi9.examplemvvmandroid.data.repository

import com.grgi9.examplemvvmandroid.data.dto.PostsResult

interface PostsRepository {
    fun getPosts(postsResultCallback: (result: PostsResult) -> Unit)
}