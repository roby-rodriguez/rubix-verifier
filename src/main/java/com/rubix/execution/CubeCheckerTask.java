package com.rubix.execution;

import java.util.concurrent.CompletableFuture;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.rubix.constant.CubeConstants;
import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.repository.CubeCustomRepository;
import com.rubix.service.impl.CubeCheckerServiceImpl;

@Component
public class CubeCheckerTask {

    private final static Logger LOGGER = Logger.getLogger(CubeCheckerServiceImpl.class);

    @Autowired
    private CubeCustomRepository cubeRepository;

    @Async
    public CompletableFuture<CubeEntity> check(final CubeDTO cube, String label, String permutation) {
        LOGGER.debug("Checking combination: " + label + " (label) " + permutation + " (permutation)");
        final CubeDTO alternate = cube.transform(label, permutation);
        String state = alternate.toString();
        String collection = CubeConstants.getCollection(alternate.getSize());
        final CubeEntity result = cubeRepository.findByKey(state, collection);
        LOGGER.debug("Finished combination: " + label + " (label) " + permutation + " (permutation)." + " State: "
                + state + (result == null ? " not " : " has been ") + "found");
        LOGGER.debug("FOUNDED: " + result.getKey());
        return CompletableFuture.completedFuture(result);
    }
}
