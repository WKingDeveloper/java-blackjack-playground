package nextstep.blackjack.model.card;

public class Card {
    private CardNumber number;
    private CardPattern pattern;

    public Card(String number, String pattern) {
        this.number = CardNumber.findCardNumber(number);
        this.pattern = CardPattern.findCardPattern(pattern);
    }
}
