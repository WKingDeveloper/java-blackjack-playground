package nextstep.blackjack;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.CardNumber;
import nextstep.blackjack.model.card.CardPattern;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;
import nextstep.blackjack.service.RuleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RuleTest {

    private Users setUsers(String names) {
        return new Users(names.split(","));
    }

    @Test
    @DisplayName("게임에 사용될 카드 52장 셋팅")
    void setGameCards() {
        Cards cards = new Cards();
        cards.setGameCards();
        assertThat(cards.getCards().size()).isEqualTo(52);
    }

    @Test
    @DisplayName("플레이어들(딜러 유저들)의 첫번째 카드 2장을 제공")
    void setFirstPlayerCards() {
        String names = "wkd,kjs,dy";
        Users users = setUsers(names);
        Dealer dealer = new Dealer();

        RuleService ruleService = new RuleService();
        ruleService.firstDraw(users,dealer);

        users.getUsers().stream().forEach(user -> assertThat(user.getCards().size()).isEqualTo(2));
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"A,10/ 3,4/ 1000/ 1500","A,10/ A,J/ 1000/ 1000"}
            ,delimiterString = "/")
    @DisplayName("첫번째 라운드 수익 계산")
    void calculateFirstRound(String userCards, String dealerCards, String batMoney, String revenue) {

        Users users = new Users();
        User user = new User("wkd");
        RuleService ruleService = new RuleService();
        Dealer dealer = new Dealer();

        user.getCards().add(new Card(CardNumber.findCardNumber(userCards.split(",")[0]),
                CardPattern.findCardPattern("diamond")));
        user.getCards().add(new Card(CardNumber.findCardNumber(userCards.split(",")[1]),
                CardPattern.findCardPattern("diamond")));
        user.setBatMoney(batMoney);

        dealer.getCards().add(new Card(CardNumber.findCardNumber(dealerCards.split(",")[0]),
                CardPattern.findCardPattern("diamond")));
        dealer.getCards().add(new Card(CardNumber.findCardNumber(dealerCards.split(",")[1]),
                CardPattern.findCardPattern("diamond")));


        users.getUsers().add(user);
        ruleService.settleFirstRound(users,dealer);

        assertThat(user.getRevenueMoney()).isEqualTo(Integer.parseInt(revenue));

    }

    @Test
    @DisplayName("유저에게 한장의 카드를 추가한다.")
    void addUserCard() {
        User user = new User("wkd");
        RuleService ruleService = new RuleService();

        user.getCards().add(new Card(CardNumber.findCardNumber("A"),
                CardPattern.findCardPattern("diamond")));
        user.getCards().add(new Card(CardNumber.findCardNumber("9"),
                CardPattern.findCardPattern("diamond")));

        ruleService.addCardsByPlayer(user);

        assertThat(user.getCards().size()).isEqualTo(3);
    }


    @ParameterizedTest
    @CsvSource(value = {"8,10/ 2","4,6/ 3"}
            ,delimiterString = "/")
    @DisplayName("딜러가 갖고있는 2장의 카드의 합이 16이하인 경우 한장을 더 받는다")
    void addDealerCardIfCardValueSixteenNotMoreThan(String cards, Integer cardSize) {
        Dealer dealer = new Dealer();
        dealer.getCards().add(new Card(CardNumber.findCardNumber(cards.split(",")[0]),
                CardPattern.findCardPattern("diamond")));
        dealer.getCards().add(new Card(CardNumber.findCardNumber(cards.split(",")[1]),
                CardPattern.findCardPattern("diamond")));

        RuleService ruleService = new RuleService();
        ruleService.addCardByDealer(dealer);
        assertThat(dealer.getCards().size()).isEqualTo(cardSize);
    }

}
