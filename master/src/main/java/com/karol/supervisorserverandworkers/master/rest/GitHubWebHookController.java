package com.karol.supervisorserverandworkers.master.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/github-webhook")
@Slf4j
public class GitHubWebHookController {


    @PostMapping
    public String register(@RequestBody String json){
        log.info("Register event from {}",json);
        return "babla";
    }
}
