package com.rubix.constant;

import java.util.Arrays;
import java.util.List;

public final class TestCubeConstants {

    /**
     * test cube faces
     */
    public static final List<String> CUBE_FACES = Arrays.asList("cece", "fabb", "afaf", "ecdd", "dbfc", "bdea");

    /**
     * test cube size
     */
    public static final String COLLECTION = CubeConstants.getCollection(CubeConstants.SIZE4);

    /**
     * db existing cube states as keys
     */
    public static final String[] EXISTING =
            {"fcdd|cfcf|eabb|aeae|bdfa|dbec", "dfdf|cafb|bebe|aced|fdaa|ebcc", "dbfa|eadd|bdec|fcbb|cfcf|aeae" };

    /**
     * non-existing db keys
     */
    public static final String[] NON_EXISTING =
            {"fcbb|bdec|eadd|dbfa|aeae|cfcf", "dbfa|cfcf|bdec|aeae|fcbb|eadd", "bdec|fcbb|dbfa|eadd|cfcf|aeae" };
}
