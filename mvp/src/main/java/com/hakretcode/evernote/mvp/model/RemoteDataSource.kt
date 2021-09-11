package com.hakretcode.evernote.mvp.model

import com.hakretcode.evernote.mvp.network.RetrofitClient
import io.reactivex.Observable


class RemoteDataSource {
    fun listNotes(): Observable<List<Note>> = RetrofitClient.evernoteApi.listNotes()

    fun getNote(noteId: Int): Observable<Note> = RetrofitClient.evernoteApi.getNote(noteId)

    fun createNote(note: Note): Observable<Note> = RetrofitClient.evernoteApi.createNote(note)
}