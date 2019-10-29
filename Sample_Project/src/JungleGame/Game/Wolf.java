package JungleGame.Game;

public class Wolf extends Animal {
    public Wolf(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "WOLF";
    }
}
