package com.github.bluetiger9.nosql.examples.document.mongodb;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBExample {
    private static final String HOST = "nosql-x64-1.local";
    private static final int PORT = 27017;
    
    private MongoClient client;
    private DB db;
    private DBCollection collection;
    
    public MongoDBExample() throws UnknownHostException {
        connect();
    }
    
    void connect() throws UnknownHostException {
        client = new MongoClient(HOST, PORT);
        db = client.getDB("mydb");
        collection = db.getCollection("persons");
    }
    
    void insert(BasicDBObject object) {
        collection.insert(object);
    }

    public static void main(String[] args) throws UnknownHostException {
        final MongoDBExample mongoDB = new MongoDBExample();
        mongoDB.insert(new BasicDBObject().append("name", "John").append("age", 30));
        mongoDB.insert(new BasicDBObject().append("name", "Mike").append("friends", Arrays.asList("John")));
    }

}
