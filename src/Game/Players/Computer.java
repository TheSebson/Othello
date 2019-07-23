package Game.Players;

import Game.Chip.Position;

import java.util.Random;

public class Computer extends Player {



    public Position turn(boolean[][] moves) {
        Random randomMove = new Random();
        int col = randomMove.nextInt(8);
        int row = randomMove.nextInt(8);
        System.out.println("Computer: " + col + ", " + row);
        if (!moves[row][col]) return turn(moves);
        return  new Position(row, col);
    }

}
