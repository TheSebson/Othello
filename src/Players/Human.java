package Players;

import Game.Chip.Position;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Human extends Player {
    @Override
    public Position turn(boolean[][] moves) {
         Position position = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose column:");
            String col = reader.readLine();
            System.out.println("Choose row:");
            String row = reader.readLine();
            System.out.println(col + ", " + row);
            position = new Position(Integer.parseInt(row), Integer.parseInt(col));

        } catch (Exception e){

        }
        if (!moves[position.getRow()][position.getCol()]) return turn(moves);
        return position;
    }


}
