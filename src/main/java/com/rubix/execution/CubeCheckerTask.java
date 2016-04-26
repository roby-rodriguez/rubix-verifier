package com.rubix.execution;

import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.repository.impl.CubeRepositoryImpl;
import com.rubix.service.impl.CubeCheckerServiceImpl;

@Component
public class CubeCheckerTask {

    private final static Logger LOGGER = Logger.getLogger(CubeCheckerServiceImpl.class);

    @Autowired
    private CubeRepositoryImpl cubeRepository;

    @Async
    public CompletableFuture<CubeEntity> check(final CubeDTO cube, final String label, final String permutation) {
        LOGGER.info("Checking combination: " + label + " (label) " + permutation + " (permutation)");
        final CubeDTO alternate = cube.transform(label, permutation);
        final String state = alternate.toString();
        final CubeEntity result = cubeRepository.findByKey(state);
        LOGGER.info("Finished combination: " + label + " (label) " + permutation + " (permutation)." + " State: "
                + state + (result == null ? " not " : " ") + "found");
        return CompletableFuture.completedFuture(result);
    }
}
