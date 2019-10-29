package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class InvalidCommand extends Command {
    @Override
    public boolean execute(Board board) {
        OutputHandler.getOutputHandler().printPrompt("Invalid command.\n");
        return false;
    }
}
