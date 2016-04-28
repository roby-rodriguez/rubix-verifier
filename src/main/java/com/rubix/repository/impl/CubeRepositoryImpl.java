package com.rubix.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public CubeEntity findByKey(String key, String collection) {
        final Query query = Query.query(Criteria.where("key").is(key));
/*        
        if ("cdee|fecb|abff|efad|dcdc|baba".equals(key) || "abff|fecb|cdee|efad|baba|dcdc".equals(key)) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
*/
        return mongoOperations.findOne(query, CubeEntity.class, collection);
    }
}
