package com.spi;

public class YourMsg implements IMsg {

    static {
        System.out.println("YourMsg static register..");
        MsgManager.register("your", YourMsg.class);
    }

    @Override
    public void send(String msg) {
        System.out.println("[通过YourMsg实现加载]" + msg);
    }

}
