package com.rubix.service.impl;

import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rubix.constant.CubeConstants;
import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.execution.CubeCheckerTask;
import com.rubix.service.CubeCheckerService;

/**
 * http://www.leveluplunch.com/java/tutorials/026-asynchronous-native-java-method-calls-spring/
 * http://stackoverflow.com/questions/2269126/using-spring-threading-and-taskexecutor-how-do-i-know-when-a-thread-is-
 * finished?rq=1 http://helenaedelson.com/?p=432
 * http://stackoverflow.com/questions/852743/any-good-spring-threading-with-a-taskexecutor-examples
 * http://geowarin.github.io/completable-futures-with-spring-async.html
 */
@Component
public class CubeCheckerServiceImpl implements CubeCheckerService {

    private final static Logger LOGGER = Logger.getLogger(CubeCheckerServiceImpl.class);

    @Autowired
    private CubeCheckerTask cubeCheckerTask;

    @Override
    public CubeEntity check(CubeDTO cube) throws InterruptedException, ExecutionException {
        LOGGER.info("Started check for cube: " + cube);
        for (String label : CubeConstants.CUBE_PERMUTATIONS) {
            for (String permutation : CubeConstants.CUBE_PERMUTATIONS) {
                final CubeEntity checked = cubeCheckerTask.check(cube, label, permutation).get();
                if (checked != null)
                    return checked;
            }
        }
        LOGGER.info("Ended check for cube: " + cube);
        return null;
    }
}
