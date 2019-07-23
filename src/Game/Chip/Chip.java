package Game.Chip;

import Game.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chip extends JButton implements ActionListener {

    public ImageIcon X, O;
    // Player - 1, computer - 2, null - 0
    private int side;
    private Position position;
    private Board board;

    public Chip(Position position, int side, Board board){
        this.side = side;
        this.position = position;
        this.board = board;
        X=new ImageIcon(this.getClass().getResource("Img/X.png"));
        O=new ImageIcon(this.getClass().getResource("Img/O.png"));
        this.addActionListener(this);
    }

    public void flip(){
        switch(side){
            case 1:
                side = 2;

                break;
            case 2:
                side = 1;

                break;
        }
        setIcon(side);
    }

    public int isSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public String toString() {
        return Integer.toString( side);
    }

    public void setIcon(int side) {
        setIcon(O);
        switch(side){
            case 1:
                setIcon(O);
                setDisabledIcon(O);
                break;
            case 2:
                setIcon(X);
                this.setDisabledIcon(X);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.addPawn(position, 1);
        setIcon(1);
    }
}
