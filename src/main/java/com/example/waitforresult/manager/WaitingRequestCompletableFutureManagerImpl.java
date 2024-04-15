package com.example.waitforresult.manager;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WaitingRequestCompletableFutureManagerImpl implements WaitingRequestCompletableFutureManager{

    private ConcurrentHashMap<String, CompletableFuture<String>> uuidToQueryCompletableFuture = new ConcurrentHashMap<>();


    @Override
    public void put(String uuid, CompletableFuture<String> completableFuture) {
        CompletableFuture<String> savedFuture = uuidToQueryCompletableFuture.putIfAbsent(uuid, completableFuture);
        if(savedFuture != null) {
            throw new IllegalStateException("A task already running with  Id: " + uuid);
        }
    }

    @Override
    public CompletableFuture<String> getAndRemove(String uuid) {
        if(!uuidToQueryCompletableFuture.containsKey(uuid)) {
            throw new IllegalArgumentException("A task already running with Id:" + uuid);
        }
        return uuidToQueryCompletableFuture.remove(uuid);
    }
}
