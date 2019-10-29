package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class MoveToDenCommand extends MoveCommand {
    public MoveToDenCommand(int fromX, int fromY, int toX, int toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public boolean execute(Board board) {
        Cell tmp = board.getCell(toX, toY);
        assert(tmp instanceof DenCell);
        DenCell den = (DenCell)tmp;

        if (den.getOwner() == board.getCell(fromX, fromY).getAnimal().getOwner()) {
            Command command = new InvalidCommand();
            return command.execute(board);
        }

        board.moveAnimal(fromX, fromY, toX, toY);

        String name = board.getCell(toX, toY).getAnimal().getName();
        OutputHandler out = OutputHandler.getOutputHandler();
        out.printPrompt(name + " moves to Den.\n");

        Game.onWinnerFound();

        return true;
    }
}
