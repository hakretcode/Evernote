package com.hakretcode.evernote.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hakretcode.evernote.mvvm.data.NoteRepository
import com.hakretcode.evernote.mvvm.data.model.Note
import com.hakretcode.evernote.mvvm.viewmodel.AddViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddViewModelTest {
    @get:Rule val rule: TestRule = InstantTaskExecutorRule()
    @Mock private lateinit var repository: NoteRepository
    private lateinit var viewModel: AddViewModel

    @Before
    fun setup() {
        viewModel = AddViewModel(repository)
    }

    @Test
    fun `test should not save note without title`() {
        Mockito.doReturn(MutableLiveData(Note())).`when`(repository).createNote(
            Note(title = "TEST TEST KAPPKAPPAKAPPA", body = "TEST! ! TETS"))

        viewModel.title.set("TEST TEST KAPPKAPPAKAPPA")
        viewModel.body.set("TEST! ! TETS")

        viewModel.createNote()
            .observeForever { note ->
                if (note != null) print("Ready")
                else print("Fail")
            }
    }
}