package com.rubix.repository.impl;

import com.rubix.entity.CubeEntity;
import com.rubix.repository.CubeCustomRepository;

public class CubeRepositoryImplMock implements CubeCustomRepository {

    @Override
    public CubeEntity findByKey(String key, String collection) {
        // throw new RuntimeException(); todo -> this doesn't seem to work
        CubeEntity c = new CubeEntity();
        c.setKey("MUIE");
        return c;
    }
}
