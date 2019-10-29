package JungleGame.Game;

import JungleGame.IO.OutputHandler;

public class LoadCommand extends Command {
    public LoadCommand(String loadFile) {
        this.loadFile = loadFile;
    }

    @Override
    public boolean execute(Board board) {
        OutputHandler out = OutputHandler.getOutputHandler();

        if (board.load(loadFile)) {
            out.printPrompt("Game loaded.\n");
            return true;
        } else {
            out.printPrompt("Fail to load.\n");
            return false;
        }
    }

    private String loadFile;
}
