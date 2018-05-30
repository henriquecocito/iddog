package me.henriquecocito.iddog.login

import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.login.data.LoginRepository
import me.henriquecocito.iddog.login.data.LoginRepositoryInterface
import me.henriquecocito.iddog.login.data.model.LoginResponse
import me.henriquecocito.iddog.login.data.model.User
import me.henriquecocito.iddog.login.domain.LoginInteractor
import me.henriquecocito.iddog.login.domain.LoginInterface
import me.henriquecocito.iddog.login.presentation.LoginContract
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginInteractorTest {

    @Mock private lateinit var repository : LoginRepositoryInterface

    private lateinit var interactor: LoginInterface

    private val expectedLoginResponse = LoginResponse(User("henriquecocito@gmail.com", "123"), mutableMapOf())

    @Before
    fun setUp() {
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{ Schedulers.trampoline() }

        interactor = LoginInteractor(repository)
    }

    @Test
    fun testLogin_shouldSignIn_whenEmailIsNotNull() {
        val email = "henriquecocito@gmail.com"

        given(repository.signIn(anyString())).willReturn {
            Observable.just(expectedLoginResponse)
        }

        val observer = TestObserver<User>()

        interactor.doLogin(email).subscribe(observer)
        observer.assertNoErrors()
        observer.assertValue {it == expectedLoginResponse.user}
        observer.assertComplete()

        verify(repository).signIn(email)
    }

    @Test
    fun testLogin_shouldReturnError_whenEmailIsEmpty() {
        val email = ""

        val observer = TestObserver<User>()

        interactor.doLogin(email).subscribe(observer)
        observer.assertError(Throwable::class.java)
        observer.assertNotComplete()
    }

    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}