package com.hakretcode.evernote.mvi.presenter

import com.hakretcode.evernote.mvi.data.model.Note
import com.hakretcode.evernote.mvi.interactor.HomeInteractor
import com.hakretcode.evernote.mvi.router.HomeRouter
import com.hakretcode.evernote.mvi.view.HomeView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

class HomePresenterTest {
    @Test
    fun `display notes when query success`() {
        val list = listOf(listOf(Note(id = 1, title = "TitleA", body = "BodyA")),
            listOf(Note(id = 1, title = "TitleA", body = "BodyA")))
        val map = list.map { it: List<Note> -> it }
        val flatMap = list.flatMap { it: List<Note> -> it }
    }

}