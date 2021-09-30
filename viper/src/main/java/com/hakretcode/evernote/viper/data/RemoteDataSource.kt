package com.hakretcode.evernote.viper.data

import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.data.network.RetrofitClient
import io.reactivex.Observable

class RemoteDataSource : NoteRepository {
    override fun listNotes(): Observable<List<Note>> = RetrofitClient.evernoteApi.listNotes()

    override fun getNote(noteId: Int): Observable<Note> = RetrofitClient.evernoteApi.getNote(noteId)

    override fun createNote(note: Note): Observable<Note> = RetrofitClient.evernoteApi.createNote(note)
}