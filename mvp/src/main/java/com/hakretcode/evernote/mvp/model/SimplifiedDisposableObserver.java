package com.hakretcode.evernote.mvp.model;

import io.reactivex.observers.DisposableObserver;

public abstract class SimplifiedDisposableObserver<T> extends DisposableObserver<T> {
    @Override
    public void onComplete() {}
}
