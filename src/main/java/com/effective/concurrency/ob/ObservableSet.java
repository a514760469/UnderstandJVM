package com.effective.concurrency.ob;

import com.effective.ForwardingSet;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet<E> extends ForwardingSet<E> {

    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();

    public ObservableSet(Set<E> s) {
        super(s);
    }

    public void addObserver(SetObserver<E> observer) {
//        synchronized (observers) {
            observers.add(observer);
//        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
//        synchronized (observers) {
            return observers.remove(observer);
//        }
    }

    private void notifyElementAdded(E element) {
//        List<SetObserver<E>> snapshot = null;
//        synchronized (observers) {
//            snapshot = new ArrayList<>(observers);
//        }
        for (SetObserver<E> observer : observers) {
            observer.added(this, element);
        }
    }

    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if (added) {
            notifyElementAdded(e);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c) {
            result |= add(element);
        }
        return result;
    }
}
