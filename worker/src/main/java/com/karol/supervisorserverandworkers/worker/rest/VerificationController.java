package com.karol.supervisorserverandworkers.worker.rest;

import com.karol.supervisorServerAndWorkers.worker.api.VerificationApi;
import com.karol.supervisorserverandworkers.worker.service.VerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification")
@Slf4j
public class VerificationController implements VerificationApi {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }


    @Override
    @GetMapping
    @RequestMapping("/verification-register/{uuid}")
    public Boolean verificationRegister(@PathVariable String uuid) {
        return verificationService.verify(uuid);
    }
}
