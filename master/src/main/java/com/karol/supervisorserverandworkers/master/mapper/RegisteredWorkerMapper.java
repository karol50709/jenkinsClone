package com.karol.supervisorserverandworkers.master.mapper;

import com.karol.supervisorServerAndWorkers.beans.RegisterBean;
import com.karol.supervisorserverandworkers.master.bean.RegisteredWorker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisteredWorkerMapper {

    RegisteredWorker sourceToDestination(RegisterBean source);

    RegisterBean destinationToSource(RegisteredWorker destination);
}
