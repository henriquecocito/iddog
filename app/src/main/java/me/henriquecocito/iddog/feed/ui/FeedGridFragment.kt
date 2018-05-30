package me.henriquecocito.iddog.feed.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.fragment_feed.*
import me.henriquecocito.iddog.R
import me.henriquecocito.iddog.feed.presentation.FeedContract
import me.henriquecocito.iddog.feed.presentation.FeedPresenter
import me.henriquecocito.iddog.login.ui.LoginActivity

class FeedGridFragment : Fragment(), FeedContract.View {

    private var presenter : FeedContract.Presenter? = null
    private var list = mutableListOf<String>()

    companion object {
        private const val CATEGORY_KEY = "category"
        const val CATEGORY_HUSKY = "husky"
        const val CATEGORY_LABRADOR = "labrador"
        const val CATEGORY_HOUND = "hound"
        const val CATEGORY_PUG = "pug"

        fun newInstance(category: String) = FeedGridFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY_KEY, category)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FeedPresenter(this.context!!, this).also {
            setupSwipeRefresh()
            setupRecyclerView()
            arguments?.getString(CATEGORY_KEY)?.let { category -> it.load(category) }
        }
    }

    override fun finish() {
        activity?.finish()
    }

    override fun showLoading() {
        activity?.loading?.visibility = View.VISIBLE
        refresh.isRefreshing = true
    }

    override fun hideLoading() {
        activity?.loading?.visibility = View.INVISIBLE
        refresh.isRefreshing = false
    }

    override fun showError(e: Throwable) {
        fragment.let {
            Snackbar
                .make(it, e.localizedMessage, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    override fun showItems(list: MutableList<String>) {
        this.list.apply {
            clear()
            addAll(list)
            grid.adapter.notifyDataSetChanged()
        }
    }

    override fun showEmptyView() {
        fragment.let {
            it.addView(LayoutInflater.from(context!!).inflate(R.layout.view_empty, it, false))
        }
    }

    override fun hideEmptyView() {
        fragment.let {
            it.removeView(it.findViewById(R.id.empty))
        }
    }

    override fun showNetworkError(e: Throwable) {
        if(list.size < 1)
            fragment.let {
                it.addView(LayoutInflater.from(context!!).inflate(R.layout.view_network_error, it, false))
            }
        else
            showError(e)
    }

    override fun hideNetworkError() {
        fragment.let {
            it.removeView(it.findViewById(R.id.error))
        }
    }

    override fun showUnknownError(e: Throwable) {
        if(list.size < 1)
            fragment.let {
                it.addView(LayoutInflater.from(context!!).inflate(R.layout.view_unknown_error, it, false))
            }
        else
            showError(e)
    }

    override fun hideUnknownError() {
        fragment.let {
            it.removeView(it.findViewById(R.id.error))
        }
    }

    override fun openLogin() {
        startActivity(LoginActivity.newIntent(context!!))
    }

    private fun setupSwipeRefresh() {
        refresh.apply {
            setOnRefreshListener {
                arguments?.getString(CATEGORY_KEY)?.let { presenter?.load(it) }
            }
            setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary)
        }
    }

    private fun setupRecyclerView() {
        grid.adapter = FeedGridAdapter(this.context!!, list)
    }
}