package nextstep.blackjack;

import nextstep.blackjack.model.Player;
import nextstep.blackjack.model.PlayerName;
import nextstep.blackjack.model.Players;
import nextstep.blackjack.valid.ValidationPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJack {


    @Test
    @DisplayName("중복된 이름이 있는지 확인")
    void validPlayerNames() {
        String playerNamess = "pobi,jason,pobi";
        String[] classifierPlayers = playerNamess.split(",");

        assertThat(ValidationPlayer.validPlayerNames(classifierPlayers)).isFalse();
    }


    @Test
    @DisplayName("플레이어 리스트를 만든다.")
    void setPlayerList() {
        String playerNames = "pobi,jason";
        String[] classifierPlayers = playerNames.split(",");


        Map<String, Player> ps = new HashMap<>();
        Arrays.stream(classifierPlayers)
                .forEach(playerName -> ps.put(playerName, new Player(playerName)));
        Players players = new Players(ps);

        assertThat(players.getPlayers().keySet().toArray())
                .usingRecursiveComparison()
                .isEqualTo(new String[]{"pobi","jason"});

    }



}
