package com.gustavo.foton.desafio.marvel.presentation.characters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gustavo.foton.desafio.marvel.R
import com.gustavo.foton.desafio.marvel.extension.asJsonString
import com.gustavo.foton.desafio.marvel.framework.di.BaseUrlModule
import com.gustavo.foton.desafio.marvel.framework.di.CoroutinesModule
import com.gustavo.foton.desafio.marvel.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class, CoroutinesModule::class)
@HiltAndroidTest
class CharactersFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer().apply {
            start(8080)
        }
        launchFragmentInHiltContainer<CharactersFragment>()
    }

    @Test
    fun shouldShowCharacters_whenViewIsCreated(): Unit = runBlocking {
        server.enqueue(MockResponse().setBody("characters_page1.json".asJsonString()))
        delay(500)
        onView(
            withId(R.id.recyclerCharacters)
        ).check(
            matches(isDisplayed())
        )
    }
}