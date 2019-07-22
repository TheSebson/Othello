package Game.Board;

import Game.Chip.Chip;

public class Spot {

    Chip chip;

    Spot(Chip chip)
    {
        this.chip = chip;
    }

    @Override
    public String toString() {
        return chip.toString();
    }

    public Chip getChip() {
        return chip;
    }

    public void setChip(Chip chip) {
        this.chip = chip;
    }
}
