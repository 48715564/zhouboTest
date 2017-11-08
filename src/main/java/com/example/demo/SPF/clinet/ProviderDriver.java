package com.example.demo.SPF.clinet;

import com.example.demo.SPF.Provider;
import com.example.demo.SPF.Service;
import com.example.demo.SPF.Services;

/**
 * Created by bozhou on 2017/8/22.
 */
public class ProviderDriver implements Provider {
    static{
        Services.registerProvider("oracle",new ProviderDriver());
    }

    public Service newService() {
        return new ServiceImpl();
    }
}
