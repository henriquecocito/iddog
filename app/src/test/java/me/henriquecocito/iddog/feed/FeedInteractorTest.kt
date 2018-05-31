package me.henriquecocito.iddog.feed

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.willReturn
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import me.henriquecocito.iddog.feed.data.FeedRepositoryInterface
import me.henriquecocito.iddog.feed.data.FeedResponse
import me.henriquecocito.iddog.feed.domain.FeedInteractor
import me.henriquecocito.iddog.feed.domain.FeedInterface
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class FeedInteractorTest {

    @Mock private lateinit var repository : FeedRepositoryInterface

    private lateinit var interactor : FeedInterface
    private val expectedResponse = FeedResponse("husky", mutableListOf("a", "b", "c"))

    @Before
    fun setUp() {
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        interactor = FeedInteractor(repository)
    }

    @Test
    fun testFeed_returnFeedResponse_whenResponseIsSuccessful() {
        given(repository.feed(anyString(), anyString())).willReturn {
            Observable.just(expectedResponse)
        }

        val observer = TestObserver<FeedResponse>()
        interactor.feed("husky", "123").subscribe(observer)

        observer.assertNoErrors()
        observer.assertValue { it == expectedResponse }
        observer.assertComplete()
    }

    @Test
    fun testFeed_returnError_whenResponseIsError() {
        val expectedError : HttpException = mock()

        given(repository.feed(anyString(), anyString())).willReturn {
            Observable.error(expectedError)
        }

        val observer = TestObserver<FeedResponse>()
        interactor.feed("husky", "123").subscribe(observer)

        observer.assertError(expectedError)
        observer.assertNotComplete()
    }

    @After
    fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}