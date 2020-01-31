package com.thinktag.persist.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinktag.persist.model.EventState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisPublisher {

    @Autowired
    JedisPool jedisPool;

    public void publish(EventState es) throws Exception {
        try (Jedis publisher = jedisPool.getResource()) {
            String channel = String.join(":",es.getStateMachineName(),es.getName());
            String data = writeJson(es);

            System.out.println("Publishing on channel "+ channel);
            System.out.println(data);
            publisher.publish(channel,
                    data);
        }
    }

    private String writeJson(Object ob) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(ob);
    }
}
