package nextstep.blackjack.model.card;

import java.util.Arrays;

public enum CardPattern {
    DIAMOND("diamond"),
    HEART("heart"),
    CLOVER("clover"),
    SPACE("space");

    private final String pattern;

    CardPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public static CardPattern findCardPattern(String pattern) {
        return Arrays.asList(CardPattern.values()).stream()
                .filter(cardPattern -> cardPattern.getPattern().equals(pattern))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
