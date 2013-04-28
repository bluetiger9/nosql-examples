package com.github.bluetiger9.nosql.examples.keyvalue.voldemort;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;

public class VoldemortExample {
    public static final String URL = "tcp://nosql-x64-1.local:6666/";
    private StoreClient<String, String> client;
    
    public VoldemortExample() {
        connect();
    }

    private void connect() {
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(URL));
        client = factory.getStoreClient("test");
    }
    
    void set(String key, String value) {
        client.put(key, value);
    }
    
    String get(String key) {
        return client.get(key).getValue();
    }
    
    public static void main(String[] args) {
        final VoldemortExample voldemort = new VoldemortExample();
        voldemort.set("name", "John");
        System.out.println(voldemort.get("name"));        
    }

}
