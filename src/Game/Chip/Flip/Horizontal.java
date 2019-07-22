package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

public class Horizontal extends Flip {

    public void set(Chip[][] chips, Position pos, boolean chipSide) {
        if(pos.getCol() == chips.length - 1 || chips[pos.getRow()][pos.getCol()+1] == null)
            direction = -1;
        else if(pos.getCol() == 0 || chips[pos.getRow() ][pos.getCol()- 1] == null)
            direction = 1;
        else
            direction = 0;
        super.set(chips, pos, chipSide);
    }

//    @Override
//    public boolean check() {
//        if (direction == 0) return false;
//        System.out.println(" horizontall direction: " + direction);
//        for(int i = direction; i + pos.getCol() < chips.length && i + pos.getCol() > 0; i+= direction) {
//            System.out.println("Column: " + pos.getCol() + i);
//            Chip nextSpot = chips[pos.getRow()][pos.getCol() + i];
//            if (nextSpot == null)
//                return false;
//            else if (nextSpot.isSide() == chipSide
//                    && (pos.getCol() + i == chips.length-1 || pos.getCol() + i == 0 || chips[pos.getRow()][pos.getCol() + i + direction] == null) )
//                return true;
//            else if (nextSpot.isSide() == chipSide)
//                return false;
//        }
//        return false;
//    }

    @Override
    public boolean check() {
        if (direction == 0) return false;
        System.out.println(" horizontall direction: " + direction);
        int column = pos.getCol();
        int row = pos.getRow();
        for(int i = direction; i + column < chips.length && column + i >= 0; i+= direction) {
            Chip nextChip = chips[row][column + i];
            if (nextChip == null)
                return false;
            else if (nextChip.isSide() == chipSide){
                return (column + i == chips.length - 1 ||
                        column + i == 0 ||
                        chips[row][column + i + direction] == null);
            }
        }
        return false;
    }

    @Override
    public Chip[][] flip() {
        int j = 0;
        System.out.println("Flip Horizontall");
        while(pos.getCol() + j < chips.length && pos.getCol() + j >= 0 ){
            Chip nextSpot = chips[pos.getRow()][pos.getCol() + j];
            if (nextSpot == null) return chips;
            if (nextSpot.isSide() == !chipSide) chips[pos.getRow()][pos.getCol() + j].flip();
            j += direction;
        }

        return chips;
    }
}
