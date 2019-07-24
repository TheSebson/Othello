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
    public boolean buttonClicked;

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

        setupFrame(size);
        addChip(new Position(size/2 - 1 ,size/2 - 1), 1);
        addChip(new Position(size/2  ,size/2 ), 1);

        addChip(new Position(size/2 - 1 ,size/2 ), 2);
        addChip(new Position(size/2  ,size/2 - 1), 2);

    }

    public int getBoardSize(){
        return buttons.length;
    }
    private void setupFrame(int size){
        panel = new JPanel();
        buttons = new Chip[size][size];

        setSize(size*100,size*100);
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


    public void updateButtons() {
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

    public synchronized void addChip(Position pos, int side){
        buttons[pos.getRow()][pos.getCol()].setSide(side);
        possibleMoves.updatePossibleMoves(pos, buttons);
        for(Flip way : flips) {
            way.set(buttons, pos, side);
            if (way.check())
                this.buttons = way.flip();
        }
        updateButtons();

    }


    public boolean isGameOn(){
        return possibleMoves.isPossibleMove();
    }

    public PossibleMoves getPossibleMoves() {
        return possibleMoves;
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
