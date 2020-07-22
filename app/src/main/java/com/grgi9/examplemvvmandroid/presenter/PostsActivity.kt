package com.grgi9.examplemvvmandroid.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grgi9.examplemvvmandroid.R
import com.grgi9.examplemvvmandroid.data.model.Post
import com.grgi9.examplemvvmandroid.data.repository.PostsApiDataSource
import kotlinx.android.synthetic.main.activity_posts.*

class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        val viewModel: PostsActivityViewModel =
            PostsActivityViewModel.ViewModelFactory(
                PostsApiDataSource()
            )
                .create(PostsActivityViewModel::class.java)

        viewModel.postLiveData.observe(this, Observer {
            it?.let { posts: List<Post> ->
                with(rv_posts) {
                    layoutManager =
                        LinearLayoutManager(this@PostsActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter =
                        PostsAdapter(
                            posts
                        ) { post ->
                            val intent =
                                PostDetailActivity.getStartIntent(
                                    this@PostsActivity,
                                    post
                                )
                            this@PostsActivity.startActivity(intent)
                        }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                view_flipper.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessageResId ->
                    txt_error.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getPosts()

    }
}