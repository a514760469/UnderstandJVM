package com.effective.bean;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * 有层次结构的builder
 */
public abstract class Pizza {

    // 披萨的顶层
    public enum Topping {
        //火腿 蘑菇      洋葱   胡椒     香肠
        HAM, MUSHROOM, ONION, PEPPER, SAUSAGE
    }

    final Set<Topping> toppings;

    /**
     * 抽象 泛型builder 带有递归类型T
     */
    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping (Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }
        // 子类必须重写这个方法 return this;
        protected abstract T self();
        abstract Pizza build();
    }

    public Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}
