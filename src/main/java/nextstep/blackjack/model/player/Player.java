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

    public PlayerCards getCards() {
        return this.cards;
    }

    public abstract void getFirstDrawCards(Cards cards);

    public boolean hasBlackJack(){
        return this.cards.calculateCards() == 21;
    }


}
