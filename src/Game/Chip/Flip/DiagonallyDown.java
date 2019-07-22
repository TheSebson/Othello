package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

public class DiagonallyDown extends Flip {

    public void set(Chip[][] chips, Position pos, boolean chipSide) {
        if(pos.getCol() == chips.length - 1 || pos.getRow() == 0 || chips[pos.getRow()-1][pos.getCol()+1] == null)
            direction = -1;
        else if(pos.getCol() == 0 || pos.getRow() == chips.length - 1 || chips[pos.getRow() + 1][pos.getCol()- 1] == null)
            direction = 1;
        else
            direction = 0;
        super.set(chips, pos, chipSide);
    }
    @Override
    public boolean check() {
        if (direction == 0) return false;
        System.out.println(" diagonally right direction: " + direction);
        int column = pos.getCol();
        int row = pos.getRow();
        for(int i = direction; (i + column < chips.length && column + i >= 0) && (row - i  < chips.length && row - i >= 0); i+= direction) {
            Chip nextChip = chips[row - i][column + i];
            System.out.println(nextChip + ", ["+ row + ", " + column + "]");
            if (nextChip == null)
                return false;
            else if (nextChip.isSide() == chipSide){
                return (column + i == chips.length - 1 ||
                        column + i == 0 ||
                        row - i == chips.length - 1 ||
                        row - i == 0 ||
                        chips[row - i - direction][column + i + direction] == null);
            }
        }
        return false;
    }

    @Override
    public Chip[][] flip() {
        System.out.println("fliping diagonally left");
        int j = 0;
        while(pos.getCol() + j < chips.length && pos.getCol() + j >= 0 && pos.getRow() - j < chips.length && pos.getRow() - j >= 0 ){
            Chip nextSpot = chips[pos.getRow() - j][pos.getCol() + j];
            if (nextSpot == null) return chips;
            if (nextSpot.isSide() == !chipSide) chips[pos.getRow() - j][pos.getCol() + j].flip();
            j += direction;
        }

        return chips;
    }
}
