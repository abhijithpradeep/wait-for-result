package com.example.waitforresult.service.api;

public interface WaitService {

    String waitFor(String id);

    void populate(String id, String body);

}
