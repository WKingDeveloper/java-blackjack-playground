package nextstep.blackjack.model.player;

import java.util.Map;

public class Players {

    Map<String,Player> players;

    public Players(Map<String,Player> players) {
        this.players = players;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }
}
