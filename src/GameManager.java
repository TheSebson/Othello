import Game.Board.Board;
import Game.Chip.Position;
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
            human.turn(board.getPossibleMoves().getMoves());
            System.out.println("Human played");
            // TODO Refactoring
            while (true) {
                System.out.println("Computer playing");
                Position computerPosition = computer.turn(board.getPossibleMoves().getMoves());
                if(board.getPossibleMoves().getMoves()[computerPosition.getRow()][computerPosition.getCol()] ){
                    board.addPawn(computer.turn(board.getPossibleMoves().getMoves()), 2);
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
