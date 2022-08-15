package com.karol.supervisorserverandworkers.master.scheduler;

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
        List<RegisteredWorker> workers = connectedWorkersService.getWorkers();
        log.info("healthCheck");
    }

}
