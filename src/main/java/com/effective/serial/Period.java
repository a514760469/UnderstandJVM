package com.effective.serial;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Immutable 类
 * 不可变的时间周期类
 */
public class Period implements Serializable {

    private static final long serialVersionUID = 450043538951204399L;

    private final Date start;

    private final Date end;

    public Period(Date start, Date end) {
        // 保护性拷贝
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (start.compareTo(end) < 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
    }

    public Date getStart() {
        return new Date(start.getTime());
    }

    public Date getEnd() {
        return new Date(end.getTime());
    }

    private Object writeReplace() {
        return new SerializableProxy(this);
    }

    private Object readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }

    // 本类的序列化代理
    private static class SerializableProxy implements Serializable {

        private static final long serialVersionUID = -6521869756279813737L;

        private final Date start;

        private final Date end;

        SerializableProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        private Object readResolve() {
            return new Period(start, end);
        }
    }

}
