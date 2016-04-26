package com.rubix.repository;

import com.rubix.entity.CubeEntity;

public interface CubeCustomRepository {

    CubeEntity findByKey(String key);
}
