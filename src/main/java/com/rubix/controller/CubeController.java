package com.rubix.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rubix.dto.CubeDTO;

@RestController
public class CubeController {

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Boolean checkExists(@Valid @RequestBody final CubeDTO cube) {
		return true;
	}
}
