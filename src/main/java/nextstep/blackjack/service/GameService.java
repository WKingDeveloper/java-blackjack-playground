package nextstep.blackjack.service;

import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;
import nextstep.blackjack.valid.ValidationPlayer;
import nextstep.blackjack.view.InputView;

public class GameService {

    public static void main(String[] args) {


        ValidationPlayer validationPlayer = new ValidationPlayer();
        boolean validNamesResult = true;

        String names="";
        while (validNamesResult){
            names = InputView.init();
            validNamesResult = !validationPlayer.validNames(names);
        }

        String[] splitNames = names.split(",");

        Users users = new Users(splitNames);
        Dealer dealer = new Dealer();

        for (User user : users.getUsers()) {
            user.setBatMoney(InputView.inputBatMoney(user.getName()));
        }

        RuleService ruleService = new RuleService();
        ruleService.firstDraw(users,dealer);

    }
}
