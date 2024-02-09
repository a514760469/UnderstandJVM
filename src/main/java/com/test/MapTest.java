package com.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglifeng
 * @since 2021-04-02
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        Bean bean = new Bean();
        bean.setBalance(BigDecimal.ZERO);
        map.put("beforeAccountBalance", bean.getBalance());

        System.out.println(map);
        System.out.println(bean);

        bean.setBalance(BigDecimal.ONE);
        System.out.println(map);
    }


    static class Bean {

        private BigDecimal balance;

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "balance=" + balance +
                    '}';
        }
    }
}
