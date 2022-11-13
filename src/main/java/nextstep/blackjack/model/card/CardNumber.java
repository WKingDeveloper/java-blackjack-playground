package nextstep.blackjack.model.card;

import java.util.Arrays;

public enum CardNumber {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String number;

    CardNumber(String number) {
        this.number = number;
    }

    public static CardNumber findCardNumber(String number) {
        return Arrays.stream(CardNumber.values())
                .filter(cardNumber -> cardNumber.getNumber().equals(number))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getNumber() {
        return number;
    }
}
