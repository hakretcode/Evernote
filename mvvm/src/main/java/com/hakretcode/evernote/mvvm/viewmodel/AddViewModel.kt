package com.hakretcode.evernote.mvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakretcode.evernote.mvvm.data.NoteRepository
import com.hakretcode.evernote.mvvm.data.NoteRepositoryImpl
import com.hakretcode.evernote.mvvm.data.model.Note

class AddViewModel(private val repository: NoteRepository = NoteRepositoryImpl()) : ViewModel() {
    val title = ObservableField("")
    val body = ObservableField("")

    fun createNote(): LiveData<Note> {
        if (title.get().isNullOrEmpty() ||
        body.get().isNullOrEmpty()) return MutableLiveData(null)
        return repository.createNote(Note(title = title.get(), body = body.get()))
    }

    fun getNote(noteId: Int): LiveData<Note?> = repository.getNote(noteId)
}