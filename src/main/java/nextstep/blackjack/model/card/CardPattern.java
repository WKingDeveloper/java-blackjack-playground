package nextstep.blackjack.model.card;

import java.util.Arrays;

public enum CardPattern {
    CLOVER("clover","클로버"),
    DIAMOND("diamond","다이아몬드"),
    HEART("heart","하트"),
    SPADE("spade","스페이스");


    private final String pattern;
    private final String name;

    CardPattern(String pattern, String name) {
        this.pattern = pattern;
        this.name = name;
    }

    public static CardPattern findCardPattern(String name) {
        return Arrays.stream(CardPattern.values()).filter(value -> value.getPattern().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalAccessError());
    }

    public String getPattern() {
        return pattern;
    }

    public String getName() {
        return name;
    }
}
