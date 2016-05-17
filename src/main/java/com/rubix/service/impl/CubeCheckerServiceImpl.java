package com.rubix.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import com.rubix.constant.CubeConstants;
import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.execution.CubeCheckerTask;
import com.rubix.service.CubeCheckerService;
import com.rubix.util.CubeCheckerServiceManager;

/**
 * Checks if a cube state already exists in its current form or as an equivalent in the database.
 */
@Component
public class CubeCheckerServiceImpl implements CubeCheckerService {

    private final static Logger LOGGER = Logger.getLogger(CubeCheckerServiceImpl.class);

    @Autowired
    private CubeCheckerTask cubeCheckerTask;

    @Autowired
    private CubeCheckerServiceManager cubeCheckerServiceManager;

    @Override
    public void check(final CubeDTO cube, final DeferredResult<CubeEntity> deferredResult)
            throws InterruptedException, ExecutionException {
        LOGGER.debug("Launching checker threads for cube: " + cube);
        System.out.println("CHECKER=" + this.cubeCheckerServiceManager);
        for (String label : CubeConstants.CUBE_PERMUTATIONS) {
            for (String permutation : CubeConstants.CUBE_PERMUTATIONS) {
                final CompletableFuture<CubeEntity> checked = cubeCheckerTask.check(cube, label, permutation);
                checked.whenCompleteAsync((result, throwable) -> {
                    if (result != null || cubeCheckerServiceManager.checkFinished()) {
                        LOGGER.debug("RETURNED RESULT NOW" + cubeCheckerServiceManager);
                        // todo check if this setResult call actually ends the write to http stream i.e. sends resp
                        // immediately -> indeed response write is triggered on set, thus it will not wait for the
                        // other (i.e. now irrelevant) threads to finish, see artificial delay set within this commit
                        deferredResult.setResult(result);
                    }
                });
            }
        }
        LOGGER.debug("Launched all checker threads for cube: " + cube);
    }
}
