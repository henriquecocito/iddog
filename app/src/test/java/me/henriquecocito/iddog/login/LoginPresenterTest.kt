package me.henriquecocito.iddog.login

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.willReturn
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.login.data.model.User
import me.henriquecocito.iddog.login.domain.LoginInterface
import me.henriquecocito.iddog.login.presentation.LoginContract
import me.henriquecocito.iddog.login.presentation.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginPresenterTest {

    @Mock private lateinit var accountInteractor : AccountInterface
    @Mock private lateinit var loginInteractor : LoginInterface
    @Mock private lateinit var view : LoginContract.View

    private lateinit var presenter : LoginContract.Presenter
    private val expectedUser : User = User("henriquecocito@gmail.com", "123")

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }

        presenter = LoginPresenter(view, loginInteractor, accountInteractor)
    }

    @Test
    fun testLogin_shouldOpenFeed_whenResponseIsSuccess() {

        given(loginInteractor.doLogin(any())).willReturn {
            Observable.just(expectedUser)
        }

        given(accountInteractor.save(any())).willReturn {
            Observable.just(expectedUser)
        }

        presenter.login("henriquecocito@gmail.com")

        verify(view).showLoading()
        verify(view).openFeed()
        verify(view).finish()
    }

    @Test
    fun testLogin_shouldShowError_whenResponseIsError() {

        val expectedError = Throwable("Error test")

        given(loginInteractor.doLogin(any())).willReturn {
            Observable.error(expectedError)
        }

        presenter.login("henriquecocito@gmail.com")

        verify(view).showLoading()
        verify(view).showError(expectedError)
        verify(view).hideLoading()
    }

    @Test
    fun testLogin_shouldOpenFeed_whenUserIsAlreadyLoggedIn() {

        given(accountInteractor.get()).willReturn {
            Observable.just(expectedUser)
        }

        presenter.start()

        verify(view).showLoading()
        verify(view).openFeed()
        verify(view).finish()
    }

    @Test
    fun testLogin_shouldDoNothing_whenUserIsNotLoggedIn() {

        val expectedError = Throwable("Not found")

        given(accountInteractor.get()).willReturn {
            Observable.error(expectedError)
        }

        presenter.start()

        verify(view).showLoading()
        verify(view).hideLoading()
    }
}