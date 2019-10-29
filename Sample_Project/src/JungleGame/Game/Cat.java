package JungleGame.Game;

public class Cat extends Animal {
    public Cat(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "CAT";
    }
}
