package com.rubix.constant;

import java.util.Arrays;
import java.util.List;

public final class CubeConstants {

    public enum LABEL {
        a, b, c, d, e, f
    };

    public static final String DELIMITER = "|";

    public static final String ORIGINAL = "abcdef";

    /**
     * Given original state 'a|b|c|d|e|f' (representing the cube), the following permutations are equivalent: (ie. these
     * correspond to viewing the cube from all possible angles)
     */
    public static final List<String> CUBE_PERMUTATIONS =
            Arrays.asList("bcdaef", "cdabef", "dabcef",
                    "badcfe", "adcbfe", "dcbafe", "cbadfe",

                    "fceabd", "ceafbd", "eafcbd", "afcebd",
                    "faecdb", "aecfdb", "ecfadb", "cfaedb",

                    "fbedac", "bedfac", "edfbac", "dfbeac",
                    "fdebca", "debfca", "ebfdca", "bfdeca"
            );
    
    /**
     * := CUBE_PERMUTATIONS.size() ^ 2
     */
    public static final int TOTAL_NR_OF_COMBINATIONS = 529;
}
