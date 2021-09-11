package com.hakretcode.evernote.mvp.add.presentation

import com.hakretcode.evernote.mvp.add.Add
import com.hakretcode.evernote.mvp.model.Note
import com.hakretcode.evernote.mvp.model.RemoteDataSource
import com.hakretcode.evernote.mvp.model.SimplifiedDisposableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class AddPresenter(private val view: Add.View) : Add.Presenter {
    private val dataSource = RemoteDataSource()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun createNote(title: String, body: String) {
        if (title.isEmpty() || body.isEmpty()) { view.displayError("Nota incompleta"); return }
        compositeDisposable.add(
            dataSource.createNote(Note(title = title, body = body))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : SimplifiedDisposableObserver<Note>() {
                    override fun onNext(t: Note) = view.returnToHome()

                    override fun onError(e: Throwable) = view.displayError("Erro ao criar notas")
                })
        )
    }

    override fun getNote(id: Int) {
        compositeDisposable.add(dataSource.getNote(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :
                SimplifiedDisposableObserver<Note>() {
                override fun onNext(t: Note) = view.displayNote(t.title, t.body)

                override fun onError(e: Throwable) = view.displayError("Erro ao carregar nota")
            }))
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}