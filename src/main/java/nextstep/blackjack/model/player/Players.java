package nextstep.blackjack.model.player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Players {

    Map<String,Player> players = new HashMap<>();

    public Players(String[] playerNames) {
        this.players.put("dealer", new Player("dealer"));
        Arrays.stream(playerNames)
                .forEach(playerName -> this.players.put(playerName, new Player(playerName)));
    }

    public Map<String, Player> getPlayers() {
        return players;
    }
}
