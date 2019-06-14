package com.jvm.test;

import java.util.ArrayList;
import java.util.List;

public class JvmTest {

    static class OOMObj {

    }

    /**
     * -verbose:gc
     * -verbose:gc -Xms20M -Xmx20M 
     * -XX:+HeapDumpOnOutOfMemoryError 出现OOM后生产Dump文件
     * -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        List<OOMObj> list = new ArrayList<OOMObj>();
        while (true) {
            list.add(new OOMObj());
        }
    }

}
