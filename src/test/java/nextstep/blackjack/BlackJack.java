package nextstep.blackjack;

import nextstep.blackjack.model.card.BaseCards;
import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;
import nextstep.blackjack.model.rule.Revenue;
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
        Users users = setUsers();

        assertThat(users.getPlayers().keySet())
                .usingRecursiveComparison()
                .isEqualTo(new HashSet<>(Arrays.asList("pobi", "jason")));

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
    @DisplayName("유저들에게 시작 시 카드 2장씩 분배")
    void distributeTwoCardsByUsers() {
        Users users = setUsers();

        Cards baseCards = new BaseCards();
        users.distributFirstDraw(baseCards);

        assertThat(users.getPlayers().get("pobi").getCards().getCards().size()).isEqualTo(2);
        assertThat(users.getPlayers().get("jason").getCards().getCards().size()).isEqualTo(2);
        assertThat(baseCards.getCards().size()).isEqualTo(48);
    }

    @Test
    @DisplayName("딜러에게 시작 시 카드 2장 분배 ")
    void distributeTwoCardsByDealer() {

        Dealer dealer = new Dealer();

        Cards baseCards = new BaseCards();
        dealer.getFirstDrawCards(baseCards);

        assertThat(dealer.getCards().getCards().size()).isEqualTo(2);
        assertThat(baseCards.getCards().size()).isEqualTo(50);
    }

    @Test
    @DisplayName("유저와 딜러에게 블랙잭이 있는지 확인")
    void findHasBlackJackByUsers() {
        Users users = setUsers();
        Dealer dealer = new Dealer();

        Card card1 = new Card("10", "space");
        Card card2 = new Card("J", "heart");
        Card card3 = new Card("8", "clover");
        Card card4 = new Card("Q", "diamond");
        Card card5 = new Card("8", "heart");
        Card card6 = new Card("K", "space");

        users.getPlayers().get("pobi").getCards().getCards().add(card1);
        users.getPlayers().get("pobi").getCards().getCards().add(card2);
        users.getPlayers().get("jason").getCards().getCards().add(card3);
        users.getPlayers().get("jason").getCards().getCards().add(card4);
        dealer.getCards().getCards().add(card5);
        dealer.getCards().getCards().add(card6);

        assertThat(users.findHasBlackJackUsers())
                .usingRecursiveComparison()
                .isEqualTo(Arrays.asList("pobi"));

        assertThat(dealer.hasBlackJack()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 16이하 카드를 가질 경우 한장을 더 받게 한다.")
    void validDealerCardsValue() {
        Dealer dealer = new Dealer();

        Card card1 = new Card("5", "space");
        Card card2 = new Card("10", "heart");

        dealer.getCards().getCards().add(card1);
        dealer.getCards().getCards().add(card2);

        dealer.validCardsValue(new BaseCards());

        assertThat(dealer.getCards().getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("유저가 원하는 경우 카드를 더 받게 한다.")
    void addUserCard() {
        BaseCards cards = new BaseCards();
        Users users = setUsers();
        users.distributFirstDraw(cards);
        users.getPlayers().get("pobi").addCard(cards);

        assertThat(users.getPlayers().get("pobi").getCards().getCards().size()).isEqualTo(3);
        assertThat(users.getPlayers().get("jason").getCards().getCards().size()).isEqualTo(2);
    }

    private Users setUsers() {
        String playerNames = "pobi,jason";
        String[] classifierPlayers = playerNames.split(",");
        return new Users(classifierPlayers);
    }

    @Test
    @DisplayName("유저가 첫 카드 2장에 블랙잭 나온 경우 승리 보상 - 딜러가 블랙잭이 아닌 경우")
    void firstRoundResultisDealerBlackJackFalse() {
        Users users = setUsers();
        Dealer dealer = new Dealer();

        Card card1 = new Card("10", "space");
        Card card2 = new Card("J", "heart");
        Card card3 = new Card("8", "clover");
        Card card4 = new Card("Q", "diamond");
        Card card5 = new Card("7", "heart");
        Card card6 = new Card("K", "space");

        users.getPlayers().get("pobi").getCards().getCards().add(card1);
        users.getPlayers().get("pobi").getCards().getCards().add(card2);
        users.getPlayers().get("jason").getCards().getCards().add(card3);
        users.getPlayers().get("jason").getCards().getCards().add(card4);

        users.getPlayers().get("pobi").setBatMoney("1000");
        users.getPlayers().get("jason").setBatMoney("3000");

        dealer.getCards().getCards().add(card5);
        dealer.getCards().getCards().add(card6);


        Revenue.result(0,users,dealer);
        assertThat(users.getPlayers().get("pobi").getRevenue()).isEqualTo(1500);

    }

    @Test
    @DisplayName("유저가 첫 카드 2장에 블랙잭 나온 경우 승리 보상 - 딜러가 블랙잭인 경우")
    void firstRoundResultisDealerBlackJackTrue() {
        Users users = setUsers();
        Dealer dealer = new Dealer();

        Card card1 = new Card("10", "space");
        Card card2 = new Card("J", "heart");
        Card card3 = new Card("8", "clover");
        Card card4 = new Card("Q", "diamond");
        Card card5 = new Card("8", "heart");
        Card card6 = new Card("K", "space");

        users.getPlayers().get("pobi").getCards().getCards().add(card1);
        users.getPlayers().get("pobi").getCards().getCards().add(card2);
        users.getPlayers().get("jason").getCards().getCards().add(card3);
        users.getPlayers().get("jason").getCards().getCards().add(card4);

        users.getPlayers().get("pobi").setBatMoney("1000");
        users.getPlayers().get("jason").setBatMoney("3000");

        dealer.getCards().getCards().add(card5);
        dealer.getCards().getCards().add(card6);

        Revenue.result(0,users,dealer);
        assertThat(users.getPlayers().get("pobi").getRevenue()).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어들과 딜러 결과별 계산 진행")
    void result() {
        Users users = setUsers();
        Dealer dealer = new Dealer();

        Card card1 = new Card("10", "space");
        Card card2 = new Card("9", "heart");
        Card card3 = new Card("3", "clover");
        Card card4 = new Card("4", "diamond");
        Card card5 = new Card("8", "heart");
        Card card6 = new Card("5", "space");

        users.getPlayers().get("pobi").getCards().getCards().add(card1);
        users.getPlayers().get("pobi").getCards().getCards().add(card2);
        users.getPlayers().get("jason").getCards().getCards().add(card3);
        users.getPlayers().get("jason").getCards().getCards().add(card4);

        users.getPlayers().get("pobi").setBatMoney("1000");
        users.getPlayers().get("jason").setBatMoney("3000");

        dealer.getCards().getCards().add(card5);
        dealer.getCards().getCards().add(card6);

        Revenue.result(1,users,dealer);
        Revenue.dealerRevenue(users,dealer);
        assertThat(users.getPlayers().get("pobi").getRevenue()).isEqualTo(1000);
        assertThat(users.getPlayers().get("jason").getRevenue()).isEqualTo(-3000);
        assertThat(dealer.getRevenue()).isEqualTo(2000);
    }


}
