package com.algorithm.recursion;

import java.util.TreeMap;

/**
 * @author zhanglifeng
 * @since 2023-09-01
 */
public class NumberOfAtom {


    public static void main(String[] args) {

        String res = new NumberOfAtom().countOfAtoms("Mg(OH)2");
        System.out.println("res = " + res);
    }

    public String countOfAtoms(String formula) {
        StringBuilder sb = new StringBuilder();
        TreeMap<String, Integer> map = f(formula.toCharArray(), 0);
        for (String key : map.keySet()) {
            sb.append(key);
            int value = map.get(key);
            if (value > 1) {
                sb.append(value);
            }
        }
        return sb.toString();
    }

    int where = 0;

    public TreeMap<String, Integer> f(char[] c, int i) {
        StringBuilder name = new StringBuilder();
        TreeMap<String, Integer> ans = new TreeMap<>();
        TreeMap<String, Integer> pre = null;

        int count = 0;

        while (i < c.length && c[i] != ')') {
            if (c[i] >= 'A' && c[i] <= 'Z' || c[i] == '(') {
                fill(ans, name, count, pre);
                // 清空 历史name，pre 表， count
                name.setLength(0);
                pre = null;
                count = 0;

                if (c[i] >= 'A' && c[i] <= 'Z') {
                    name.append(c[i++]);
                } else {
                    pre = f(c, i + 1);
                    i = where + 1;
                }

            }
            else if (c[i] >= 'a' && c[i] <= 'z') {
                name.append(c[i++]);
            }
            else if (c[i] >= '0' && c[i] <= '9') {
                count = count * 10 + (c[i++] - '0');
            }
        }
        fill(ans, name, count, pre);
        where = i;
        return ans;
    }

    public void fill(TreeMap<String, Integer> ans, StringBuilder name, int count, TreeMap<String, Integer> pre) {
        if (name.length() > 0 || pre != null) {
            count = count == 0 ? 1 : count;
            if (name.length() > 0) {
                String key = name.toString();
                ans.put(key, ans.getOrDefault(key, 0) + count);
            }
            else {
                for (String key : pre.keySet()) {
                    ans.put(key, ans.getOrDefault(key, 0) + pre.get(key) * count);
                }
            }
        }
    }
}
