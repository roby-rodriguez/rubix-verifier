package com.rubix.dto;

import java.util.ArrayList;
import java.util.List;

import com.rubix.constant.CubeConstants;

public class CubeDTO {

    private List<String> faces;

    public CubeDTO() {
        this.faces = new ArrayList<>();
    }

    public CubeDTO(final CubeDTO cubeDTO) {
        this.faces = new ArrayList<>(cubeDTO.getFaces());
    }

    public List<String> getFaces() {
        return faces;
    }

    public void setFaces(final List<String> faces) {
        this.faces = faces;
    }

    // todo create/add validation annotations instead for these two parameters
    public CubeDTO transform(final String labeling, final String permutation) {
        CubeDTO transformed = new CubeDTO(this);
        if (labeling != null && !labeling.isEmpty()) {
            List<String> relabeled = new ArrayList<>();
            for (String face : transformed.getFaces()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < face.length(); i++) {
                    sb.append(labeling.charAt(CubeConstants.ORIGINAL.indexOf(face.charAt(i))));
                }
                relabeled.add(sb.toString());
                transformed.setFaces(relabeled);
            }
        }
        // todo 1) in each iteration you could reuse the 'relabeled' obtained in the previous step
        // todo 2) why not break this into two operations + use java 8 streams
        if (permutation != null && !permutation.isEmpty()) {
            List<String> permuted = new ArrayList<>();
            for (int i = 0; i < permutation.length(); i++) {
                permuted.add(transformed.getFaces().get(CubeConstants.ORIGINAL.indexOf(permutation.charAt(i))));
            }
            transformed.setFaces(permuted);
        }
        return transformed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < faces.size() - 1; i++) {
            sb.append(faces.get(i));
            sb.append(CubeConstants.DELIMITER);
        }
        sb.append(faces.get(i));
        return sb.toString();
    }
}
