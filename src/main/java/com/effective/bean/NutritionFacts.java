package com.effective.bean;

/**
 * 营养成分 类，设计一个不可变类， builder模式
 */
public class NutritionFacts {

    private final int servingSize;// 每一份量
    private final int servings;   // 份
    private final int calories;   // 卡路里
    private final int fat;        // 脂肪
    private final int sodium;     // 钠
    private final int carbohydrate;// 碳水化合物

    private NutritionFacts(int servingSize, int servings,
                           int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    private NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.sodium = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    public int getServingSize() {
        return servingSize;
    }

    public int getServings() {
        return servings;
    }

    public int getCalories() {
        return calories;
    }

    public int getFat() {
        return fat;
    }

    public int getSodium() {
        return sodium;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    @Override
    public String toString() {
        return "NutritionFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sodium=" + sodium +
                ", carbohydrate=" + carbohydrate +
                '}';
    }

    public static class Builder {
        // Required parameters 必要的参数
        private final int servingSize;
        private final int servings;

        // Optional parameters
        private int calories = 0;
        private int fat = 0;
        private int sodium;
        private int carbohydrate = 0;


        public Builder(int servingSize, int servings) {
            // 检查参数的有效性  抛出 IllegalArgumentException
            this.servingSize  = servingSize;
            this.servings     = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat) {
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium) {
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
}
