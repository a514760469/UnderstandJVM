package com.effective.enums;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * Phase: 阶段
 */
public enum Phase {
    // 固    液       气
    SOLID, LIQUID, GAS;

    /**
     * Transition: 过渡
     * Map(起始阶段，Map(目标阶段，过度))
     */
    public enum Transition {
        /**
         * 融化
         */
        MELT(SOLID, LIQUID),
        /**
         * 冻结
         */
        FREEZE(LIQUID, SOLID),
        /**
         * 沸腾
         */
        BOIL(LIQUID, GAS),
        /**
         * 凝结
         */
        CONDENSE(GAS, LIQUID),
        /**
         * 升华
         */
        SUBLIME(SOLID, GAS),
        /**
         * 凝华
         */
        DEPOSIT(GAS, SOLID);

        /**
         * 起始阶段
         */
        private final Phase from;

        /**
         * 目标阶段
         */
        private final Phase to;

        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        /**
         * Map(起始阶段，Map(目标阶段，过度))
         *
         * Transition[]
         * groupingBy 按起始阶段分组    SOLID LIQUID GAS
         */
        private static final Map<Phase, Map<Phase, Transition>> map
                = Stream.of(values()).collect(
                    groupingBy(
                        t -> t.from,
                        () -> new EnumMap<>(Phase.class),
                        toMap(t -> t.to, t -> t, (x, y) -> y, () -> new EnumMap<>(Phase.class))
                    )
                );

        public static Transition from(Phase from, Phase to) {
            return map.get(from).get(to);
        }

    }

}
