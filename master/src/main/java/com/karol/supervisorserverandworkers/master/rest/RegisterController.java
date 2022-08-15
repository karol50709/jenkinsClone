package com.karol.supervisorserverandworkers.master.rest;

import com.karol.supervisorServerAndWorkers.beans.RegisterBean;
import com.karol.supervisorServerAndWorkers.master.api.RegisterApi;
import com.karol.supervisorserverandworkers.master.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegisterController implements RegisterApi {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public String register(@RequestBody RegisterBean registerBean){
        log.info("Register event from {}", registerBean.getAddress());
        registerService.verify(registerBean);
        return "babla";
    }

    @Override
    @DeleteMapping
    @RequestMapping("/unregister/{workerAddress}")
    public Boolean unregister(@PathVariable String workerAddress) {
        registerService.unregister(workerAddress);
        return null;
    }
}
