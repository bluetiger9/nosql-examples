package com.github.bluetiger9.nosql.examples.keyvalue.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedExample {
    private MemcachedClient client;
    
    public MemcachedExample() throws IOException {
        client = new MemcachedClient(new InetSocketAddress("nosql-x64-1.local", 11211));
    }
    
    void put(String key, String value) {
        client.add(key, 3600, value);
    }
    
    String get(String key) {
        return (String) client.get(key);
    }
    
    public static void main(String[] args) throws IOException {
        MemcachedExample memcached = new MemcachedExample();
        memcached.put("name", "John");
        System.out.println(memcached.get("name"));
        System.exit(0);
    }

}
