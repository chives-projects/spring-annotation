package com.csc.demo1.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-10-26 10:30
 * @Version: 1.0
 */
@Component
public class FactoryBeanTest implements FactoryBean<FactoryBeanTest.FactoryInnerBeanTest> {

    @Override
    public FactoryBeanTest.FactoryInnerBeanTest getObject() throws Exception {
        System.out.println("[FactoryBean] getObject");
        return new FactoryBeanTest.FactoryInnerBeanTest();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanTest.FactoryInnerBeanTest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    class FactoryInnerBeanTest {
    }
}
