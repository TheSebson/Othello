package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

public class Vertical extends Flip {

    public void set(Chip[][] chips, Position pos, int chipSide) {
        super.set(chips, pos, chipSide);
        if(row == chips.length - 1 || chips[row+1][column].isSide() == 0)
            direction = -1;
        else if(row == 0 || chips[row - 1][column].isSide() == 0)
            direction = 1;
        else
            direction = 0;

    }



    @Override
    public boolean check() {
        if (direction == 0)
            return false;
        for(int i = direction; i + row < chips.length && i + row >= 0; i+= direction)
        {
            Chip nextChip = chips[row + i][column];
            if (nextChip.isSide() == 0)
                return false;
            else if (nextChip.isSide() == chipSide){
                return (row + i == chips.length - 1 || row + i == 0 || chips[row + i + direction][column].isSide() == 0);
            }
        }
        return false;

    }

    @Override
    public Chip[][] flip( ) {
        int j = 0;
        while(row + j < chips.length && row + j >= 0 )
        {
            Chip nextSpot = chips[row + j][column];
            if (nextSpot.isSide() == 0) return chips;
            if (nextSpot.isSide() != chipSide)
                chips[row + j][column].flip();
            j += direction;
        }

        return chips;


    }
}
