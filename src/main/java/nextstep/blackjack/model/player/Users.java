package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Cards;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Users {

    Set<User> users = new HashSet<>();

    public Users(String[] splitNames) {
        Arrays.stream(splitNames).forEach(name -> users.add(new User(name)));
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public int getUsersSize() {
        return this.users.size();
    }


    public void addAllUsersTwoCards(Cards gameCards) {
        for (User user : users) {
            for (int i = 0; i < 2; i++) {
                user.getCards().add(gameCards.getCards().get(i));
                gameCards.getCards().remove(i);
            }
        }
    }
}
