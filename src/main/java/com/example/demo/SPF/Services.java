package com.example.demo.SPF;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bozhou on 2017/8/22.
 */
public class Services {
    private Services(){}
    private static final Map<String,Provider> providers = new ConcurrentHashMap<>();
    private static final String DEFAULT_PROVIDER_NAME="<def>";
    public static void registerDefaultProvider(Provider provider){
        providers.put(DEFAULT_PROVIDER_NAME,provider);
    }
    public static void registerProvider(String name,Provider provider){
        providers.put(name,provider);
    }
    public static Service getInstance(){
        return providers.get(DEFAULT_PROVIDER_NAME).newService();
    }
    public static Service getInstance(String name){
        Provider provider = providers.get(name);
        if(provider==null){
            throw new IllegalArgumentException("参数不正确！");
        }
        return provider.newService();
    }
}
