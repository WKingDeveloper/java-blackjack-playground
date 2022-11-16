package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Cards;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Users {

    Map<String,User> players = new HashMap<>();

    public Users(String[] playerNames) {
        Arrays.stream(playerNames)
                .forEach(playerName -> this.players.put(playerName, new User(playerName)));
    }

    public Map<String, User> getPlayers() {
        return players;
    }

    public void distributFirstDraw(Cards baseCards) {
        Set<String> keys = this.players.keySet();
        keys.stream().forEach(key -> this.players.get(key).getFirstDrawCards(baseCards));
    }
}
