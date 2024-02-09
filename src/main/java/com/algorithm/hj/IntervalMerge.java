package com.algorithm.hj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanglifeng
 * @since 2023-08-23
 */
public class IntervalMerge {

    public static void main(String[] args) {

        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(10, 30));
        intervals.add(new Interval(20, 60));
        intervals.add(new Interval(20, 59));
        intervals.add(new Interval(80, 100));
        intervals.add(new Interval(150,180));
        mergeInterval(intervals);

    }


    public static void mergeInterval(List<Interval> list) {

        list.sort((o1, o2) -> {
            if (o1.start > o2.start) {
                return 1;
            } else if (o1.start < o2.start) {
                return -1;
            }
            return 0;
        });

        List<Interval> res = new ArrayList<>();

        int i = 0;
        while (i < list.size()) {
            Interval i1 = list.get(i);
            Interval r = new Interval(i1.start, i1.end);// 初始化
            int j = i + 1;
            while (j < list.size()) {
                Interval i2 = list.get(j);
                if (i2.start <= r.end) {
                    if (i2.end >= r.end) {
                        r.end = i2.end;
                    }
                    j++;
                } else {
                    break;
                }
            }
            res.add(r);
            i = j;
        }
        System.out.println(res);
    }

    public static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
}
