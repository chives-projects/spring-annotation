package com.csc.demo1;


import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description:
 * @PackageName: com.csc.demo1
 * @Author: csc
 * @Create: 2020-07-13 13:35
 * @Version: 1.0
 */
public class EasyTest {
    @Test
    public void t1() throws ClassNotFoundException, NoSuchFieldException {
        HashMap<String, String> map = new HashMap(16);
        Class<?> obj = Class.forName("java.util.HashMap");

        Field[] fields = obj.getFields();
        for (Field field : fields)
            System.out.println(field.getName());
        map.put("k", "v");
        map.forEach((k, v) -> System.out.println(k + "\t" + v));
    }
}
