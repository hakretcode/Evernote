package com.hakretcode.evernote.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hakretcode.evernote.mvvm.data.NoteRepository
import com.hakretcode.evernote.mvvm.data.NoteRepositoryImpl
import com.hakretcode.evernote.mvvm.data.model.Note

class HomeViewModel(private val repository: NoteRepository = NoteRepositoryImpl()) : ViewModel() {
    fun getAllNotes(): LiveData<List<Note>?> {
        return repository.getAllNotes()
    }
}