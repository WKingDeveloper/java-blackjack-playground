package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.player.Money;
import nextstep.blackjack.model.player.Users;

import java.util.List;

public class Revenue {

    public static void firstRoundResult(Users users, boolean isBlackJackByDealer) {

        List<String> findHasBlackJackByUsers = users.findHasBlackJackUsers();
        if (findHasBlackJackByUsers.size() == 0) {
            return;
        }

        if (!isBlackJackByDealer) {
            findHasBlackJackByUsers.stream().forEach(user ->
                    users.getPlayers().get(user).setRevenue(new Money((int)(users.getPlayers().get(user).getBatMoney()*1.5))));
            return;
        }

        findHasBlackJackByUsers.stream().forEach(user ->
                users.getPlayers().get(user).setRevenue(new Money((int)(users.getPlayers().get(user).getBatMoney()*1))));

    }

}
