package nextstep.blackjack.service;

import nextstep.blackjack.model.card.BaseCards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;
import nextstep.blackjack.model.rule.RevenueRule;
import nextstep.blackjack.valid.ValidationPlayer;
import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.ResultView;

import java.util.List;
import java.util.Set;

public class GameService {

    public static void main(String[] args) {

        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        String[] playerNames = null;
        boolean validNames = false;
        while (!validNames) {
            playerNames = inputView.init().split(",");
            validNames = ValidationPlayer.validPlayerName(playerNames);
        }


        Users users = new Users(playerNames);
        Set<String> playerKeys = users.getPlayerKeys();

        playerKeys.forEach(key -> users.getPlayers()
                .get(key)
                .setBatMoney(inputView.setBatMoneyView(key)));

        Dealer dealer = new Dealer();
        BaseCards cards = new BaseCards();

        dealer.getFirstDrawCards(cards);
        users.distributFirstDraw(cards);

        resultView.firstDrawCardsResult(users, dealer);
        boolean result = RevenueRule.result(true, users, dealer);

        if (result) {
            RevenueRule.dealerRevenue(users,dealer);
            resultView.end(users,dealer);
            return;
        }


        for (String key : playerKeys) {
            boolean isAddCard = true;
            User user = users.getPlayers().get(key);
            while (isAddCard && user.getCards().calculateCards()<21) {

                isAddCard = inputView.addCard(key, user);

                if (isAddCard) {
                    user.addCard(cards);
                    resultView.userCard(key,user);
                }
            }
        }

        if(dealer.validCardsValue(cards)){
            resultView.dealerCard(dealer);
        }
        RevenueRule.result(false, users, dealer);
        RevenueRule.dealerRevenue(users,dealer);
        resultView.end(users,dealer);
    }
}
