package nextstep.blackjack.service;

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

    public void firstDraw(Users users, Dealer dealer) {
        users.addAllUsersTwoCards(this.gameCards);
        dealer.addTwoCards(this.gameCards);
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
