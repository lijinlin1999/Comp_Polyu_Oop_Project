package JungleGame.Game;

import JungleGame.IO.InputHandler;
import JungleGame.IO.OutputHandler;

import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        InputHandler in = InputHandler.getInputHandler();
        OutputHandler out = OutputHandler.getOutputHandler();

        board = new Board();

        if (!board.load("src\\default.save")) {
            out.printPrompt("Fail to initialise the game.\nPlease try again.\n");
            return;
        }

        out.printPrompt("Welcome to The Jungle Game!\n");
        out.printPrompt("Please enter the name of the first player:\n");
        players.get(0).setName(in.getString());
        out.printPrompt("Please enter the name of the second player:\n");
        players.get(1).setName(in.getString());

        out.printPrompt("\nNow let the game begin!!\n\n");

        //board.load("src\\test.txt");

        Player player;
        Command command;
        while (!isFinished) {
            out.draw(board);

            player = getCurPlayer();
            out.printPrompt("Player " + player.getName() + ", please enter a command:\n");
            command = player.getCommand();

            if (command.execute(board) && command instanceof GameCommand && !isFinished) {
                nextPlayer();
            }

            out.printPrompt("\n");
        }

        out.draw(board);
        out.printPrompt("Game Finishes!\nThe winner is " + getCurPlayer().getName() + "!\n");

        out.printPrompt("\nPress enter to exit...");
        in.getString();
        System.exit(0);
    }

    public static Player getCurPlayer() {
        return players.get(curPlayerIndex);
    }

    static void nextPlayer() {
        curPlayerIndex = (curPlayerIndex + 1) % players.size();
    }

    static void onWinnerFound() {
        isFinished = true;
    }

    static ArrayList<Player> getPlayers() {
        return players;
    }

    static void setPlayers(ArrayList<Player> players) {
        Game.players = players;
    }

    static int getCurPlayerIndex() {
        return curPlayerIndex;
    }

    static void setCurPlayerIndex(int curPlayerIndex) {
        Game.curPlayerIndex = curPlayerIndex;
    }

    private static Board board;
    private static ArrayList<Player> players;
    private static int curPlayerIndex = 0;
    private static boolean isFinished = false;
}
