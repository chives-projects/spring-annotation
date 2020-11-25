package com.csc.demo1.config;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-11-25 10:16
 * @Version: 1.0
 */
@Component
public class SmartInitializingSingletonTest implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("[SmartInitializingSingletonTest]");
    }
}
