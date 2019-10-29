package JungleGame.Game;

public abstract class Cell {
    public Cell(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public abstract String getType();

    protected Animal animal;
}
