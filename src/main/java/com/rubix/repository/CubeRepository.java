package com.rubix.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rubix.entity.CubeEntity;

public interface CubeRepository extends MongoRepository<CubeEntity, String> {

	public CubeEntity findByKey(String key); 
}
