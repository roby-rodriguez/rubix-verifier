package com.rubix.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rubix.config.UnitTestConfig;
import com.rubix.constant.CubeConstants;
import com.rubix.entity.CubeEntity;

@ActiveProfiles({"test", "unit" })
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UnitTestConfig.class)
public abstract class CubeRepositoryTest {

    public static final String COLLECTION = CubeConstants.getCollection(CubeConstants.SIZE4);

    public static final String[] EXISTING =
            {"fcdd|cfcf|eabb|aeae|bdfa|dbec", "dfdf|cafb|bebe|aced|fdaa|ebcc", "dbfa|eadd|bdec|fcbb|cfcf|aeae" };

    public static final String[] NON_EXISTING =
            {"fcbb|bdec|eadd|dbfa|aeae|cfcf", "dbfa|cfcf|bdec|aeae|fcbb|eadd", "bdec|fcbb|dbfa|eadd|cfcf|aeae" };

    @Autowired
    private MongoOperations mongoOperations;

    @Before
    public void setup() {
        importData(CubeRepositoryTest.COLLECTION, CubeRepositoryTest.EXISTING);
    }

    private void importData(String collection, String... keys) {
        for (String key : keys) {
            final CubeEntity cube = new CubeEntity();
            cube.setKey(key);
            mongoOperations.save(cube, collection);
        }
    }
}
