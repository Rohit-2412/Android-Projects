package com.example.newsmonkey

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NewsListAdapter(
    private val mContext: Context,
    private val clickListener: newsItemClick
) :
    RecyclerView.Adapter<ViewHolder>() {

    private val items: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating the layout with the article view (R.layout.article_item)
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            clickListener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // the parameter position is the index of the current article
        // getting the current article from the ArrayList using the position
        val currentArticle: News = items[position]

        // setting the text of textViews
        holder.title.text = currentArticle.title
        holder.description.text = currentArticle.description

        // subString(0,10) trims the date to make it short
        (currentArticle.author +
                " | " + currentArticle.publishedAt.substring(
            0,
            10
        )).also { holder.authorAndDate.text = it }

        // setting the content Description on the Image
        holder.image.contentDescription = currentArticle.content

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // declaring and assigning the views
    val title: TextView = itemView.findViewById(R.id.tvTitle)
    val description: TextView = itemView.findViewById(R.id.tvDescription)
    val authorAndDate: TextView = itemView.findViewById(R.id.tvAuthorAndDate)
    val image: ImageView = itemView.findViewById(R.id.ivImage)

}

interface newsItemClick {
    fun onItemClicked(item: News)
}