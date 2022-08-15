package com.karol.supervisorserverandworkers.master.service;

import com.karol.supervisorServerAndWorkers.beans.RegisterBean;
import com.karol.supervisorserverandworkers.master.bean.RegisteredWorker;
import com.karol.supervisorserverandworkers.master.mapper.RegisteredWorkerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ConnectedWorkersService {

    private Map<String , RegisteredWorker> workerMap;
    private final RegisteredWorkerMapper registeredWorkerMapper;

    public ConnectedWorkersService(RegisteredWorkerMapper registeredWorkerMapper) {
        this.registeredWorkerMapper = registeredWorkerMapper;
        this.workerMap = new HashMap<>();
    }

    public List<RegisteredWorker> getWorkers(){
        return new ArrayList<>(workerMap
                .values());
    }

    public void register(RegisterBean registerBean) {
        RegisteredWorker registeredWorker = registeredWorkerMapper.sourceToDestination(registerBean);
        workerMap.put(registerBean.getAddress(),registeredWorker );
    }

    public void unregister(String address) {
        workerMap.remove(address);
    }
}
