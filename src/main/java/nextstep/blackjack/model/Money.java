package nextstep.blackjack.model;

public class Money {

    private Integer money;

    public Money(String money) {
        this.money = Integer.parseInt(money);
    }

    public Integer getMoney() {
        return this.money;
    }
}
