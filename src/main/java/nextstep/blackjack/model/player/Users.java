package nextstep.blackjack.model.player;

import nextstep.blackjack.model.card.Cards;

import java.util.*;
import java.util.stream.Collectors;

public class Users {

    Map<String,User> players = new HashMap<>();

    public  Users(String[] playerNames) {
        Arrays.stream(playerNames)
                .forEach(playerName -> this.players.put(playerName, new User(playerName)));
    }

    public Map<String, User> getPlayers() {
        return players;
    }

    public void distributFirstDraw(Cards baseCards) {
        getPlayerKeys().stream().forEach(key -> this.players.get(key).getFirstDrawCards(baseCards));
    }

    public List<String> findHasBlackJackUsers() {
        return getPlayerKeys().stream().filter(key -> this.players.get(key).hasBlackJack())
                .collect(Collectors.toList());
    }

    public Set<String> getPlayerKeys() {
        return this.players.keySet();
    }

    public Integer getUsersRevenue() {
        Integer value = 0;
        Set<String> keys = players.keySet();

        for (String key:keys) {
            value += players.get(key).getRevenue() * -1;
        }

        return value;
    }

}
