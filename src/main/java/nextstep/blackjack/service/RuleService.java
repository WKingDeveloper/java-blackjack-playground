package nextstep.blackjack.service;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;

public class RuleService {

    private Cards gameCards;

    public RuleService() {
        this.gameCards = new Cards();
        this.gameCards.setGameCards();
    }

    public void addCardsByPlayer(User user) {
        Card card = this.gameCards.getCards().get(0);
        user.addCard(card);
        this.gameCards.removeCard(1);
    }

    public void firstDraw(Users users, Dealer dealer) {
        users.addAllUsersTwoCards(this.gameCards);
        this.gameCards.removeCard(users.getUsersSize());
        dealer.addTwoCards(this.gameCards);
        this.gameCards.removeCard(1);
    }

    public void settleFirstRound(Users users, Dealer dealer) {
        for(User user : users.getUsers()){
            Integer userValue = user.getCardsValue();
            Integer dealerValue = dealer.getCardsValue();

            if (hasBlackJack(userValue) && hasBlackJack(dealerValue)) {
                user.setRevenueMoney(user.getBatMoney());
                return;
            }

            if (hasBlackJack(userValue)) {
                user.setRevenueMoney((int) (user.getBatMoney() * 1.5));
            }
        }

    }

    private boolean hasBlackJack(Integer value) {
        return value == 21;
    }
}
