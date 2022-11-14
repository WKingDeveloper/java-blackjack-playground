package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayerCards;

import java.util.List;
import java.util.Random;

public class Player {
    private PlayerName name;
    private Money batMoney;
    private PlayerCards cards;

    public Player(String playerName) {
        this.cards = new PlayerCards();
        this.name = new PlayerName(playerName);
    }

    public void setBatMoney(String money) {
        this.batMoney = new Money(money);
    }

    public Integer getBatMoney() {
        return this.batMoney.getMoney();
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
}
