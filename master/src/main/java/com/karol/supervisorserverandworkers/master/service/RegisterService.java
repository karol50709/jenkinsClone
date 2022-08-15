package com.karol.supervisorserverandworkers.master.service;

import com.karol.supervisorServerAndWorkers.beans.RegisterBean;
import com.karol.supervisorServerAndWorkers.workerClient.WorkerRegisterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterService {

    private final ConnectedWorkersService connectedWorkersService;

    public RegisterService(ConnectedWorkersService connectedWorkersService) {
        this.connectedWorkersService = connectedWorkersService;
    }

    public Boolean verify(RegisterBean registerBean) {
        WorkerRegisterClient workerRegisterClient = new WorkerRegisterClient(registerBean.getAddress());
        log.info("Send verification request to {} with uuid {}", registerBean.getAddress(), registerBean.getVerificationCode());
        Boolean result = workerRegisterClient.verificationRegister(registerBean.getVerificationCode());
        if (result) {
            connectedWorkersService.register(registerBean);
        }
        return result;
    }

    public void unregister(String workerAddress) {
        connectedWorkersService.unregister(workerAddress);
    }
}
