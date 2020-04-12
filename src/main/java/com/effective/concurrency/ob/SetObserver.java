package com.effective.concurrency.ob;

public interface SetObserver<E> {

    void added(ObservableSet<E> set, E element);

}
