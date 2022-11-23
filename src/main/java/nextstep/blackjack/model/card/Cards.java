package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private List<Card> cards = new ArrayList<>();
    private Integer sum = 0;

    public List<Card> getCards() {
        return this.cards;
    }

    public void setGameCards() {
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        String[] patterns = {"diamond","spade","heart","clover"};

        for (String number : numbers) {
            for (String pattern : patterns) {
                this.cards.add(new Card(CardNumber.findCardNumber(number),CardPattern.findCardPattern(pattern)));
            }
        }

        Collections.shuffle(this.cards);
    }

    public Integer calculateCardsValue() {
        final Integer[] sum = {0};
        List<Card> aceCards = this.cards.stream().filter(card -> card.getValue() == 1)
                .collect(Collectors.toList());

        if (aceCards.size() == 0) {
            this.cards.stream()
                    .forEach(card -> sum[0] +=card.getValue());
            return sum[0];
        }

        this.cards.stream()
                .filter(card -> card.getValue() != 1)
                .forEach(card -> sum[0] +=card.getValue());

        for (Card card : aceCards) {
            if (sum[0]>10){
                sum[0] += card.getValue();
                continue;
            }
            sum[0] += 11;
        }

        return sum[0];
    }

}
