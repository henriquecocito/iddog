package me.henriquecocito.iddog.login.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import me.henriquecocito.iddog.R
import me.henriquecocito.iddog.login.presentation.LoginContract
import me.henriquecocito.iddog.login.presentation.LoginPresenter
import android.widget.FrameLayout



class LoginActivity : AppCompatActivity(), LoginContract.View {

    val presenter : LoginContract.Presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login);
    }

    override fun onStart() {
        super.onStart()
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
        resources.getIdentifier(e.localizedMessage, "string", packageName).let {
            if(it != 0) {
                Snackbar
                        .make(container, it, Snackbar.LENGTH_LONG)
                        .show()
            }
        }
    }
}