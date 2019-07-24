package Game.Players;

import Game.Board.Board;
import Game.Chip.Position;

import java.util.concurrent.TimeUnit;


public class Human extends Player {
    private Board board;


    public void turn(Board board) {
        try {
            while (!board.buttonClicked) {
                TimeUnit.MICROSECONDS.sleep(1);

            }
        }catch (Exception e){

        }
        board.buttonClicked =false;

    }


}
