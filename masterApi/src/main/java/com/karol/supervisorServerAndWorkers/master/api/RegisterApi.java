package com.karol.supervisorServerAndWorkers.master.api;

import com.karol.supervisorServerAndWorkers.beans.RegisterBean;

public interface RegisterApi {

     String register(RegisterBean registerBean);

     Boolean unregister(String workerAddress);
}
