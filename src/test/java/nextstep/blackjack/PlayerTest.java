package nextstep.blackjack;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.player.Dealer;
import nextstep.blackjack.model.player.User;
import nextstep.blackjack.model.player.Users;
import nextstep.blackjack.service.RuleService;
import nextstep.blackjack.valid.ValidationPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"wkd,kjs/ true", "wkd,kjs,wkd/ false"}, delimiterString = "/")
    @DisplayName("이름 리스트에 중복이 있는지 확인")
    void validNotSameNames(String names, boolean result) {
        ValidationPlayer validationPlayer = new ValidationPlayer();
        assertThat(validationPlayer.validNames(names)).isEqualTo(result);
    }

    @Test
    @DisplayName("이름 리스트에 dealer가 있는지 확인")
    void validForbiddenNames() {
        String names = "wkd,kjs,dealer";
        ValidationPlayer validationPlayer = new ValidationPlayer();
        assertThat(validationPlayer.validNames(names)).isEqualTo(false);
    }

    @Test
    @DisplayName("이름 리스트를 통해 유저 리스트 만들기")
    void setUserList() {
        String names = "wkd,kjs,dy";

        Users users = setUsers(names);
        Dealer dealer = new Dealer();

        assertThat(users.getUsersSize())
                .usingRecursiveComparison()
                .isEqualTo(3);
        assertThat(dealer.getName()).isEqualTo("dealer");
    }

    @ParameterizedTest
    @CsvSource(value = {"wkd/ 1000", "kjs/ 3000", "dy/ 5000"}, delimiterString = "/")
    @DisplayName("유저 배팅 금액을 설정한다.")
    void setUsersBatMoney(String name, String batMoneys) {
        User user = new User(name);
        user.setBatMoney(batMoneys);
        assertThat(user.getBatMoney()).isEqualTo(Integer.parseInt(batMoneys));
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

    private Users setUsers(String names) {
        return new Users(names.split(","));
    }

}
