package nextstep.blackjack.model.card;

import java.util.Arrays;

public enum CardNumber {
    ACE("A",1),
    TWO("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J",10),
    QUEEN("Q",10),
    KING("K",10);

    private final String name;
    private final int score;

    CardNumber(final String name, final int score) {
        this.name = name;
        this.score = score;
    }

    public static CardNumber findCardNumber(String name) {
        return Arrays.stream(CardNumber.values()).filter(value -> value.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);

    }

    public String getName() {
        return name;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int getScore() {
        return score;
    }

}
