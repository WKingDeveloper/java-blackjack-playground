package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Cards;

public class Dealer extends Player{
    public Dealer() {
        super("dealer");
    }

    @Override
    public void getFirstDrawCards(Cards cards) {
        for (int i = 0; i < 2; i++) {
            this.getCards().getCards().add(cards.getCards().get(i));
            cards.getCards().remove(i);
        }
    }


    public boolean validCardsValue(Cards cards) {
        if (getCards().calculateCards() < 17) {
            addCard(cards);
            return true;
        }
        return false;
    }
}
