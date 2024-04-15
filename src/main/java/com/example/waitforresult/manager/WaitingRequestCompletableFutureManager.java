package com.example.waitforresult.manager;

import java.util.concurrent.CompletableFuture;

public interface WaitingRequestCompletableFutureManager {

    void put(String uuid, CompletableFuture<String> abc);

    CompletableFuture<String> getAndRemove(String uuid);

}
