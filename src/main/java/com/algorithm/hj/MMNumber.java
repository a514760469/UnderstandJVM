package com.algorithm.hj;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhanglifeng
 * @since 2023-08-18
 */
public class MMNumber {

    public static void main(String[] args) {


//        System.out.println(process("fdsieii512321 adagrn 45"));
//        int anInt = Integer.parseInt("AA", 16);
//        System.out.println(anInt);


        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        //总共有128个字符。字需要用128位
        BitSet bitSet = new BitSet(128);
        for (char c : line.toCharArray()) {
            //判断字符c是否已出现
            if (!bitSet.get(c)) {
                //未出现就设置为已出现
                bitSet.set(c);
            }
        }
        //统计有多少字符已出现过
        System.out.println(bitSet.cardinality());
    }


    static List<String> process(String str) {
        List<String> res = new ArrayList<>();
        int num = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (num >= 8) {
                res.add(sb.toString());
                num = 0;
                sb = new StringBuilder();
            }
            sb.append(c);
            num ++;
        }
        int x = 8 - sb.length();
        if (x > 0) {
            for (int i = 0; i < x; i++) {
                sb.append(0);
            }
        }
        res.add(sb.toString());
        return res;
    }

}
