package JungleGame.Game;

public class TrapCell extends Cell{
    public TrapCell(Animal animal) {
        super(animal);
    }

    @Override
    public String getType() {
        return "TRAP";
    }
}
