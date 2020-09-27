package com.java8.stream.lambda;

import com.java8.function.CollectorsUtil;
import com.java8.stream.lambda.bean.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhanglifeng
 * @since 2020-09-27 9:40
 */
public class GroupingBy {

    private static final List<Person> list = new ArrayList<Person>();
    static {
        list.add(new Person("a", new BigDecimal("21")));
        list.add(new Person("a", new BigDecimal("10")));
    }

    @Test
    public void testGroupingByBigDecimal() {
        Map<String, BigDecimal> amountSum = list.stream().collect(Collectors.groupingBy(Person::getName, CollectorsUtil.summingBigDecimal(Person::getAmount)));
        System.out.println(amountSum);
        Map<String, BigDecimal> collect =
                list.stream().collect(Collectors.groupingBy(Person::getName, CollectorsUtil.summingBigDecimal(Person::getAmount)));
    }
}
