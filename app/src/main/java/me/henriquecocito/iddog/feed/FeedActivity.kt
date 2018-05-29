package me.henriquecocito.iddog.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_feed.*
import me.henriquecocito.iddog.R

class FeedActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, FeedActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

    }


}