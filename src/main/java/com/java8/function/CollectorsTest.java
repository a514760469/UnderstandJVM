package com.java8.function;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhanglifeng
 * @date 2019/11/7/0007
 */
public class CollectorsTest {

    public static void main(String[] args) {
        List<BigDecimal> list = Arrays.asList(new BigDecimal("2.0"), new BigDecimal("3.0"), new BigDecimal("9.6"));
        BigDecimal res = list.stream().collect(CollectorsUtil.summingBigDecimal(bigDecimal -> bigDecimal));
        System.out.println(res);
//        Arrays.stream(records).parallel().collect(Collectors.groupingBy(
//                Record::getType, CollectorsUtil.summingBigDecimal(Record::getAmount)));
    }
}
