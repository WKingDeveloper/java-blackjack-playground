package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.Money;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;

import java.util.List;
import java.util.Set;

public class Revenue {

    public static void result(int round, Users users, Dealer dealer) {

        if(round == 0) {
            firstRoundResult(users, dealer);
            return;
        }
        roundResult(users,dealer);
    }

    private static void firstRoundResult(Users users, Dealer dealer) {

        List<String> findHasBlackJackByUsers = users.findHasBlackJackUsers();
        if (findHasBlackJackByUsers.size() == 0) {
            return;
        }

        if (!dealer.hasBlackJack()) {
            findHasBlackJackByUsers.stream().forEach(user ->
                    users.getPlayers().get(user).setRevenue(new Money((int)(users.getPlayers().get(user).getBatMoney()*1.5))));
            return;
        }
        findHasBlackJackByUsers.stream().forEach(user ->
                users.getPlayers().get(user).setRevenue(new Money((users.getPlayers().get(user).getBatMoney()))));

    }

    public static void roundResult(Users users, Dealer dealer) {
        Set<String> keys = users.getPlayers().keySet();
        Integer dealerValue = dealer.getCards().calculateCards();
        if (dealerValue>21){
            for (String key : keys) {
                User user = users.getPlayers().get(key);
                if(user.getRevenue() == 0){
                    user.setRevenue(new Money(user.getBatMoney()));
                }
            }
            return;
        }

        for (String key : keys) {
            User user = users.getPlayers().get(key);

            if (user.getRevenue() != 0) {
                continue;
            }
            Integer value = user.getCards().calculateCards();
            if (value > 21 || dealerValue >= value) {
                user.setRevenue(new Money(user.getBatMoney() * -1));
                continue;
            }

            user.setRevenue(new Money(user.getBatMoney()));

        }
    }

    public static void dealerRevenue(Users users, Dealer dealer) {
        dealer.setRevenue(new Money(users.getUsersRevenue() * -1));
    }

}
