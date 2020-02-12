package com.effective.enums;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * 加减乘除操作
 */
public enum BasicOperation implements Operation {

    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    private static final Map<String, BasicOperation> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e -> e));

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static Optional<BasicOperation> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public static BasicOperation inverse(BasicOperation op) {
        switch (op) {
            case PLUS: return BasicOperation.MINUS;
            case MINUS: return BasicOperation.PLUS;
            case TIMES: return BasicOperation.DIVIDE;
            case DIVIDE: return BasicOperation.TIMES;
            default:
                throw new AssertionError("Unknown operation: " + op);
        }
    }

//    public abstract double apply(double x, double y);

}
