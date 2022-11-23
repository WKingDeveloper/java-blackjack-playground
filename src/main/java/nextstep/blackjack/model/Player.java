package nextstep.blackjack.model;

public abstract class Player {

    private PlayerName name;

    public Player(String name) {
        this.name = new PlayerName(name);
    }

    public String getName() {
        return this.name.getName();
    }
}
