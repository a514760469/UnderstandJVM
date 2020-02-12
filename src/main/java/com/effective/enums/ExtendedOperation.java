package com.effective.enums;

public enum ExtendedOperation implements Operation {
    /**
     * 求幂
     */
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },

    /**
     * 求余
     */
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    }
    ;

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
