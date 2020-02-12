package com.effective.enums;

public class PhaseTest {

    public static void main(String[] args) {
        Phase.Transition transition = Phase.Transition.from(Phase.GAS, Phase.LIQUID);
        System.out.println(transition);

    }
}
