package Game.Board;

import Game.Chip.Chip;
import Game.Chip.Position;

public class PossibleMoves {
    private boolean[][] moves;
    private Chip[][] chips;

    PossibleMoves(int boardSize){
        moves = new boolean[boardSize][boardSize];
    }

    public boolean[][] getMoves() {
        return moves;
    }

    public boolean isPossibleMove() {
        int result = 0;

        for (int i = 0; i< moves.length; i++)
            for (int j = 0; j < moves.length; j++ )
                if (moves[i][j]) result++;
        return result > 0;
    }



    void updatePossibleMoves(Position pos, Chip[][] chips){
        int row = pos.getRow();
        int col = pos.getCol();
        this.chips = chips;
        moves[row][col] = false;

        if(top(pos) && !moves[row-1][col])
            moves[row - 1][col] = true;

        if(rightUp(pos) && !moves[row-1][col + 1])
            moves[row - 1][col + 1] = true;

        if(right(pos) && !moves[row][col+1])
            moves[row][col + 1] = true;

        if(rightDown(pos) && !moves[row+1][col + 1])
            moves[row + 1][col + 1] = true;

        if(down(pos) && !moves[row+1][col])
            moves[row+1][col] = true;

        if( leftDown(pos) && !moves[row+1][col - 1])
            moves[row + 1][col - 1] = true;

        if( left(pos) && !moves[row][col-1])
            moves[row ][col-1] = true;

        if( leftUp(pos) && !moves[col-1][row - 1])
            moves[row - 1][col - 1] = true;

    }

    private boolean top(Position pos){
        if(pos.getRow() < 1)
            return false;
        return chips[pos.getRow()-1][pos.getCol()] == null;
    }
    private boolean rightUp(Position pos){
        if(pos.getRow() < 1 || pos.getCol() + 1 >= chips.length)
            return false;
        return chips[pos.getRow() - 1][pos.getCol() + 1] == null;
    }
    private boolean right(Position pos){
        if(pos.getCol() + 1 >= chips.length)
            return false;
        return chips[pos.getRow()][pos.getCol()+1] == null;
    }
    private boolean rightDown(Position pos){
        if(pos.getCol() + 1 >= chips.length || pos.getRow() + 1 >= chips.length)
            return false;
        return chips[pos.getRow()+1][pos.getCol() + 1] == null;

    }
    private boolean down(Position pos){
        if(pos.getRow() + 1 >= chips.length)
            return false;
        return chips[pos.getRow()+1][pos.getCol()] == null ;
    }
    private boolean leftDown(Position pos){
        if(pos.getRow() + 1 >= chips.length || pos.getCol() < 1 )
            return false;
        return chips[pos.getRow()+1][pos.getCol() - 1] == null;
    }
    private boolean left(Position pos){
        if(pos.getCol() < 1)
            return false;
        return chips[pos.getRow()][pos.getCol()-1] == null;
    }
    private boolean leftUp(Position pos){
        if(pos.getCol() < 1 || pos.getRow() < 1)
            return false;
        return chips[pos.getRow()-1][pos.getCol() - 1] == null;
    }

    public String toString() {
        System.out.println("Possible Moves Array");
        StringBuilder stringBuilder = new StringBuilder(moves.length);

        for (int col = 0; col < moves[1].length; col++){

            for (int row = 0; row < moves[1].length; row++)
                stringBuilder.append("[" + ( moves[col][row] ? " " : "X")+ "]");
            stringBuilder.append("\n");
        }
        return  stringBuilder.toString();
    }
}
