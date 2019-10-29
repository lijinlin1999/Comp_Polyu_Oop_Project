package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class EatCommand extends GameCommand {
    public EatCommand(int fromX, int fromY, int toX, int toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public boolean execute(Board board) {
        Cell fromCell = board.getCell(fromX, fromY);
        Animal eater = fromCell.getAnimal();
        Cell toCell = board.getCell(toX, toY);
        Animal eatee = toCell.getAnimal();

        // Check Enemy
        if (eatee.getOwner() == Game.getCurPlayer()) {
            Command command = new InvalidCommand();
            return command.execute(board);
        }

        // Check Elephant
        if (eater instanceof Elephant) {
            Command command = new ElephantEatCommand(fromX, fromY, toX, toY);
            return command.execute(board);
        }

        // Check Rat
        if (eater instanceof Rat) {
            Command command = new RatEatCommand(fromX, fromY, toX, toY);
            return command.execute(board);
        }

        // Normal eat
        if (eater.getRank() < eatee.getRank() && !(toCell instanceof TrapCell)) {
            Command command = new InvalidCommand();
            return command.execute(board);
        }

        Command command = new MoveCommand(fromX, fromY, toX, toY);
        if (command.execute(board)) {
            OutputHandler out = OutputHandler.getOutputHandler();
            out.printPrompt(eater.getName() + " captures " + eatee.getName() + ".\n");
            return true;
        } else {
            return false;
        }
    }
}
