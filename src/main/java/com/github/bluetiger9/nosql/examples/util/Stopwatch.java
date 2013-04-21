package com.github.bluetiger9.nosql.examples.util;

public class Stopwatch {
	long startTime = -1;
	long stopTime = -1;
	
	public Stopwatch() {
		reset();
	}
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public void stop() {
		stopTime = System.currentTimeMillis();
	}
	
	public void reset() {
		startTime = -1;
		stopTime = -1;
	}
	
	public void restart() {
		reset(); start();
	}
	
	public long time() {
		if (startTime == -1) 
			return -1;
		
		long endTime = (stopTime != -1) ? stopTime : System.currentTimeMillis();
		return endTime - startTime;		
	}
	
	public String toString() {
		return time() + " ms";		
	}

}
