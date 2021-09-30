package com.hakretcode.evernote.mvi.data

import com.hakretcode.evernote.mvi.data.model.Note
import com.hakretcode.evernote.mvi.data.network.RetrofitClient
import io.reactivex.Observable

class NoteRepository {
    fun listNotes(): Observable<List<Note>> = RetrofitClient.evernoteApi.listNotes()

    fun getNote(noteId: Int): Observable<Note> = RetrofitClient.evernoteApi.getNote(noteId)

    fun createNote(note: Note): Observable<Note> = RetrofitClient.evernoteApi.createNote(note)
}