package com.rubix.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cube#{foo}")
public class CubeEntity {

    private String key;

    private Long step;

    private String parent;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
