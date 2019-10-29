package JungleGame.Game;

import JungleGame.IO.*;

public class Player {
    public Player(String name, int numAnimal) {
        this.name = name;
        this.numAnimal = numAnimal;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public int getAnimalNum() {
        return numAnimal;
    }

    void decreaseAnimalNum() {
        if (numAnimal > 0) {
            --numAnimal;
        }
        if (numAnimal == 0) {
            Game.onWinnerFound();
        }
    }

    public Command getCommand() {
        InputHandler in = InputHandler.getInputHandler();
        return in.getCommand();
    }

    private String name;
    private int numAnimal;
}
