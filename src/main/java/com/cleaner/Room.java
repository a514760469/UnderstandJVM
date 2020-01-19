package com.cleaner;

import sun.misc.Cleaner;

public class Room implements AutoCloseable {


    private final Cleaner CLEANER;

    private final State state;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        CLEANER = Cleaner.create(this, state);
    }


    private static class State implements Runnable {

        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("cleaning room");
            numJunkPiles = 0;
        }
    }




    @Override
    public void close() {
        CLEANER.clean();
    }
}
