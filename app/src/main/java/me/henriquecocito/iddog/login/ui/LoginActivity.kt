package me.henriquecocito.iddog.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import me.henriquecocito.iddog.R
import me.henriquecocito.iddog.feed.ui.FeedActivity
import me.henriquecocito.iddog.login.presentation.LoginContract
import me.henriquecocito.iddog.login.presentation.LoginPresenter


class LoginActivity : AppCompatActivity(), LoginContract.View {

    var presenter : LoginContract.Presenter = LoginPresenter(this, this)

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login);
        presenter.start()
    }

    override fun login(view: View) {
        presenter.login(email.text.toString())
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
        email.isEnabled = false
        login.isEnabled = false
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
        email.isEnabled = true
        login.isEnabled = true
    }

    override fun showError(e: Throwable) {
        Snackbar
                .make(container, resources.getIdentifier(e.localizedMessage, "string", packageName).let {
                    if(it == 0) e.localizedMessage else getString(it)
                }, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun openFeed() {
        startActivity(FeedActivity.newIntent(this))
    }
}