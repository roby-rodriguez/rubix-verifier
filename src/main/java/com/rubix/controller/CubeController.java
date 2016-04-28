package com.rubix.controller;

import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.service.CubeCheckerService;

@RestController
public class CubeController {

    @Autowired
    private CubeCheckerService cubeCheckerService;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<CubeEntity> checkExists(@Valid @RequestBody final CubeDTO cube)
            throws InterruptedException, ExecutionException {
        // todo add application exception that wraps every other ex just like ApplicationException from pisc
        final DeferredResult<CubeEntity> deferredResult = new DeferredResult<>();
        // initiate processing in another thread and let go of the current thread to handle some more requests
        cubeCheckerService.check(cube, deferredResult);
        return deferredResult;
    }
}
