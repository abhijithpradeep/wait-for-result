package com.example.waitforresult.resource;

import com.example.waitforresult.service.api.WaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class WaitResource {

    @Autowired
    private WaitService waitService;

    @GetMapping("/wait-for/{id}")
    String waitFor(@PathVariable String id){
        return waitService.waitFor(id);
    }

    @PostMapping("/populate/{id}")
    void populate(@PathVariable String id, @RequestBody String body){
        waitService.populate(id, body);
    }

}
