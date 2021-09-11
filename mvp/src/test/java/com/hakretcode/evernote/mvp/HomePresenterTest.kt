package com.hakretcode.evernote.mvp

import com.hakretcode.evernote.mvp.home.Home
import com.hakretcode.evernote.mvp.home.presentation.HomePresenter
import com.hakretcode.evernote.mvp.model.Note
import com.hakretcode.evernote.mvp.model.RemoteDataSource
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {
    @Rule @JvmField val taskSchedulerRule = RxJavaRule()
    @Mock private lateinit var mockView: Home.View
    @Mock private lateinit var mockDataSource: RemoteDataSource
    private lateinit var presenter: Home.Presenter
    @Before
    fun setup() {
        presenter = HomePresenter(mockView, mockDataSource)
    }
    private val fakeAllNotes: List<Note>
    get() = listOf(
        Note(0, "Note 1", "test", "01/02/2004", "Note 1 Body"),
        Note(0, "Note 2", "test", "01/02/2004", "Note 2 Body"),
        Note(0, "Note 3", "test", "01/02/2004", "Note 3 Body"),
        Note(0, "Note 4", "test", "01/02/2004", "Note 4 Body"),
        Note(0, "Note 5", "test", "01/02/2004", "Note 5 Body"),
    )
    @Test
    fun `test should fetch all notes`() {
        // Given
        Mockito.doReturn(Observable.just(fakeAllNotes)).`when`(mockDataSource).listNotes()
        // When
        presenter.getAllNotes()
        // Then
        Mockito.verify(mockView).displayNotes(fakeAllNotes)
    }
}