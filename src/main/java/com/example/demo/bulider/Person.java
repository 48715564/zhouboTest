package com.example.demo.bulider;

/**
 * Created by bozhou on 2017/8/22.
 * 人
 */
public class Person {
    private final int age;
    private final String name;
    private final float height;
    private final float weight;
    public static class Bulider implements BuliderInter<Person>{
        private final int age;
        private final String name;
        private float height=0;
        private float weight=0;
        public Bulider(int age,String name){
            this.age = age;
            this.name = name;
        }
        public Bulider height(float height){
            this.height = height;
            return this;
        }
        public Bulider weight(float weight){
            this.weight = weight;
            return this;
        }
        public Person bulid(){
            return new Person(this);
        }
    }
    private Person(Bulider bulider){
        age = bulider.age;
        name = bulider.name;
        height = bulider.height;
        weight = bulider.weight;
    }
}
