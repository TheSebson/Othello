package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

public class DiagonallyUp extends Flip {

    public void set(Chip[][] chips, Position pos, int chipSide) {
        if(pos.getCol() == chips.length - 1 || pos.getRow() == chips.length - 1 || chips[pos.getRow()+1][pos.getCol()+1].isSide() == 0)
            direction = -1;
        else if(pos.getCol() == 0 || pos.getRow() == 0 || chips[pos.getRow() - 1][pos.getCol()- 1].isSide() == 0)
            direction = 1;
        else
            direction = 0;
        super.set(chips, pos, chipSide);
    }
    @Override
    public boolean check() {
        if (direction == 0) return false;
        System.out.println(" diagonally left direction: " + direction);
        int column = pos.getCol();
        int row = pos.getRow();
        for(int i = direction; (i + column < chips.length && column + i >= 0) && (i + row < chips.length && row + i >= 0); i+= direction) {
            Chip nextChip = chips[row + i][column + i];
            System.out.println(nextChip);
            if (nextChip.isSide() == 0)
                return false;
            else if (nextChip.isSide() == chipSide){
                System.out.println("tak");
                return (column + i == chips.length - 1 ||
                        column + i == 0 ||
                        row + i == chips.length - 1 ||
                        row + i == 0 ||
                        chips[row + i + direction][column + i + direction].isSide() == 0);
            }
        }
        return false;
    }

    @Override
    public Chip[][] flip() {
        System.out.println("fliping diagonally left");
        int j = 0;
        while( (pos.getCol() + j < chips.length && pos.getCol() + j >= 0) && (pos.getRow() + j < chips.length && pos.getRow() + j >= 0) ){
            Chip nextSpot = chips[pos.getRow() + j][pos.getCol() + j];
            System.out.println(nextSpot);
            if (nextSpot.isSide() == 0) return chips;
            if (nextSpot.isSide() != chipSide)
                chips[pos.getRow() + j][pos.getCol() + j].flip();
            j += direction;
        }

        return chips;
    }
}
