package nextstep.blackjack.model.player;

public class Player {
    private PlayerName name;
    private Money batMoney;

    public Player(String playerName) {
        this.name = new PlayerName(playerName);
    }

    public void setBatMoney(String money) {
        this.batMoney = new Money(money);
    }

    public Integer getBatMoney() {
        return this.batMoney.getMoney();
    }
}
