package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class MoveToLandCommand extends MoveCommand {
    public MoveToLandCommand(int fromX, int fromY, int toX, int toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public boolean execute(Board board) {
        board.moveAnimal(fromX, fromY, toX, toY);

        String name = board.getCell(toX, toY).getAnimal().getName();
        OutputHandler out = OutputHandler.getOutputHandler();
        out.printPrompt(name + " moves.\n");

        return true;
    }
}
