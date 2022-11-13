package nextstep.blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJack {

    @Test
    @DisplayName("플레이어 리스트를 만든다.")
    void setPlayerList() {
        String playerNamess = "pobi,jason";
        String[] classifierPlayers = playerNamess.split(",");

        Players players = new Players(Arrays.stream(classifierPlayers)
                .map(playerName -> new Player(playerName)).collect(Collectors.toSet()));

        assertThat(players)
                .usingRecursiveComparison()
                .isEqualTo(new HashSet<>(Arrays.asList(new Player("pobi"),new Player("jason"))));
    }

    @Test
    @DisplayName("중복된 이름이 있는지 확인")
    void validPlayerNames() {
        String playerNamess = "pobi,jason,pobi";
        String[] classifierPlayers = playerNamess.split(",");

        assertThat(ValidationPlayer.validPlayerNames(classifierPlayers)).isFalse();
    }


}
