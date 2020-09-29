package com.jvm.jmm;

public class VolatileTestGreat {
	volatile boolean shutdownRequested;
	
	public void shutdown() {
		shutdownRequested = true;
	}
	
	public void doWork() {
	}
}
