package nextstep.blackjack.model;

public abstract class Player {

    private PlayerName name;
    private Money batMoney;

    public Player(String name) {
        this.name = new PlayerName(name);
    }

    public String getName() {
        return this.name.getName();
    }

    public void setBatMoney(String s){
        this.batMoney = new Money(s);
    }

    public Integer getBatMoney() {
        return batMoney.getMoney();
    }
}
