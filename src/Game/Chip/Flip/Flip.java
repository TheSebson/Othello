package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

import java.util.List;

public abstract class Flip {

    Chip[][] chips;
    int direction;
    int column;
    int row;
    int chipSide;

    public void set(Chip[][] chips, Position pos, int chipSide){
        this.chipSide = chipSide;
        this.chips = chips;
        row = pos.getRow();
        column = pos.getCol();
    }

    public abstract boolean check();
    public abstract Chip[][] flip();


}
