package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class ElephantEatCommand extends EatCommand {
    public ElephantEatCommand(int fromX, int fromY, int toX, int toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public boolean execute(Board board) {
        Animal eater = board.getCell(fromX, fromY).getAnimal();
        Cell toCell = board.getCell(toX, toY);
        Animal eatee = toCell.getAnimal();

        if (eatee instanceof Rat || eater.getRank() < eatee.getRank() && !(toCell instanceof TrapCell)) {
            Command command = new InvalidCommand();
            return(command.execute(board));
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
