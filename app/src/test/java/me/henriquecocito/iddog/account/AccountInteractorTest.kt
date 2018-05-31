package me.henriquecocito.iddog.account

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.willReturn
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import me.henriquecocito.iddog.account.data.AccountRepositoryInterface
import me.henriquecocito.iddog.account.domain.AccountInteractor
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.login.data.model.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AccountInteractorTest {

    @Mock private lateinit var repository : AccountRepositoryInterface

    private lateinit var interactor : AccountInterface

    private val expectedUser = User("henriquecocito@gmail.com", "123")

    @Before
    fun setUp() {
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        interactor = AccountInteractor(repository)
    }


    @Test
    fun testAccount_shouldReturnUser_whenAccountIsSaved() {

        given(repository.saveAccount(any())).willReturn {
            Observable.just(expectedUser)
        }

        val observer = TestObserver<User>()
        interactor.save(expectedUser).subscribe(observer)

        observer.assertNoErrors()
        observer.assertValue { it == expectedUser }
        observer.assertComplete()

        verify(repository).saveAccount(expectedUser)
    }

    @Test
    fun testAccount_shouldReturnError_whenAccountIsNotSaved() {

        given(repository.saveAccount(any())).willReturn {
            Observable.error(Throwable())
        }

        val observer = TestObserver<User>()
        interactor.save(expectedUser).subscribe(observer)

        observer.assertError(Throwable::class.java)
        observer.assertNotComplete()
    }

    @Test
    fun testAccount_shouldReturnUser_whenAccountIsFound() {

        given(repository.getAccount()).willReturn {
            Observable.just(expectedUser)
        }

        val observer = TestObserver<User>()
        interactor.get().subscribe(observer)

        observer.assertNoErrors()
        observer.assertValue { it == expectedUser }
        observer.assertComplete()

        verify(repository).getAccount()
    }

    @Test
    fun testAccount_shouldReturnError_whenAccountIsNotFound() {

        given(repository.getAccount()).willReturn {
            Observable.error(Throwable())
        }

        val observer = TestObserver<User>()
        interactor.get().subscribe(observer)

        observer.assertError(Throwable::class.java)
        observer.assertNotComplete()
    }

    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}