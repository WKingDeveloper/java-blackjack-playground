package nextstep.blackjack;

import nextstep.blackjack.model.card.BaseCards;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;
import nextstep.blackjack.valid.ValidationPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJack {


    @CsvSource(value = {"pobi,jason,pobi", "pobi,jason,dealer"}, delimiterString = "/")
    @ParameterizedTest
    @DisplayName("중복된 이름이 있는지 확인")
    void validPlayerNames(String names) {
        String playerNames = names;
        String[] classifierPlayers = playerNames.split(",");

        assertThat(ValidationPlayer.validPlayerName(classifierPlayers)).isFalse();
    }


    @Test
    @DisplayName("플레이어 리스트를 만든다.")
    void setPlayerList() {
        String playerNames = "pobi,jason";
        String[] classifierPlayers = playerNames.split(",");
        Users users = new Users(classifierPlayers);

        assertThat(users.getPlayers().keySet())
                .usingRecursiveComparison()
                .isEqualTo(new HashSet<>(Arrays.asList("pobi","jason")));

    }



    @CsvSource(value = {"pobi/ 1000", "jason/ 2000"}, delimiterString = "/")
    @ParameterizedTest
    @DisplayName("플레이어에게 배팅 금액을 설정")
    void setPlayerBattingMoney(String playerName, String money) {

        Map<String, User> player = new HashMap<>();
        player.put(playerName, new User(playerName));

        player.get(playerName).setBatMoney(money);

        assertThat(player.get(playerName).getBatMoney())
                .isEqualTo(Integer.parseInt(money));

    }

    @Test
    @DisplayName("시작 카드 52장 셋팅 확인")
    void setCard() {
        BaseCards baseCards = new BaseCards();
        assertThat(baseCards.getCards().size()).isEqualTo(52);
    }



    @Test
    @DisplayName("플레이어에게 시작 시 카드 2장씩 분배")
    void distributeTwoCardsByPlayers(){
        String playerNames = "pobi,jason";
        String[] classifierPlayers = playerNames.split(",");
        Users users = new Users(classifierPlayers);

        Cards baseCards = new BaseCards();
        users.distributFirstDraw(baseCards);

        assertThat(users.getPlayers().get("pobi").getCards().size()).isEqualTo(2);
        assertThat(users.getPlayers().get("jason").getCards().size()).isEqualTo(2);
        assertThat(baseCards.getCards().size()).isEqualTo(48);
    }

    @Test
    @DisplayName("딜러에게 시작 시 카드 2장 분배 ")
    void distributeTwoCardsByDealer(){

        Dealer dealer = new Dealer();

        Cards baseCards = new BaseCards();
        dealer.getFirstDrawCards(baseCards);

        assertThat(dealer.getCards().size()).isEqualTo(2);
        assertThat(baseCards.getCards().size()).isEqualTo(50);
    }


}
