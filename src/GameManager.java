import Game.Board.Board;
import Game.Players.Computer;
import Game.Players.Human;
import Game.Players.Player;

public class GameManager {
    Board board;
    Player human, computer;
    public int boardSize = 8;

    GameManager(){
        board = Board.getBoard();
        board.initialize(boardSize);
        human = new Human();
        computer = new Computer();
    }

    void game() {

        while(board.isGameOn()) {
            human.turn(board);
            computer.turn(board);
        }
        finishGame();
    }

    void finishGame(){
        if(board.getWinner())
            System.out.println("Player wins with score : " );
    }


}
