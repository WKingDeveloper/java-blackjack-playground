package nextstep.blackjack;

import nextstep.blackjack.valid.ValidationPlayer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

    @ParameterizedTest
    @CsvSource(value = {"wkd,kjs/ true", "wkd,kjs,wkd/ false"}, delimiterString = "/")
    @DisplayName("이름 리스트에 중복이 있는지 확인")
    void validNotSameNames(String names, boolean result) {
        ValidationPlayer validationPlayer = new ValidationPlayer();
        Assertions.assertThat(validationPlayer.validNames(names)).isEqualTo(result);
    }

    @Test
    @DisplayName("이름 리스트에 dealer가 있는지 확인")
    void validForbiddenNames() {
        String names = "wkd,kjs,dealer";
        ValidationPlayer validationPlayer = new ValidationPlayer();
        Assertions.assertThat(validationPlayer.validNames(names)).isEqualTo(false);
    }
}
