package com.rubix.execution;

import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.repository.CubeRepository;
import com.rubix.service.impl.CubeCheckerServiceImpl;

@Component
public class CubeCheckerTask {

    private final static Logger LOGGER = Logger.getLogger(CubeCheckerServiceImpl.class);

    @Autowired
    private CubeRepository cubeRepository;

    @Async
    public Future<CubeEntity> check(final CubeDTO cube, final String label, final String permutation) {
        LOGGER.info("Checking combination: " + label + " (label) " + permutation + " (permutation)");
        final CubeDTO alternate = cube.transform(label, permutation);
        final String state = alternate.toString();
        final CubeEntity result = cubeRepository.findByKey(state);
        LOGGER.info("Finished combination: " + label + " (label) " + permutation + " (permutation)." + " State: "
                + state + (result == null ? " not " : " ") + "found");
        return new AsyncResult<CubeEntity>(result);
    }
}
