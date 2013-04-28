package com.github.bluetiger9.nosql.examples.keyvalue.riak;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.cap.UnresolvedConflictException;
import com.basho.riak.client.convert.ConversionException;
import com.github.bluetiger9.nosql.examples.util.Stopwatch;

public class RiakExample {
    public static final String HOST = "nosql-x64-1.local";
    public static final int PORT = 8087;
    public static final String BUCKET = "myBucket";
    
    private IRiakClient client;
    private Bucket bucket;
    
    public RiakExample() {}
    
    void connect() {
        try {
            client = RiakFactory.pbcClient(HOST, PORT);
            bucket = client.createBucket(BUCKET).execute();
        } catch (RiakException e) {
            System.out.println("Unable to connect to Riak server");
            e.printStackTrace();
        }
    }
    
    void store(String key, String value) throws RiakRetryFailedException, UnresolvedConflictException, ConversionException {
        bucket.store(key, value).execute();        
    }
    
    IRiakObject fetch(String key) throws UnresolvedConflictException, RiakRetryFailedException, ConversionException {
        return bucket.fetch(key).execute();
    }
    
    void delete(String key) {
        bucket.delete(key);
    }
    
    public static void main(String[] args) throws RiakRetryFailedException, UnresolvedConflictException, ConversionException {
        final RiakExample riak = new RiakExample();
        riak.connect();
        
        final Stopwatch stopwatch = new Stopwatch();        
        
        stopwatch.start();
        System.out.print("Inserting name...");
        riak.store("name", "John");
        System.out.println(stopwatch);
        System.exit(0);
        
    }
}
