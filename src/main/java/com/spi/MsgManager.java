package com.spi;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息驱动管理：
 * 用来根据不同消息驱动的实现，获得不同驱动
 */
public class MsgManager {

    private static final Map<String, Class<? extends IMsg>> REGISTER_MAP = new HashMap<>();

    public static void register(String protocol, Class<? extends IMsg> cls) {
        REGISTER_MAP.put(protocol, cls);
    }

    public static IMsg getMsgConnection(String protocol) {

        try {
            return REGISTER_MAP.get(protocol).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
