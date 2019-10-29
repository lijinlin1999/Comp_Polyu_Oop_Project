package JungleGame.Game;

public class Leopard extends Animal {
    public Leopard(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "LEOPARD";
    }
}
