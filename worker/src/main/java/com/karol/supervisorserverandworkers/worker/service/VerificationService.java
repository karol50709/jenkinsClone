package com.karol.supervisorserverandworkers.worker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class VerificationService {

    private String uuid = "";

    public synchronized String getVerificationUUID(){
        String uuid = UUID.randomUUID().toString();
        this.uuid = uuid;
        return uuid;
    }

    public boolean verify(String uuid){
        boolean verifyResult = this.uuid.equals(uuid);
        log.info("verify result: {}", verifyResult);
        return verifyResult;
    }

}
