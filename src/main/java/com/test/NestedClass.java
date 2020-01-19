package com.test;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NestedClass {

    public static void main(String[] args) {
        MySet mySet = new MySet();
        MySet.MyIterator myIterator = mySet.new MyIterator();


    }

    public void t1(Object o) {
        Set<?> s = (Set<?>) o;

        List<?>[] stringList = new List<?>[1];

    }
}

class MySet<E> extends AbstractSet<E> {

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    public class MyIterator implements Iterator {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
