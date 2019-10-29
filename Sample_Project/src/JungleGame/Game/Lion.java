package JungleGame.Game;

public class Lion extends Animal{
    public Lion(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "LION";
    }
}
