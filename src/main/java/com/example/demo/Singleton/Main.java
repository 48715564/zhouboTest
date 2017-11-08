package com.example.demo.Singleton;

/**
 * Created by bozhou on 2017/8/22.
 */
public class Main {
    public static void main(String[] args) {
        SingletonEnum.INSTANCE.setName("1111");
        System.out.println(SingletonEnum.INSTANCE.getName());
    }
}
