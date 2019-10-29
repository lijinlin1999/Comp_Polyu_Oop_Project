package JungleGame.Game;

public class Elephant extends Animal{
    public Elephant(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "ELEPHANT";
    }
}
