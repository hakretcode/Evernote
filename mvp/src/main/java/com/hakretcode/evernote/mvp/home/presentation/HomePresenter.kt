package com.hakretcode.evernote.mvp.home.presentation

import com.hakretcode.evernote.mvp.home.Home
import com.hakretcode.evernote.mvp.model.Note
import com.hakretcode.evernote.mvp.model.RemoteDataSource
import com.hakretcode.evernote.mvp.model.SimplifiedDisposableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: Home.View, private val dataSource: RemoteDataSource) : Home.Presenter, SimplifiedDisposableObserver<List<Note>>() {
    private val compositeDisposable = CompositeDisposable()

    override fun getAllNotes() {
        compositeDisposable.add(
            dataSource.listNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(this)
        )
    }

    override fun onNext(t: List<Note>) {
        if (t.isNotEmpty()) view.displayNotes(t)
        else view.displayEmptyNote()
    }

    override fun onError(e: Throwable) {
        view.displayError("Erro ao carregar notas")
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}
