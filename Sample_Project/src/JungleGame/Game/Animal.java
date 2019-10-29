package JungleGame.Game;

public abstract class Animal {
    public Animal(String name, int rank, Player owner) {
        this.name = name;
        this.rank = rank;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public Player getOwner() {
        return owner;
    }

    public abstract String getType();

    private String name;
    private int rank;
    private Player owner;
}
