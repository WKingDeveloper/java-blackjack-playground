package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Cards;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public void distributFirstDraw(Cards baseCards) {
        Set<String> keys = this.players.keySet();
        keys.stream().forEach(key -> this.players.get(key).getFirstDrawCards(baseCards));
    }
}
