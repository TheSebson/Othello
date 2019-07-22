package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

import java.util.List;

public abstract class Flip {

    protected Chip[][] chips;
    protected int direction;
    protected Position pos;
    protected boolean chipSide;

    public void set(Chip[][] chips, Position pos, boolean chipSide){
        this.pos = pos;
        this.chipSide = chipSide;
        this.chips = chips;
    }

    public abstract boolean check();
    public abstract Chip[][] flip();


}
