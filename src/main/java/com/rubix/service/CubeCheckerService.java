package com.rubix.service;

import java.util.concurrent.ExecutionException;

import org.springframework.web.context.request.async.DeferredResult;

import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;

public interface CubeCheckerService {

    public void check(CubeDTO cube, DeferredResult<CubeEntity> deferredResult)
            throws InterruptedException, ExecutionException;
}
