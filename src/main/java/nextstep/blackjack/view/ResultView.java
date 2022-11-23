package nextstep.blackjack.view;

import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;

public class ResultView {

    public static void firstRoundResult(Users users, Dealer dealer) {
        System.out.println("딜러와 pobi, jason에게 2장의 나누었습니다.");
        System.out.println(dealer.getName() + ": " + dealer.getCardsNames());
        for (User user : users.getUsers()) {

            System.out.println(user.getName() + ": " + user.getCardsNames());
        }
    }
}
