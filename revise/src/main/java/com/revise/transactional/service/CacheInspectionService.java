package com.revise.transactional.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CacheInspectionService {

    @Autowired
    private CacheManager cacheManager;

    public void printCacheContent(String cacheName) {
        Cache cache = this.cacheManager.getCache(cacheName);
        if (cache != null) {
            System.out.println("Cache content:");
            System.out.println(Objects.requireNonNull(cache.getNativeCache()));
        } else {
            System.out.println("Cache not exist: " + cacheName);
        }
    }
}