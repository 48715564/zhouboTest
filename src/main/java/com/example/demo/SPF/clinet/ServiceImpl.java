package com.example.demo.SPF.clinet;

import com.example.demo.SPF.Service;

/**
 * Created by bozhou on 2017/8/22.
 */
public class ServiceImpl implements Service {

    @Override
    public void add() {
        System.out.println("添加");
    }

    @Override
    public void delete() {
        System.out.println("删除");
    }
}
