package com.hakretcode.evernote.viper.interactor

import android.annotation.SuppressLint
import com.hakretcode.evernote.viper.data.NoteRepository
import com.hakretcode.evernote.viper.data.RemoteDataSource
import com.hakretcode.evernote.viper.data.model.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class HomeInteractor(private val repository: NoteRepository = RemoteDataSource()) {

    fun fetchNotes(onNext: Consumer<List<Note>>, onError: Consumer<Throwable>) {
        repository.listNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(onNext)
            .doOnError(onError)
            .subscribe()
    }
}
