package com.grgi9.examplemvvmandroid.presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grgi9.examplemvvmandroid.R
import com.grgi9.examplemvvmandroid.data.model.Post

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        setupActionBar()
        loadPageInfo()
    }

    private fun setupActionBar() {
        supportActionBar?.title = "Post"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadPageInfo() {
        val post: Post? = intent.getSerializableExtra(EXTRA_POST) as? Post
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        private const val EXTRA_POST = "post"

        fun getStartIntent(context: Context, post: Post): Intent {
            return Intent(context, PostDetailActivity::class.java).apply {
                putExtra(EXTRA_POST, post)
            }
        }
    }


}