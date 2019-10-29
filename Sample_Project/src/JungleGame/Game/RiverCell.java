package JungleGame.Game;

public class RiverCell extends Cell {
    public RiverCell(Animal animal) {
        super(animal);
    }

    @Override
    public String getType() {
        return "RIVER";
    }
}
