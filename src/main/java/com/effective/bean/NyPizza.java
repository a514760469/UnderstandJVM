package com.effective.bean;

import java.util.Objects;

/**
 * 纽约风味经典pizza
 * 需要一个尺寸参数
 */
public class NyPizza extends Pizza {

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    private final Size size;

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }

    @Override
    public String toString() {
        return "NyPizza{" +
                "size=" + size +
                ", toppings=" + toppings +
                '}';
    }

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }


        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }
    }

}
