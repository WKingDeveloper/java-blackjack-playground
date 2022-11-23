package nextstep.blackjack.service;

import nextstep.blackjack.valid.ValidationPlayer;
import nextstep.blackjack.view.InputView;

public class GameService {

    public static void main(String[] args) {
        String names = InputView.init();

        ValidationPlayer validationPlayer = new ValidationPlayer();
        boolean validNamesResult = validationPlayer.validNames(names);

        System.out.println("validNamesResult = " + validNamesResult);
    }
}
