package nextstep.blackjack.model;

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
}
