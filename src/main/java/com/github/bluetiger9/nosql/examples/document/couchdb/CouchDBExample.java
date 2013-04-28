package com.github.bluetiger9.nosql.examples.document.couchdb;

import java.net.MalformedURLException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class CouchDBExample {

    private HttpClient client;
    private CouchDbConnector db;

    public CouchDBExample() {
        connect();
    }
    
    void connect() {
        try {
            client = new StdHttpClient.Builder().url("http://nosql-x64-1.local:5984/").build();
            CouchDbInstance dbInstance = new StdCouchDbInstance(client);
            db = dbInstance.createConnector("my_first_database", true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        final CouchDBExample couchDB = new CouchDBExample();
        //ObjectNode doc = new ObjectNode(JsonNodeFactory.instance);
        //doc.put("name", "John");
        //doc.put("age", 30);        
        //couchDB.db.create("person", doc);
        System.out.println(couchDB.db.get(JsonNode.class, "person"));
    }

}
