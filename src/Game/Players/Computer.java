package Game.Players;

import Game.Board.Board;
import Game.Chip.Position;

import java.util.Random;

public class Computer extends Player {



    public void turn(Board board) {
        Random randomMove = new Random();
        int col = randomMove.nextInt(board.getBoardSize());
        int row = randomMove.nextInt(board.getBoardSize());
        System.out.println("Computer: " + col + ", " + row);
        if (!board.getPossibleMoves().getMoves()[row][col])
            turn(board);
        else board.addChip(new Position(row, col), 2);
    }

}
