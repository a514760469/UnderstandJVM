package com.spi;

import org.junit.Test;

public class Invoker {

    @Test
    public void test() throws ClassNotFoundException {
        String jdbcUrl = "hello";
        Class.forName("com.spi.MyMsg");
        Class.forName("com.spi.YourMsg");
        IMsg my = MsgManager.getMsgConnection("my");
        IMsg your = MsgManager.getMsgConnection("your");
        my.send(jdbcUrl);
        your.send(jdbcUrl);
    }
}
