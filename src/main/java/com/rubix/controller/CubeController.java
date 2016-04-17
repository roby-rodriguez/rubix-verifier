package com.rubix.controller;

import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;
import com.rubix.service.CubeCheckerService;

@RestController
public class CubeController {
	
	@Autowired
	private CubeCheckerService cubeCheckerService;

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public CubeEntity checkExists(@Valid @RequestBody final CubeDTO cube) throws InterruptedException, ExecutionException {
		// todo add application exception that wraps every other ex just like ApplicationException from pisc
		return cubeCheckerService.check(cube);
	}
}
