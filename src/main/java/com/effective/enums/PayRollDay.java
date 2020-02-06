package com.effective.enums;

/**
 * 策略枚举 模板
 *
 * 根据给定的某工人的基本工资以及当天的工作时间，来计算他当天的报酬。
 * 在5个工作日中，超过8小时的工作时间会产生加班工资，在节假日中所有工作都产生加班工资。
 */
public enum PayRollDay {

    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND),

    ;

    private final PayType payType;

    // 默认
    PayRollDay() {
        this(PayType.WEEKDAY);
    }

    PayRollDay(PayType payType) {
        this.payType = payType;
    }

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    private enum PayType {
        WEEKDAY {
            @Override
            int overtimePay(int minutesWorked, int payRate) {
                return minutesWorked <= MINS_PER_SHIFT ? 0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int minutesWorked, int payRate) {
                return minutesWorked * payRate / 2;
            }
        };

        private static final int MINS_PER_SHIFT = 8 * 60;

        int pay(int minutesWorked, int payRate) {
            int basePay = minutesWorked * payRate;
            return basePay + overtimePay(minutesWorked, payRate);
        }

        abstract int overtimePay(int minutesWorked, int payRate);
    }
}
