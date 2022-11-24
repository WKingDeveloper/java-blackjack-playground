package nextstep.blackjack.service;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.Player;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;

import java.util.List;
import java.util.stream.Collectors;

public class RuleService {

    private Cards gameCards;

    public RuleService() {
        this.gameCards = new Cards();
        this.gameCards.setGameCards();
    }

    public void addCardsByPlayer(Player player) {
        Card card = this.gameCards.getCards().get(0);
        player.addCard(card);
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

    public boolean addCardByDealer(Dealer dealer) {
        if(dealer.getCardsValue()>16) return false;
        addCardsByPlayer(dealer);
        return true;
    }

    public void settleFinalRound(Users users, Dealer dealer) {

        for(User user : users.getUsers()){
            Integer userRevenue = user.getRevenueMoney();
            Integer dealerRevenue = dealer.getRevenueMoney();

            if(userRevenue!=0) {
                dealer.setRevenueMoney(dealerRevenue += userRevenue * -1);
                continue;
            }

            Integer userValue = user.getCardsValue();
            Integer dealerValue = dealer.getCardsValue();


            Integer userBatMoney = user.getBatMoney();

            if(dealerValue>21){
                user.setRevenueMoney(userBatMoney);
                dealer.setRevenueMoney(dealerRevenue += userBatMoney * -1);
                continue;
            }

            if(userValue>21 || dealerValue>= userValue){
                user.setRevenueMoney(userBatMoney * -1);
                dealer.setRevenueMoney(dealerRevenue += userBatMoney);
                continue;
            }

            user.setRevenueMoney(userBatMoney);
            dealer.setRevenueMoney(dealerRevenue += userBatMoney * -1);



        }



    }
}
