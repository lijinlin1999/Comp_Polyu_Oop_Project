package JungleGame.Game;

public class LandCell extends Cell {
    public LandCell(Animal animal) {
        super(animal);
    }

    @Override
    public String getType() {
        return "LAND";
    }
}
