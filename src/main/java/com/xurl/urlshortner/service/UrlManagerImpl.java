package com.xurl.urlshortner.service;

import com.xurl.urlshortner.config.Utils;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class UrlManagerImpl implements UrlManager {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public String getUrlByKey(@NotBlank String key) {
        String url = redisTemplate.opsForValue().get(key);
        return url;
    }

    @Override
    public String shortenUrl(@NotBlank String url) {
        // generating murmur3 based hash key as short URL
        String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
        System.out.println("URL Id generated: "+ id);
        redisTemplate.opsForValue().set(id, url);
        return Utils.SHORT_URL + id;
    }
}
