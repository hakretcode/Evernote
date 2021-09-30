package com.hakretcode.evernote.viper.presenter

import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.interactor.HomeInteractor
import com.hakretcode.evernote.viper.router.HomeRouter
import com.hakretcode.evernote.viper.view.HomeView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest {
    private lateinit var presenter: HomePresenter
    @Mock private lateinit var view: HomeView
    @Mock private lateinit var interactor: HomeInteractor
    @Mock private lateinit var router: HomeRouter

    @Before
    fun setup() {
        presenter = HomePresenter(view, interactor, router)
    }

    @Test
    fun `display notes when query success`() {
        val list = listOf(Note(id = 1, title = "TitleA", body = "BodyA"),
            Note(id = 1, title = "TitleA", body = "BodyA"))
        presenter.onQuerySuccess(list)
        verify(view).displayNotes(list)
    }

}