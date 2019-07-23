package Game.Board;

import Game.Chip.Chip;
import Game.Chip.Flip.*;
import Game.Chip.Position;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board extends JFrame {

    private PossibleMoves possibleMoves;
    private List<Flip> flips;
    private JPanel panel;
    private Chip[][] buttons;
    private static Board board;

    public static Board getBoard() {
        if(board == null)
            board = new Board();
        return board;
    }

    private Board(){
        super("Othello Game");
    }

    public void initialize(int size){
        possibleMoves = new PossibleMoves(size);
        flips = new ArrayList<>(Arrays.asList(new Horizontal(), new Vertical(), new DiagonallyUp(), new DiagonallyDown()));

        setFrame(size);
//        flips = new ArrayList<>(Arrays.asList(, new Horizontal(), new DiagonallyUp(), new DiagonallyDown()));
        addPawn(new Position(size/2 - 1 ,size/2 - 1), 1);
        addPawn(new Position(size/2  ,size/2 ), 1);

        addPawn(new Position(size/2 - 1 ,size/2 ), 2);
        addPawn(new Position(size/2  ,size/2 - 1), 2);

    }

    private void setFrame(int size){
        panel = new JPanel();
        buttons = new Chip[size][size];

        setSize(800,800);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(size, size));

        for(int i = 0; i < buttons.length; i++)
        {
            for (int j = 0; j < buttons.length; j++) {

                    buttons[i][j] = new Chip(new Position(i, j), 0, this);
                    buttons[i][j].setEnabled(false);
                    panel.add(buttons[i][j]);
            }
        }
        add(panel);

        setVisible(true);
    }

    public void updateChip(Position pos, int b){
        buttons[pos.getRow()][pos.getCol()].setIcon(b);
        buttons[pos.getRow()][pos.getCol()].setSide(b);
        buttons[pos.getRow()][pos.getCol()].setEnabled(false);

    }

    public void updateButton() {
        for(int i = 0; i < buttons.length; i++)
        {
            for (int j = 0; j < buttons.length; j++) {
                if(possibleMoves.getMoves()[i][j])
                    buttons[i][j].setEnabled(true);
                else
                    buttons[i][j].setEnabled(false);

            }
        }
    }

    public synchronized void addPawn(Position pos, int side){
        updateChip(pos, side);
        possibleMoves.updatePossibleMoves(pos, buttons);
        for(Flip way : flips) {
            way.set(buttons, pos, side);
            if (way.check())
                this.buttons = way.flip();
        }
        updateButton();


        System.out.println(possibleMoves.toString());
        System.out.println(toString());
    }


    public boolean isGameOn(){
        return possibleMoves.isPossibleMove();
    }

    public PossibleMoves getPossibleMoves() {
        return possibleMoves;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(buttons.length);
        stringBuilder.append("    0.    1.   2.   3.   4.   5.   6.   7.\n");
        for (int col = 0; col < buttons[1].length; col++){
            stringBuilder.append(col + ".  ");
            for (int row = 0; row < buttons[1].length; row++)
                stringBuilder.append("[ " + (buttons[col][row].isSide() == 0 ? " " : buttons[col][row])+ " ]");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public boolean getWinner() {
        int playerScore = countChips();
        int computerScore = (int)(Math.pow(buttons.length, 2)) - playerScore;
        System.out.println("Player: " + playerScore + ", " + "Computer: " + computerScore);
        return playerScore > computerScore;
    }

    private int countChips() {
        int result = 0;
        for (int i = 0; i< buttons.length; i++)
            for (int j = 0; j < buttons.length; j++ )
                if (buttons[i][j].isSide() == 1) result++;
        return result;
    }
}
