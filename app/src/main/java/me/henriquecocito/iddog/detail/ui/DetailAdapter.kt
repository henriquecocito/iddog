package me.henriquecocito.iddog.detail.ui

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_pager.view.*
import me.henriquecocito.iddog.R
import java.util.ArrayList

class DetailAdapter(private val context: Context, private val list: ArrayList<String>?) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = list?.size ?: 0

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pager, container, false)
        Glide
                .with(view)
                .load(list?.get(position))
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions()
                        .error(R.drawable.bg_broken_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(view.image)

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}