package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayerCards;

public abstract class Player {

    private PlayerName name;
    private PlayerCards cards;
    private Money revenue;

    public Player(String playerName) {
        this.cards = new PlayerCards();
        this.name = new PlayerName(playerName);
        this.revenue = new Money("0");
    }

    public PlayerCards getCards() {
        return this.cards;
    }

    public abstract void getFirstDrawCards(Cards cards);

    public boolean hasBlackJack(){
        return this.cards.calculateCards() == 21;
    }

    public void addCard(Cards cards) {
        this.getCards().getCards().add(cards.getCards().get(0));
        cards.getCards().remove(0);
    }

    public Integer getRevenue() {
        return revenue.getMoney();
    }

    public void setRevenue(Money revenue) {
        this.revenue = revenue;
    }

    public String getCardsNames() {
        String cardNames = "";
        for(Card card : this.cards.getCards()){
            cardNames += ", " + card.getNumber().getValue().toString() +card.getPattern().getValue().toString();
        }
        cardNames = cardNames.replaceFirst(", ", "");
        return cardNames;
    }
}
