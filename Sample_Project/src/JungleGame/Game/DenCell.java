package JungleGame.Game;

public class DenCell extends Cell {
    public DenCell(Animal animal, Player owner) {
        super(animal);
        this.owner = owner;
    }

    Player getOwner() {
        return owner;
    }

    private Player owner;

    @Override
    public String getType() {
        return "DEN ";
    }
}
