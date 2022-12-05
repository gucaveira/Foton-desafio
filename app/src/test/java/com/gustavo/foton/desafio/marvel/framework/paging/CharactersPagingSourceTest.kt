package com.gustavo.foton.desafio.marvel.framework.paging

import androidx.paging.PagingSource
import com.gustavo.foton.desafio.core.data.repository.CharactersRemoteDataSource
import com.gustavo.foton.desafio.marvel.factory.response.CharacterPagingFactory
import com.gustavo.foton.desafio.testing.MainCoroutineRule
import com.gustavo.foton.desafio.testing.model.CharacterFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersPagingSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: CharactersRemoteDataSource

    private var characterPagingFactory = CharacterPagingFactory()

    private val charactersFactory = CharacterFactory()

    private lateinit var charactersPagingSource: CharactersPagingSource

    @Before
    fun setUp() {
        charactersPagingSource = CharactersPagingSource(remoteDataSource, "")
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        //Arrange
        whenever(remoteDataSource.fetchCharacters(any()))
            .thenReturn(characterPagingFactory.create())

        //Act
        val result = charactersPagingSource
            .load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )

        //Assert
        val expected = listOf(
            charactersFactory.create(CharacterFactory.Hero.ThreeDMan),
            charactersFactory.create(CharacterFactory.Hero.ABomb)
        )
        assertEquals(
            PagingSource
                .LoadResult.Page(
                    data = expected,
                    prevKey = null,
                    nextKey = 20
                ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runTest {
        //arrange
        val exception = RuntimeException()
        whenever(remoteDataSource.fetchCharacters(any())).thenThrow(exception)

        //act
        val result = charactersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        //assert
        assertEquals(PagingSource.LoadResult.Error<Int, Character>(exception), result)
    }
}