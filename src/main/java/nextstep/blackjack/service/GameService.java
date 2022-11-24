package nextstep.blackjack.service;

import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;
import nextstep.blackjack.valid.ValidationPlayer;
import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.ResultView;

public class GameService {

    public static void main(String[] args) {

        ValidationPlayer validationPlayer = new ValidationPlayer();
        boolean validNamesResult = true;
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();


        String names="";
        while (validNamesResult){
            names = inputView.init();
            validNamesResult = !validationPlayer.validNames(names);
        }

        String[] splitNames = names.split(",");

        Users users = new Users(splitNames);
        Dealer dealer = new Dealer();

        for (User user : users.getUsers()) {
            user.setBatMoney(inputView.inputBatMoney(user.getName()));
        }

        RuleService ruleService = new RuleService();
        ruleService.firstDraw(users,dealer);
        resultView.firstRoundResult(users, dealer);

        ruleService.settleFirstRound(users,dealer);

        for (User user : users.getUsers()) {
            if(user.getRevenueMoney() != 0) continue;
            boolean isAdd = true;
            while (isAdd){
                if(user.getCardsValue()>20) break;
                isAdd = inputView.inputAddUserCard(user.getName());
                if (!isAdd) break;
                ruleService.addCardsByPlayer(user);
                resultView.printPlayerCards(user);
            }
        }

        boolean isAddByDealerCard = ruleService.addCardByDealer(dealer);
        if(isAddByDealerCard) resultView.addDealerCard(dealer);

    }
}
