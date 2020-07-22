package com.grgi9.examplemvvmandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.grgi9.examplemvvmandroid.data.dto.PostsResult
import com.grgi9.examplemvvmandroid.data.model.Post
import com.grgi9.examplemvvmandroid.data.repository.PostsRepository
import com.grgi9.examplemvvmandroid.presenter.PostsActivityViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostsActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var postsLiveDataObserver: Observer<List<Post>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: PostsActivityViewModel

    @Test
    fun `when viewmodel getPosts get success then sets conteudosLiveData`() {
        // Arrange
        val posts: List<Post> = listOf(Post(1, 1, "title", "body"))

        val resultSuccess = MockRepository(PostsResult.Success(posts))
        viewModel = PostsActivityViewModel(
            resultSuccess
        )
        viewModel.postLiveData.observeForever(postsLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getPosts()

        // Asserts
        verify(postsLiveDataObserver).onChanged(posts)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when viewmodel getBooks get server error then sets viewFlipperLiveData`() {
        // Arrange
        val resultServerError = MockRepository(PostsResult.ServerError)
        viewModel = PostsActivityViewModel(
            resultServerError
        )
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getPosts()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.posts_error))
    }

    @Test
    fun `when viewmodel getBooks get api error then sets viewFlipperLiveData`() {
        // Arrange
        val resultServerError = MockRepository(PostsResult.ApiError(500))
        viewModel = PostsActivityViewModel(
            resultServerError
        )
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getPosts()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.posts_error))
    }

}

class MockRepository(private val result: PostsResult) : PostsRepository {
    override fun getPosts(postsResultCallback: (result: PostsResult) -> Unit) {
        postsResultCallback(result)
    }
}