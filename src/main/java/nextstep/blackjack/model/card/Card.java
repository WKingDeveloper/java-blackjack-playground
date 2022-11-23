package nextstep.blackjack.model.card;

public class Card {

    private CardNumber number;
    private CardPattern pattern;

    public Card(CardNumber number, CardPattern pattern) {
        this.number = number;
        this.pattern = pattern;
    }


    public Integer getValue() {
        return number.getValue();
    }

    public String getCardName() {
        return number.getName() + pattern.getName();
    }
}
