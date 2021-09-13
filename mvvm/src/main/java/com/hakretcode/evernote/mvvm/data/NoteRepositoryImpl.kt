package com.hakretcode.evernote.mvvm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hakretcode.evernote.mvvm.data.model.Note
import com.hakretcode.evernote.mvvm.data.model.RemoteDataSource
import com.hakretcode.evernote.mvvm.data.model.SimplifiedDisposableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NoteRepositoryImpl : NoteRepository {
    private val dataSource = RemoteDataSource()
    private val compositeDisposable = CompositeDisposable()

    override fun getAllNotes(): LiveData<List<Note>?> {
        val data = MutableLiveData<List<Note>?>()
        compositeDisposable.add(
            dataSource.listNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : SimplifiedDisposableObserver<List<Note>?>() {
                    override fun onNext(t: List<Note>) = data.postValue(t)
                    override fun onError(e: Throwable) = data.postValue(null)
                })
        )
        return data
    }

    override fun getNote(noteId: Int): LiveData<Note?> {
        val data = MutableLiveData<Note?>()
        compositeDisposable.add(
            dataSource.getNote(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : SimplifiedDisposableObserver<Note>() {
                    override fun onNext(t: Note) = data.postValue(t)
                    override fun onError(e: Throwable) = data.postValue(null)
                })
        )
        return data
    }

    override fun createNote(note: Note): LiveData<Note> {
        val data = MutableLiveData<Note>()
        compositeDisposable.add(
            dataSource.createNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : SimplifiedDisposableObserver<Note>() {
                    override fun onNext(t: Note) = data.postValue(t)
                    override fun onError(e: Throwable) = data.postValue(null)
                })
        )
        return data
    }
}