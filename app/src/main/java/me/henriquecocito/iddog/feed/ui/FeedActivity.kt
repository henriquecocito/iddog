package me.henriquecocito.iddog.feed.ui

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
        setOptionsNavigation()
        setSupportActionBar(toolbar)
    }

    private fun setOptionsNavigation() {
        bottomNavigation.apply {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.husky -> showFragment(FeedGridFragment.CATEGORY_HUSKY)
                    R.id.labrador -> showFragment(FeedGridFragment.CATEGORY_LABRADOR)
                    R.id.hound -> showFragment(FeedGridFragment.CATEGORY_HOUND)
                    R.id.pug -> showFragment(FeedGridFragment.CATEGORY_PUG)
                }
                true
            }
            selectedItemId = R.id.husky
        }
    }

    private fun showFragment(category: String) {
        supportFragmentManager.fragments.map {
            supportFragmentManager.beginTransaction().hide(it).commit()
        }

        supportFragmentManager.findFragmentByTag(category)?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
            return
        }

        supportFragmentManager.beginTransaction().add(R.id.container, FeedGridFragment.newInstance(category), category).commit()
    }
}