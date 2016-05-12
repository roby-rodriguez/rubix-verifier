package com.rubix.factory;

import java.util.List;

import com.rubix.constant.TestCubeConstants;
import com.rubix.dto.CubeDTO;
import com.rubix.entity.CubeEntity;

/**
 * For some reason Spring MVC mock didn't really work with Mockito mocks as return results...
 */
public class TestCubeFactory {

    public static CubeDTO getCubeMock(final List<String> faces, int size) {
        final CubeDTO cube = new CubeDTO();
        cube.setFaces(TestCubeConstants.CUBE_FACES);
        cube.setSize(size);
        return cube;
    }

    public static CubeEntity getCubeMock(String key) {
        final CubeEntity cube = new CubeEntity();
        cube.setKey(key);
        return cube;
    }
}
