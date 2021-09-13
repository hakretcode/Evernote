package com.hakretcode.evernote.mvvm.data.model;

import io.reactivex.observers.DisposableObserver;

public abstract class SimplifiedDisposableObserver<T> extends DisposableObserver<T> {
    @Override
    public void onComplete() {}
}
