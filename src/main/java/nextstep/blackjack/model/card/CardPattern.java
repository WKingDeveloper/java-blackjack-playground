package nextstep.blackjack.model.card;

import java.util.Arrays;

public enum CardPattern {
    CLOVER("clover"),
    DIAMOND("diamond"),
    HEART("heart"),
    SPADE("spade");


    private final String pattern;

    CardPattern(String pattern) {
        this.pattern = pattern;
    }

    public static CardPattern findCardPattern(String name) {
        return Arrays.stream(CardPattern.values()).filter(value -> value.getPattern().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalAccessError());
    }

    public String getPattern() {
        return pattern;
    }
}
