package nextstep.blackjack.view;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;

import java.util.Set;

public class ResultView {
    public void firstDrawCardsResult(Users users, Dealer dealer) {
        Set<String> playerKeys = users.getPlayerKeys();
        String names = "dealer";
        for (String name : playerKeys){
            names += ", "+name;
        }

        System.out.println(names +"에게 2장의 카드씩 나누었습니다.");


        System.out.println("dealer의 카드 : " + dealer.getCardsNames());
        playerKeys.stream().forEach(key -> System.out.println(key + "의 카드 : " + users.getPlayers().get(key).getCardsNames()));

    }

    public void end(Users users, Dealer dealer) {
        Set<String> playerKeys = users.getPlayerKeys();
        System.out.println("최종 수익");
        System.out.println("dealer : " + dealer.getRevenue());

        for (String key : playerKeys) {
            System.out.println(key + " : "+users.getPlayers().get(key).getRevenue());
        }
    }

    public void dealerCard(Dealer dealer) {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println("dealer의 카드 : " + dealer.getCardsNames());
    }

    public void userCard(String name, User user) {
        System.out.println(name+"의 카드 : " + user.getCardsNames());
    }
}
