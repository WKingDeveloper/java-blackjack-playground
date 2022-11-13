package nextstep.blackjack;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Player;
import nextstep.blackjack.model.player.Players;
import nextstep.blackjack.valid.ValidationPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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



    @CsvSource(value = {"pobi/ 1000", "jason/ 2000"}, delimiterString = "/")
    @ParameterizedTest
    @DisplayName("플레이어에게 배팅 금액을 설정")
    void setPlayerBattingMoney(String playerName, String money) {

        Map<String, Player> player = new HashMap<>();
        player.put(playerName, new Player(playerName));

        player.get(playerName).setBatMoney(money);

        assertThat(player.get(playerName).getBatMoney())
                .isEqualTo(Integer.parseInt(money));

    }

    @Test
    @DisplayName("시작 카드 52장 셋팅 확인")
    void setCard() {

        Cards cards = new Cards();
        assertThat(cards.getCards().size()).isEqualTo(52);

    }


}
