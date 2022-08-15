package com.karol.supervisorserverandworkers.worker.configuration;

import com.karol.supervisorServerAndWorkers.beans.RegisterBean;
import com.karol.supervisorServerAndWorkers.masterClient.MasterRegisterClient;
import com.karol.supervisorserverandworkers.worker.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
@Slf4j
public class RegisterToMaster implements CommandLineRunner {

    private final VerificationService verificationService;

    public RegisterToMaster(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @Override
    public void run(String... args) {
        MasterRegisterClient client = new MasterRegisterClient("http://localhost:8080");
        client.register(new RegisterBean("http://localhost:8081", verificationService.getVerificationUUID()));
    }

    @PreDestroy
    void unregister(){
        log.info("preDestroy");
        MasterRegisterClient client = new MasterRegisterClient("http://localhost:8080");
        client.unregister("http://localhost:8081");
    }
}
