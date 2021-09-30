package com.hakretcode.evernote.viper.data

import androidx.lifecycle.LiveData
import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.data.network.RetrofitClient
import io.reactivex.Observable

interface NoteRepository {
    fun listNotes(): Observable<List<Note>>

    fun getNote(noteId: Int): Observable<Note>

    fun createNote(note: Note): Observable<Note>
}