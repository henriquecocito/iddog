package me.henriquecocito.iddog.feed

import android.accounts.AccountsException
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.willReturn
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.feed.data.FeedResponse
import me.henriquecocito.iddog.feed.domain.FeedInterface
import me.henriquecocito.iddog.feed.presentation.FeedContract
import me.henriquecocito.iddog.feed.presentation.FeedPresenter
import me.henriquecocito.iddog.login.data.model.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class FeedPresenterTest {

    @Mock private lateinit var accountInteractor : AccountInterface
    @Mock private lateinit var feedInteractor : FeedInterface
    @Mock private lateinit var view : FeedContract.View

    private lateinit var presenter : FeedContract.Presenter

    private val expectedUser = User("henriquecocito@gmail.com", "123")
    private val expectedFeed = FeedResponse("husky", mutableListOf("a", "b", "c"))

    @Before
    fun setUp() {
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        presenter = FeedPresenter(view, feedInteractor, accountInteractor)
    }

    @Test
    fun testFeed_shouldShowItems_whenResponseIsSuccess() {

        given(accountInteractor.get()).willReturn {
            Observable.just(expectedUser)
        }

        given(feedInteractor.feed(anyString(), anyString())).willReturn {
            Observable.just(expectedFeed)
        }

        presenter.load("husky")

        verify(view).hideUnknownError()
        verify(view).hideNetworkError()
        verify(view).hideEmptyView()
        verify(view).showLoading()
        verify(view).showItems(expectedFeed.list)
        verify(view).hideLoading()
    }

    @Test
    fun testFeed_shouldOpenLogin_whenUserIsNotLoggedIn() {
        val expectedError = AccountsException()
        given(accountInteractor.get()).willReturn {
            Observable.error(expectedError)
        }

        presenter.load("husky")

        verify(view).hideUnknownError()
        verify(view).hideNetworkError()
        verify(view).hideEmptyView()
        verify(view).showLoading()
        verify(view).openLogin()
        verify(view).finish()
    }

    @Test
    fun testFeed_shouldReturnNetworkError_whenResponseIsUnknownHostError() {
        given(accountInteractor.get()).willReturn {
            Observable.just(expectedUser)
        }

        val expectedError = UnknownHostException()

        given(feedInteractor.feed(anyString(), anyString())).willReturn {
            Observable.error(expectedError)
        }

        presenter.load("husky")

        verify(view).hideUnknownError()
        verify(view).hideNetworkError()
        verify(view).hideEmptyView()
        verify(view).showLoading()
        verify(view).showNetworkError(expectedError)
        verify(view).hideLoading()
    }

    @Test
    fun testFeed_shouldReturnError_whenResponseIsHttpError() {
        given(accountInteractor.get()).willReturn {
            Observable.just(expectedUser)
        }

        val expectedError : HttpException = mock()

        given(feedInteractor.feed(anyString(), anyString())).willReturn {
            Observable.error(expectedError)
        }

        presenter.load("husky")

        verify(view).hideUnknownError()
        verify(view).hideNetworkError()
        verify(view).hideEmptyView()
        verify(view).showLoading()
        verify(view).showError(expectedError)
        verify(view).hideLoading()
    }

    @Test
    fun testFeed_shouldReturnUnknownError_whenResponseIsUnknownError() {
        given(accountInteractor.get()).willReturn {
            Observable.just(expectedUser)
        }

        val expectedError = Throwable()

        given(feedInteractor.feed(anyString(), anyString())).willReturn {
            Observable.error(expectedError)
        }

        presenter.load("husky")

        verify(view).hideUnknownError()
        verify(view).hideNetworkError()
        verify(view).hideEmptyView()
        verify(view).showLoading()
        verify(view).showUnknownError(expectedError)
        verify(view).hideLoading()
    }

    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}