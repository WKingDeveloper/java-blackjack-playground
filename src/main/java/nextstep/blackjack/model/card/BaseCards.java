package nextstep.blackjack.model.card;

import java.util.Arrays;
import java.util.Collections;

public class BaseCards extends Cards{

    public BaseCards() {
        setCards();
    }

    private void setCards() {
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] patterns = {"diamond", "heart", "clover", "space"};

        Arrays.stream(patterns)
                .forEach(pattern ->
                        Arrays.stream(numbers)
                        .forEach(number ->
                                this.getCards().add(new Card(number,pattern))));
        Collections.shuffle(this.getCards());
    }
}
