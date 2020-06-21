package com.effective.bean;

/**
 * 半月型 pizza ， 需要制定酱汁内置还是外置
 */
public class Calzone extends Pizza {

    private final boolean sauceInside;

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }

    @Override
    public String toString() {
        return "Calzone{" +
                "sauceInside=" + sauceInside +
                ", toppings=" + toppings +
                '}';
    }

    public static class Builder extends Pizza.Builder<Builder> {

        private boolean sauceInside = false;

        public Builder sauceInside() {
            this.sauceInside = true;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Calzone build() {
            return new Calzone(this);
        }
    }
}
