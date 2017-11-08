package com.example.demo.SPF.clinet;

import com.example.demo.SPF.Service;
import com.example.demo.SPF.Services;

/**
 * Created by bozhou on 2017/8/22.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.example.demo.SPF.clinet.ProviderDriver");
            Service service = Services.getInstance("oracle");
            service.add();
            service.delete();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
