package Game.Players;

import Game.Board.Board;
import Game.Chip.Position;


public class Human extends Player {
    private Board board;


    public Position turn(boolean[][] moves) {
        board = Board.getBoard();
        // TODO use listener for actionPerformed() method in Chip class
        int beforeSize = board.getPossibleMoves().countMoves();
        int afterSize = board.getPossibleMoves().countMoves();
        while (beforeSize == afterSize) {
            afterSize = board.getPossibleMoves().countMoves();
        }


        return null;
    }


}
