package me.henriquecocito.iddog.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import me.henriquecocito.iddog.R

class DetailActivity : AppCompatActivity() {

    private val list : ArrayList<String>? = arrayListOf()
    private var position : Int = 0

    companion object {
        private const val LIST_KEY = "list"
        private const val ITEM_KEY = "item"

        fun newIntent(context: Context, list: MutableList<String>, position: Int) = Intent(context, DetailActivity::class.java).apply {
            putExtra(ITEM_KEY, position)
            putExtra(LIST_KEY, list.toTypedArray())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.extras?.getStringArray(LIST_KEY)?.let { list?.addAll(it) }
        intent.extras?.getInt(ITEM_KEY)?.let { position = it }

        pager.apply {
            adapter = DetailAdapter(this@DetailActivity, list)
            currentItem = position
            setPosition(position)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
                override fun onPageSelected(position: Int) {
                    setPosition(position)
                }
            })
        }
    }

    fun setPosition(position: Int) {
        this.position = position
        label.text = getString(R.string.label_counter, (position + 1).toString(), list?.size.toString())
    }
}