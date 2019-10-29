package JungleGame.Game;

public class Tiger extends Animal {
    public Tiger(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "TIGER";
    }
}
