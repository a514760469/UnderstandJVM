package com.test;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author zhanglifeng
 * @since 2020-09-11 16:52
 */
public class DiffTest {

    public static void main(String[] args) {

//        double a = 0.96;
//        double b = 0.06;
//        System.out.println(a - b);
//        BigDecimal subtract = BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b));
//        System.out.println(subtract.doubleValue());
        BigDecimal a = new BigDecimal("");
        BigDecimal divide = a.divide(new BigDecimal("3600"), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            strings.add("1");
        }

        for (int i = 0, j = strings.size(); i < j; i++) {

        }

    }
}
