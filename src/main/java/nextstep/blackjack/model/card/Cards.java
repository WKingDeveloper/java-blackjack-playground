package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {
    List<Card> cards = new ArrayList<>();

    public Cards() {
        setCards();
    }

    private void setCards() {
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] patterns = {"Diamond", "Heart", "Clover", "Space"};


        Arrays.stream(patterns)
                .forEach(pattern ->
                        Arrays.stream(numbers)
                        .forEach(number ->
                                this.cards.add(new Card(number,pattern))));
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
