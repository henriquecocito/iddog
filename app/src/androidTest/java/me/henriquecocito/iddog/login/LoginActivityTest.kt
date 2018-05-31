package me.henriquecocito.iddog.login

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import io.reactivex.Observable
import me.henriquecocito.iddog.R
import me.henriquecocito.iddog.account.domain.AccountInterface
import me.henriquecocito.iddog.feed.ui.FeedActivity
import me.henriquecocito.iddog.login.data.model.User
import me.henriquecocito.iddog.login.domain.LoginInterface
import me.henriquecocito.iddog.login.presentation.LoginContract
import me.henriquecocito.iddog.login.presentation.LoginPresenter
import me.henriquecocito.iddog.login.ui.LoginActivity
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule @JvmField val activity = IntentsTestRule<LoginActivity>(LoginActivity::class.java)

    @Mock private lateinit var loginInteractor : LoginInterface
    @Mock private lateinit var accountInteractor : AccountInterface

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        activity.activity.presenter = LoginPresenter(activity.activity as LoginContract.View, loginInteractor, accountInteractor)
    }

    @Test
    fun testLoginActivity_assertRequiredComponents() {
        onView(withId(R.id.logo))
                .check(matches(isDisplayed()))
                .check(matches(withText(R.string.logo_name)))

        onView(withId(R.id.email))
                .check(matches(isDisplayed()))

        onView(withId(R.id.login))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginActivity_shouldShowSnackBar_whenEmailIsEmpty() {
        given(loginInteractor.doLogin(anyString()))
                .willReturn(Observable.error(Throwable(activity.activity.getString(R.string.error_empty_email))))

        onView(withId(R.id.login))
                .perform(click())

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.error_empty_email)))
                .check(matches(isDisplayed()));
    }

    @Test
    fun testLoginActivity_shouldShowSnackBar_whenEmailIsNotCorrect() {
        given(loginInteractor.doLogin(anyString()))
                .willReturn(Observable.error(Throwable("error")))

        onView(withId(R.id.email))
                .perform(click())
                .check(matches(hasFocus()))
                .perform(typeText("abcd"), closeSoftKeyboard())

        onView(withId(R.id.login))
                .perform(click())

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("error")))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginActivity_shouldOpenFeed_whenEmailIsCorrect() {
        val expectedUser = User("henriquecocito@gmail.com", "123")

        given(loginInteractor.doLogin(anyString()))
                .willReturn(Observable.just(expectedUser))

        given(accountInteractor.save(expectedUser))
                .willReturn(Observable.just(expectedUser))

        onView(withId(R.id.email))
                .perform(click())
                .check(matches(hasFocus()))
                .perform(typeText(expectedUser.email), closeSoftKeyboard())

        onView(withId(R.id.login))
                .perform(click())

        intended(hasComponent(FeedActivity::class.java.name))
    }
}