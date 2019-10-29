package JungleGame.Game;

public class MoveCommand extends GameCommand {
    // Only check player and location
    public MoveCommand(int fromX, int fromY, int toX, int toY) {
        super(fromX, fromY, toX, toY);
    }

    @Override
    public boolean execute(Board board) {
        Command command;

        // Check player
        Cell fromCell = board.getCell(fromX, fromY);
        if (Game.getCurPlayer() != fromCell.getAnimal().getOwner()) {
            command = new InvalidCommand();
            return command.execute(board);
        }

        // Check horizontal and vertical
        if (fromX != toX && fromY != toY) {
            command = new InvalidCommand();
            return command.execute(board);
        }

        // Check jump
        if (Math.abs(toX - fromX) != 1 && Math.abs(fromY - toY) != 1) {
            command = new JumpCommand(fromX, fromY, toX, toY);
            return command.execute(board);
        }

        Cell toCell = board.getCell(toX, toY);

        // Move to trap
        if (toCell instanceof TrapCell) {
            command = new MoveToTrapCommand(fromX, fromY, toX, toY);
            return command.execute(board);
        }

        // Move to Den
        if (toCell instanceof DenCell) {
            command = new MoveToDenCommand(fromX, fromY, toX, toY);
            return command.execute(board);
        }

        // Move to River
        if (toCell instanceof RiverCell) {
            command = new MoveToRiverCommand(fromX, fromY, toX, toY);
            return command.execute(board);
        }

        // General move
        command = new MoveToLandCommand(fromX, fromY, toX, toY);
        return command.execute(board);
    }
}