package com.grgi9.examplemvvmandroid.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grgi9.examplemvvmandroid.R
import com.grgi9.examplemvvmandroid.data.model.Post

class PostsAdapter(
    private val posts: List<Post>,
    private val onItemClickListener: ((post: Post) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val txtUserId: TextView = itemView.findViewById(R.id.txt_user_id)
        private val txtId: TextView = itemView.findViewById(R.id.txt_id)
        private val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
        private val txtBody: TextView = itemView.findViewById(R.id.txt_body)

        internal fun bind(post: Post) {
            txtUserId.text = post.userId.toString()
            txtId.text = post.id.toString()
            txtTitle.text = post.title
            txtBody.text = post.body

            itemView.setOnClickListener {
                onItemClickListener(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}