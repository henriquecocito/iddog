package me.henriquecocito.iddog.feed.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_feed.view.*
import me.henriquecocito.iddog.R

class FeedGridAdapter(private val context: Context,
                      private val list: MutableList<String>,
                      private val listener: (position: Int) -> Unit) : RecyclerView.Adapter<FeedGridAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false), listener)

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    class ViewHolder(val view: View, private val listener: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bind(url: String, position: Int) {

            Glide
                    .with(view)
                    .load(url)
                    .transition(withCrossFade())
                    .apply(RequestOptions()
                            .error(R.drawable.bg_broken_image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop())
                    .into(view.image)

            view.setOnClickListener { listener(position) }
        }
    }
}