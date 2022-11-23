package nextstep.blackjack.model.player;

public class Money {

    private Integer money;

    public Money(String money) {
        this.money = Integer.valueOf(money);
    }

    public Integer getMoney() {
        return this.money;
    }
}
