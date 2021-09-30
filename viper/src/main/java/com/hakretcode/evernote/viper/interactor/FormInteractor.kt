package com.hakretcode.evernote.viper.interactor

import com.hakretcode.evernote.viper.data.NoteRepository
import com.hakretcode.evernote.viper.data.RemoteDataSource
import com.hakretcode.evernote.viper.data.model.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class FormInteractor(private val repository: NoteRepository = RemoteDataSource()) {
    private val compositeDisposable = CompositeDisposable()

    fun fetchNote(noteId: Int, onNext: Consumer<Note>, onError: Consumer<Throwable>) {
        compositeDisposable.add(repository.getNote(noteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(onNext)
            .doOnError(onError)
            .subscribe())
    }

    fun createNote(note: Note, onNext: Consumer<Note>, onError: Consumer<Throwable>) {
        compositeDisposable.add(repository.createNote(note)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(onNext)
            .doOnError(onError)
            .subscribe())
    }

    fun dispose() = compositeDisposable.clear()
}
