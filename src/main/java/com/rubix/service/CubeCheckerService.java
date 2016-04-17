package com.rubix.service;

import java.util.concurrent.ExecutionException;

import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;

public interface CubeCheckerService {

	public CubeEntity check(CubeDTO cube) throws InterruptedException, ExecutionException;
}
