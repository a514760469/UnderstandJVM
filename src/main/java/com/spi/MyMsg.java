package com.spi;

public class MyMsg implements IMsg {

    static {
        System.out.println("MyMsg static register..");
        MsgManager.register("my", MyMsg.class);
    }

    @Override
    public void send(String msg) {
        System.out.println("[通过MyMsg实现加载]" + msg);
    }
}
