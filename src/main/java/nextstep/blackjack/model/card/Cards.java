package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private List<Card> cards = new ArrayList<>();

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
}
