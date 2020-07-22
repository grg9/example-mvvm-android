package com.grgi9.examplemvvmandroid.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grgi9.examplemvvmandroid.R
import com.grgi9.examplemvvmandroid.data.dto.PostsResult
import com.grgi9.examplemvvmandroid.data.model.Post
import com.grgi9.examplemvvmandroid.data.repository.PostsRepository

class PostsActivityViewModel(val dataSource: PostsRepository) : ViewModel() {

    var postLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    var viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getPosts() {
        dataSource.getPosts { result: PostsResult ->
            when (result) {
                is PostsResult.Success -> {
                    postLiveData.value = result.posts
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_POSTS, null)
                }
                else -> {
                    viewFlipperLiveData.value = Pair(
                        VIEW_FLIPPER_ERROR,
                        R.string.posts_error
                    )
                }
            }
        }
    }

    class ViewModelFactory(private val dataSource: PostsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PostsActivityViewModel::class.java)) {
                return PostsActivityViewModel(
                    dataSource
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_POSTS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

}