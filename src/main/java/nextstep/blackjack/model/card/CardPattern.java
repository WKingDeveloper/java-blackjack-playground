package nextstep.blackjack.model.card;

import java.util.Arrays;

public enum CardPattern {
    DIAMOND("diamond","다이아"),
    HEART("heart","하트"),
    CLOVER("clover","클로버"),
    SPACE("space","스페이스");

    private final String pattern;

    public String getValue() {
        return value;
    }

    private String value;

    CardPattern(String pattern,String value) {
        this.pattern = pattern;
        this.value = value;
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
