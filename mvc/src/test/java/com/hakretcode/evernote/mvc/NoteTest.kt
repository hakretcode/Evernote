package com.hakretcode.evernote.mvc

import com.hakretcode.evernote.mvc.model.Note
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class NoteTest {
    @Test
    fun `test should format date pattern to month and year`() {
        val note = Note(title = "NotaA", body = "NotaA Conteudo", date = "20/01/2021")
        Assert.assertEquals(
            "Fev 2019",
            SimpleDateFormat("MM yyyy").format(Date(System.currentTimeMillis()))
        )
    }

    @Test
    fun `test should create a channel and subscriber, after subscribe in channel`() {
        val channel = createChannel()
        val subscriber = createSubscriber()
        channel.subscribeOn(Schedulers.single())
            .observeOn(Schedulers.io())
            .subscribe(subscriber)
    }

    private fun createChannel(): Observable<String> = Observable.create { emitter ->
        emitter.onNext("Bem vindo ao canal")
        emitter.onComplete()
    }

    private fun createSubscriber(): Observer<String> = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("Inscrição completa")
            println("Thread: ${Thread.currentThread().name}")
        }

        override fun onNext(t: String) {
            println(t)
            println("Thread: ${Thread.currentThread().name}")
        }

        override fun onError(e: Throwable) {
            println("Error: ${e.message}")
            println("Thread: ${Thread.currentThread().name}")
        }

        override fun onComplete() {
            println("Novo valor emitido")
            println("Thread: ${Thread.currentThread().name}")
        }

    }
}