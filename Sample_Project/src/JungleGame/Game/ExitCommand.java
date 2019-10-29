package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class ExitCommand extends Command {
    @Override
    public boolean execute(Board board) {
        OutputHandler out = OutputHandler.getOutputHandler();
        out.printPrompt("Exit the game...\n");
        System.exit(0);
        return true;
    }
}
