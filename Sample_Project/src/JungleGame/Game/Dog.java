package JungleGame.Game;

public class Dog extends Animal {
    public Dog(String name, int rank, Player owner) {
        super(name, rank, owner);
    }

    @Override
    public String getType() {
        return "DOG";
    }
}
