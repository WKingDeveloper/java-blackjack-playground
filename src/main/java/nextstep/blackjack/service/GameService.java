package nextstep.blackjack.service;

import nextstep.blackjack.model.Dealer;
import nextstep.blackjack.model.User;
import nextstep.blackjack.model.Users;
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

    }
}
