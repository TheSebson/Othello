package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

public class DiagonallyUp extends Flip {

    public void set(Chip[][] chips, Position pos, int chipSide) {
        super.set(chips, pos, chipSide);
        if(column == chips.length - 1 || row == chips.length - 1 || chips[row+1][column+1].isSide() == 0)
            direction = -1;
        else if(column == 0 || row == 0 || chips[row - 1][column- 1].isSide() == 0)
            direction = 1;
        else
            direction = 0;

    }
    @Override
    public boolean check() {
        if (direction == 0) return false;
        for(int i = direction; (i + column < chips.length && column + i >= 0)
                && (i + row < chips.length && row + i >= 0); i+= direction)
        {
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
        while( (column + j < chips.length && column + j >= 0) &&
                (row + j < chips.length && row + j >= 0) )
        {
            Chip nextSpot = chips[row + j][column + j];
            if (nextSpot.isSide() == 0)
                return chips;
            if (nextSpot.isSide() != chipSide)
                chips[row + j][column + j].flip();
            j += direction;
        }

        return chips;
    }
}
