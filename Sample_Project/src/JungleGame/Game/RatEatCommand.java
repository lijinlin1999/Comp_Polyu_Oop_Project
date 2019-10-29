package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class RatEatCommand extends EatCommand {
    public RatEatCommand(int fromX, int fromY, int toX, int toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public boolean execute(Board board) {
        Cell fromCell = board.getCell(fromX, fromY);
        Cell toCell = board.getCell(toX, toY);
        Animal eater = fromCell.getAnimal();
        Animal eatee = toCell.getAnimal();

        // Check River
        if (fromCell instanceof RiverCell && !(toCell instanceof RiverCell)) {
            Command command = new InvalidCommand();
            return command.execute(board);
        }
        if (!(fromCell instanceof  RiverCell) && toCell instanceof RiverCell) {
            Command command = new InvalidCommand();
            return command.execute(board);
        }

        if (eater.getRank() < eatee.getRank() && !(eatee instanceof Elephant) && !(toCell instanceof TrapCell)) {
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
