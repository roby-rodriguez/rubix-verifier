package com.rubix.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rubix.config.UnitTestConfig;
import com.rubix.constant.TestCubeConstants;
import com.rubix.entity.CubeEntity;

@ActiveProfiles({"test", "unit" })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UnitTestConfig.class)
public abstract class CubeRepositoryTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Before
    public void setup() {
        importData(TestCubeConstants.COLLECTION, TestCubeConstants.EXISTING);
    }

    private void importData(String collection, String... keys) {
        for (String key : keys) {
            final CubeEntity cube = new CubeEntity();
            cube.setKey(key);
            mongoOperations.save(cube, collection);
        }
    }
}
