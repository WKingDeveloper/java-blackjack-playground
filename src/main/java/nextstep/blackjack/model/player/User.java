package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Cards;

public class User extends Player{

    private Money batMoney;

    public User(String playerName) {
        super(playerName);
    }

    @Override
    public void getFirstDrawCards(Cards cards) {
        for (int i = 0; i < 2; i++) {
            this.getCards().getCards().add(cards.getCards().get(i));
            cards.getCards().remove(i);
        }
    }

    public void setBatMoney(String money) {
        this.batMoney = new Money(money);
    }

    public Integer getBatMoney() {
        return this.batMoney.getMoney();
    }

}
