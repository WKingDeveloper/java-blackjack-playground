package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.List;

public abstract class Cards {

    private List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return this.cards;
    }

    public Integer calculateCards() {
        final int[] sumValue = {0};
        long count = cards.stream().filter(card -> card.getNumber().getValue() == 1)
                .count();

        if(count == 0){
            cards.stream()
                    .forEach(card -> sumValue[0] += card.getNumber().getValue());
            return sumValue[0];
        }

        cards.stream()
                .filter(card -> card.getNumber().getValue()!=1)
                .forEach(card -> sumValue[0] += card.getNumber().getValue());

        for (int i = 0; i < count; i++) {
            if(sumValue[0]<11){
                sumValue[0] += 11;
                continue;
            }
            sumValue[0] += 1;
        }
        return sumValue[0];
    }
}
