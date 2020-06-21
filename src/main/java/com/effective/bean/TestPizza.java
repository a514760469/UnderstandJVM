package com.effective.bean;

public class TestPizza {

    public static void main(String[] args) {

        NyPizza nyPizza = new NyPizza.Builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.HAM).addTopping(Pizza.Topping.SAUSAGE).build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(Pizza.Topping.HAM).sauceInside().build();

        System.out.println(nyPizza);
        System.out.println(calzone);
    }
}
