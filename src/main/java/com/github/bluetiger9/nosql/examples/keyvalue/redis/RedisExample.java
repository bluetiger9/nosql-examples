package com.github.bluetiger9.nosql.examples.keyvalue.redis;

import redis.clients.jedis.Jedis;

public class RedisExample {
    public static final String HOST = "nosql-x64-1.local";
    
    private final Jedis jedis;
    
    public RedisExample() {
        jedis = new Jedis(HOST);
        jedis.set("name", "Johny");
        System.out.println(jedis.get("name"));
        jedis.del("name");
        System.out.println(jedis.get("name"));
    }
    
    public static void main(String[] args) {
        new RedisExample();
    }

}
