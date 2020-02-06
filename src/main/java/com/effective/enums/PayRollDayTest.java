package com.effective.enums;

public class PayRollDayTest {
    public static void main(String[] args) {
        int pay = PayRollDay.MONDAY.pay(500, 10);
        System.out.println(pay);

        // 枚举的序号
//        System.out.println(PayRollDay.FRIDAY.ordinal());
    }
}
