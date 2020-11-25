package com.csc.demo1.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-11-25 10:24
 * @Version: 1.0
 */
@Component
public class DisposableBeanTest implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("[DisposableBean] DisposableBeanTest");
    }
}
