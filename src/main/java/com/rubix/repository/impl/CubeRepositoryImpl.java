package com.rubix.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.rubix.entity.CubeEntity;
import com.rubix.repository.CubeCustomRepository;
// import com.rubix.repository.CubeRepository;

@Repository
public class CubeRepositoryImpl implements CubeCustomRepository {

    // @Autowired
    // private CubeRepository cubeRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public CubeEntity findByKey(String key) {
        // TODO Auto-generated method stub
        return null;
    }
}
