package nextstep.blackjack;

import nextstep.blackjack.model.Dealer;
import nextstep.blackjack.model.User;
import nextstep.blackjack.model.Users;
import nextstep.blackjack.valid.ValidationPlayer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    private Users setUsers(String names) {
        return new Users(names.split(","));
    }

}
