import Game.Board.Board;
import Game.Chip.Position;
import Players.Computer;
import Players.Human;
import Players.Player;

public class GameManager {
    Board board;
    Player human, computer;
    public int boardSize = 8;

    GameManager(){
        board = new Board(boardSize);
        human = new Human();
        computer = new Computer();
    }

    void game() {

        while(board.isGameOn()) {
            board.addPawn(human.turn(board.getPossibleMoves()), true);
            while (true) {
                Position computerPosition = computer.turn(board.getPossibleMoves());
                if(board.getPossibleMoves()[computerPosition.getRow()][computerPosition.getCol()] ){
                    board.addPawn(computer.turn(board.getPossibleMoves()), false);
                    break;
                }
            }
        }
        finishGame();
    }

    void finishGame(){
        if(board.getWinner())
            System.out.println("Player wins with score : " );
    }


}
