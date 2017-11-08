package com.example.demo.Singleton;

import java.io.Serializable;

/**
 * Created by bozhou on 2017/8/22.
 */
public enum SingletonEnum implements Serializable{
    INSTANCE;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
