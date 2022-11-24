package nextstep.blackjack.view;

import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.Player;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;

public class ResultView {

    public void firstRoundResult(Users users, Dealer dealer) {
        System.out.println("딜러와 pobi, jason에게 2장의 나누었습니다.");
        printPlayerCards(dealer);
        for (User user : users.getUsers()) {
            printPlayerCards(user);
        }
    }

    public void printPlayerCards(Player user) {
        System.out.println(user.getName() + ": " + user.getCardsNames());
    }

    public void addDealerCard(Dealer dealer) {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        printPlayerCards(dealer);
    }
}
