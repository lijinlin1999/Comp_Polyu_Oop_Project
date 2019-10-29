package JungleGame.Game;

public class GameCommand extends Command {
    public GameCommand(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public boolean execute(Board board) {
        Command command;
        Cell fromCell = board.getCell(fromX, fromY);
        Cell toCell = board.getCell(toX, toY);

        // Invalid command case
        if (fromCell == null || toCell == null || fromCell.getAnimal() == null) {
            command = new InvalidCommand();
            return command.execute(board);
        }

        // Pure moving case
        if (toCell.getAnimal() == null) {
            command = new MoveCommand(fromX, fromY, toX, toY);
            return command.execute(board);
        }

        // Moving and eating case
        command = new EatCommand(fromX, fromY, toX, toY);
        return command.execute(board);
    }

    protected int fromX, fromY, toX, toY;
}
