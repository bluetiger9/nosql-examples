package com.github.bluetiger9.nosql.examples.document.terrastore;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import terrastore.client.BucketOperation;
import terrastore.client.TerrastoreClient;
import terrastore.client.connection.resteasy.HTTPConnectionFactory;

public class TerrastoreExample {
    
    private TerrastoreClient client;

    public TerrastoreExample() {
        connect();
    }
    
    void connect() {
        client = new TerrastoreClient("http://nosql-x64-1.local:8205", new HTTPConnectionFactory());       
    }
    
    public static void main(String[] args) {
        final TerrastoreExample terrastore = new TerrastoreExample();
        final BucketOperation bucket = terrastore.client.bucket("test");        
        
        final ObjectNode doc = new ObjectNode(JsonNodeFactory.instance);
        doc.put("name", "John");
        doc.put("age", 30);  
        bucket.key("john").put(doc);
        
        System.out.println(bucket.key("john").get(JsonNode.class));
    }

}
