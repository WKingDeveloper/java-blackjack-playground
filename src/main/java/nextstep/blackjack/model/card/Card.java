package nextstep.blackjack.model.card;

public class Card {

    private CardNumber number;
    private CardPattern pattern;

    public Card() {
    }

    public Card(CardNumber number, CardPattern pattern) {
        this.number = number;
        this.pattern = pattern;
    }
}
