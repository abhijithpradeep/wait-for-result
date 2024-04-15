package com.example.waitforresult.service.impl;

import com.example.waitforresult.manager.WaitingRequestCompletableFutureManager;
import com.example.waitforresult.service.api.WaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class WaitServiceImpl implements WaitService {

    @Autowired
    WaitingRequestCompletableFutureManager waitingRequestCompletableFutureManager;

    @Override
    public String waitFor(String id) {
        CompletableFuture<String> completableFuture= new CompletableFuture<>();
        waitingRequestCompletableFutureManager.put(id, completableFuture);
        //Here the thread waits, we should use reactive frameworks to prevent it
        return completableFuture.join();

    }

    @Override
    public void populate(String id, String body) {
        CompletableFuture<String> completableFuture = waitingRequestCompletableFutureManager.getAndRemove(id);
        completableFuture.complete(body);
    }
}
