package com.example.demo.bulider;

/**
 * Created by bozhou on 2017/8/22.
 */
public class Main {
    public static void main(String[] args) {
        Person person = new Person.Bulider(1,"222").height(180).weight(100).bulid();
    }
}
