package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public abstract class Cards {

    List<Card> cards = new ArrayList<>();
    public List<Card> getCards() {
        return this.cards;
    }

}
