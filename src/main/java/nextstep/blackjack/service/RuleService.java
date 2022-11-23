package nextstep.blackjack.service;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.Users;

public class RuleService {

    private Cards gameCards;

    public RuleService() {
        this.gameCards = new Cards();
        this.gameCards.setGameCards();
    }

    public void firstDraw(Users users, Dealer dealer) {
        users.addAllUsersTwoCards(this.gameCards);
        dealer.addTwoCards(this.gameCards);
    }
}
