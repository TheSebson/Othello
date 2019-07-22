package Game.Board;

import Game.Chip.Chip;
import Game.Chip.Flip.*;
import Game.Chip.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {

    private Chip[][] chips;
//    private boolean[][] posibleMoves;
    private PossibleMoves possibleMoves;
//    private ChipFliper chipFliper;
    private List<Flip> flips;

    public Board(int size){
        chips = new Chip[size][size];
        possibleMoves = new PossibleMoves(size);
        flips = new ArrayList<>(Arrays.asList(new Vertical(), new Horizontal(), new DiagonallyUp(), new DiagonallyDown()));

        addPawn(new Position(size/2 - 1 ,size/2 - 1), true);
        addPawn(new Position(size/2  ,size/2 ), true);

        addPawn(new Position(size/2 - 1 ,size/2 ), false);
        addPawn(new Position(size/2  ,size/2 - 1), false);

    }

    public synchronized void addPawn(Position pos, boolean side){

        chips[pos.getRow()][pos.getCol()] = new Chip(side);
        possibleMoves.updatePossibleMoves(pos, chips);
        for(Flip way : flips) {
            way.set(chips, pos, side);
            if (way.check()) {
                this.chips = way.flip();
                continue;
            }
        }

        System.out.println(possibleMoves.toString());
        System.out.println(toString());
    }

    public boolean isGameOn(){
        return possibleMoves.isPossibleMove();
    }

    public boolean[][] getPossibleMoves() {
        return possibleMoves.getMoves();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(chips.length);
        stringBuilder.append("      0.      1.     2.     3.     4.     5.     6.     7.\n");
        for (int col = 0; col < chips[1].length; col++){
            stringBuilder.append(col + ".  ");
            for (int row = 0; row < chips[1].length; row++)
                stringBuilder.append("[" + (chips[col][row] == null ? "     " : chips[col][row])+ "]");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean getWinner() {
        int playerScore = countChips();
        int computerScore = (int)(Math.pow(chips.length, 2)) - playerScore;
        System.out.println("Player: " + playerScore + ", " + "Computer: " + computerScore);
        return playerScore > computerScore;
    }

    private int countChips() {
        int result = 0;
        for (int i = 0; i< chips.length; i++)
            for (int j = 0; j < chips.length; j++ )
                if (chips[i][j].isSide()) result++;
        return result;
    }
}
