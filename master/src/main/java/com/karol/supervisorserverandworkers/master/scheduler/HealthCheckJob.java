package com.karol.supervisorserverandworkers.master.scheduler;

import com.karol.supervisorServerAndWorkers.workerClient.HealthCheckClient;
import com.karol.supervisorserverandworkers.master.bean.RegisteredWorker;
import com.karol.supervisorserverandworkers.master.service.ConnectedWorkersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class HealthCheckJob {

    private final ConnectedWorkersService connectedWorkersService;

    public HealthCheckJob(ConnectedWorkersService connectedWorkersService) {
        this.connectedWorkersService = connectedWorkersService;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void healthCheck(){
        log.info("Health check nodes start");
        List<RegisteredWorker> workers = connectedWorkersService.getWorkers();
        if(workers.isEmpty()){
            log.warn("No workers connected");
        } else {
            workers.forEach(this::checkAliveNode);
        }
        log.info("Health check nodes stop");
    }

    private void checkAliveNode(RegisteredWorker registeredWorker) {
        HealthCheckClient healthCheckClient = new HealthCheckClient(registeredWorker.getAddress());
        Boolean alive = healthCheckClient.healthCheck();
        if(!alive){
            log.warn("Health Check error, unregister node {}", registeredWorker.getAddress());
            connectedWorkersService.unregister(registeredWorker.getAddress());
        }
    }

}
