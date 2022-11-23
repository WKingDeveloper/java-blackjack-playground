package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

import java.util.List;

public abstract class Player {

    private Cards cards;
    private PlayerName name;
    private Money batMoney;
    private Money revenueMoney;

    public Player(String name) {
        this.name = new PlayerName(name);
        this.batMoney = new Money("0");
        this.revenueMoney = new Money("0");
        this.cards = new Cards();

    }

    public String getName() {
        return this.name.getName();
    }

    public void setBatMoney(String money){
        this.batMoney = new Money(money);
    }

    public Integer getBatMoney() {
        return batMoney.getMoney();
    }

    public List<Card> getCards() {
        return this.cards.getCards();
    }

    public void addTwoCards(Cards gameCards) {
        for (int i = 0; i < 2; i++) {
            this.cards.getCards().add(gameCards.getCards().get(i));
            gameCards.getCards().remove(i);
        }
    }

    public Integer getRevenueMoney() {
        return this.revenueMoney.getMoney();
    }


    public Integer getCardsValue() {
        return this.cards.calculateCardsValue();
    }

    public void setRevenueMoney(Integer money) {
        this.revenueMoney = new Money(money);
    }
}
