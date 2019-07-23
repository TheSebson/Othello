package Game.Chip.Flip;

import Game.Chip.Chip;
import Game.Chip.Position;

public class Vertical extends Flip {

    public void set(Chip[][] chips, Position pos, int chipSide) {
        if(pos.getRow() == chips.length - 1 || chips[pos.getRow()+1][pos.getCol()].isSide() == 0)
            direction = -1;
        else if(pos.getRow() == 0 || chips[pos.getRow() - 1][pos.getCol()].isSide() == 0)
            direction = 1;
        else
            direction = 0;
        super.set(chips, pos, chipSide);
    }


//    @Override
//    public boolean check() {
//        if (direction == 0) return false;
//        System.out.println(" verticall direction: " + direction);
//
//        for(int i = direction; i + pos.getRow() < chips.length && i + pos.getRow() > 0; i+= direction) {
//            Chip nextSpot = chips[pos.getRow() + i][pos.getCol()];
//            System.out.println("vertex :" + pos.getRow() + i + "," + pos.getCol());
//            System.out.println(nextSpot);
//            if (nextSpot == null)
//                return false;
//            else if (nextSpot.isSide() == chipSide
//                    && (pos.getRow() + i == chips.length-1 || pos.getRow() + i == 0 || chips[pos.getRow() + i + direction][pos.getCol()] == null) )
//                return true;
//            else if (nextSpot.isSide() == chipSide)
//                return false;
//        }
//        return false;
//
//    }

    @Override
    public boolean check() {
        if (direction == 0) return false;
        System.out.println(" verticall direction: " + direction);
        int column = pos.getCol();
        int row = pos.getRow();
        for(int i = direction; i + row < chips.length && i + row >= 0; i+= direction) {
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
        System.out.println("Flip Vertical");
        while(pos.getRow() + j < chips.length && pos.getRow() + j >= 0 ){
            Chip nextSpot = chips[pos.getRow() + j][pos.getCol()];
            if (nextSpot.isSide() == 0) return chips;
            if (nextSpot.isSide() != chipSide)
                chips[pos.getRow() + j][pos.getCol()].flip();
            j += direction;
        }

        return chips;


    }
}
