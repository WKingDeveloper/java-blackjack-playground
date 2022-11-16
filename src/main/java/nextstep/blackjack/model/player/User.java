package nextstep.blackjack.model.player;

public class User extends Player{

    private Money batMoney;

    public User(String playerName) {
        super(playerName);
    }

    public void setBatMoney(String money) {
        this.batMoney = new Money(money);
    }

    public Integer getBatMoney() {
        return this.batMoney.getMoney();
    }

}
