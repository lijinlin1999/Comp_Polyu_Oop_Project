package JungleGame.Game;

public class Rat extends Animal {
    public Rat(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "RAT";
    }
}
