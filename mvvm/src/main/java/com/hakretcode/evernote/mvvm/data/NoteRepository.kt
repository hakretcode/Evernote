package com.hakretcode.evernote.mvvm.data

import androidx.lifecycle.LiveData
import com.hakretcode.evernote.mvvm.data.model.Note
import com.hakretcode.evernote.mvvm.data.network.RetrofitClient
import io.reactivex.Observable

interface NoteRepository {
    fun getAllNotes(): LiveData<List<Note>?>

    fun getNote(noteId: Int): LiveData<Note?>

    fun createNote(note: Note): LiveData<Note>
}