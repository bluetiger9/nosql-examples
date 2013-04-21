package com.github.bluetiger9.nosql.examples.keyvalue.couchbase;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.internal.GetFuture;
import net.spy.memcached.internal.OperationFuture;

import com.couchbase.client.CouchbaseClient;
import com.github.bluetiger9.nosql.examples.util.Stopwatch;

public class CouchBaseExample {
	public static final List<URI> SERVERS = Arrays.asList(URI.create("http://nosql-x64-1.local:8091/pools"));
	public static final String PASSWORD = "";
	
	public static final String BUCKET = "default";
	public static final int EXPIRE_TIME = 10;
	
	private CouchbaseClient client;
	
	public CouchBaseExample() {
		connect();
	}
	
	void connect() {
		try {
			client = new CouchbaseClient(SERVERS, BUCKET, PASSWORD);
		} catch (Exception e) {
			System.err.println("Error connecting to Couchbase: "
					+ e.getMessage());
			System.exit(-1);
		}
	}
	
	boolean shutDown() {
		return client.shutdown(3, TimeUnit.SECONDS);		
	}
	
	Object syncGet(String key) {
		return client.get(key);
	}
	
	GetFuture<Object> asyncGet(String key) {
		return client.asyncGet(key);
	}
	
	OperationFuture<Boolean> set(String key, Object value) {
		return client.set(key, EXPIRE_TIME, value);
	}
	
	OperationFuture<Boolean> delete(String key) {
		return client.delete(key);
	}
	
	public static void main(String args[]) throws Exception {
		final CouchBaseExample couchBase = new CouchBaseExample();
		
		final Stopwatch stopwatch = new Stopwatch();
		
		stopwatch.start();
		System.out.print("Setting name...");
		System.out.println(couchBase.set("name", "John").get() ? stopwatch : "fail");

		stopwatch.restart();
		System.out.print("Setting age..."); 
		System.out.println(couchBase.set("age", "30").get() ? stopwatch : "fail");
		
		stopwatch.restart();
		System.out.print("Getting name (sync)...");		
		System.out.println(couchBase.syncGet("name") + "..." + stopwatch);
		
		stopwatch.restart();
		System.out.print("Getting age (async)...");
		System.out.println(couchBase.asyncGet("age").get() + "..." + stopwatch);
		
		stopwatch.restart();
		System.out.print("Deleting name...");
		System.out.println(couchBase.delete("name") + "..." + stopwatch);
		
		stopwatch.restart();
		System.out.print("Deleting name...");
		System.out.println(couchBase.delete("name").get().booleanValue() ? "fail" : stopwatch);
		
		stopwatch.restart();
		System.out.print("Deleting age...");
		System.out.println(couchBase.delete("age").get().booleanValue() ? "fail" : stopwatch);
		
		stopwatch.restart();
		System.out.print("Deleting address (inexistent)...");
		System.out.println(couchBase.delete("address").get().booleanValue() ? "fail" : stopwatch);
		
		System.out.println("Shuting down the client..." + (couchBase.shutDown() ? "ok" : "error"));
	}
}
