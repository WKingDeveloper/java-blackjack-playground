package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public abstract class Cards {

    private List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return this.cards;
    }

    public Integer calculateCards() {
        final int[] sumValue = {0};
        cards.stream().forEach(card -> sumValue[0] += card.getNumber().value);
        return sumValue[0];
    }
}
