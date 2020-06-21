package com.effective.bean;

public class TestNutritionFacts {
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240, 2)
                .calories(100).sodium(35).carbohydrate(27).build();
        System.out.println(cocaCola);
    }
}
