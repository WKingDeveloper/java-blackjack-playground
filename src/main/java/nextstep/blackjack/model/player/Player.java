package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayerCards;

import java.util.List;

public abstract class Player {

    private PlayerName name;
    private PlayerCards cards;

    public Player(String playerName) {
        this.cards = new PlayerCards();
        this.name = new PlayerName(playerName);
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public void getFirstDrawCards(Cards cards) {

        for (int i = 0; i < 2; i++) {
            this.cards.getCards().add(cards.getCards().get(i));
            cards.getCards().remove(i);
        }

    }

    public boolean hasBlackJack(){
        return this.cards.calculateCards() == 21;
    };
}
