package game;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;

/**
 * Created by Martial on 05/12/2015.
 */
public class BoardWindow  extends JFrame {
    public BoardWindow(Game game, Board board) {
        this.setTitle("My game board");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 300));
        this.setVisible(true);
        this.setLocation(0, 0);
        this.setResizable(true);
        this.setAlwaysOnTop(false);

        this.game=game;
        this.board=board;

        //displays the board
        int side = Math.min(Math.min(this.getHeight(),this.getWidth()),800);
        BoardPanel sp = new BoardPanel(this);
        sp.setLayout(new GridLayout(8, 8));
        sp.setSize(side - 60, side - 60);
        sp.setBounds(30, 30, side - 60, side - 60);
        sp.setMinimumSize(new Dimension(300,300));
        sp.setMaximumSize(new Dimension(800,800));
        sp.setPreferredSize(new Dimension(side - 60, side - 60));
        sp.revalidate();
        //builds the squares
        int squareside = (side-60)/8;
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                Square sq = new Square(i,j,this.board.getPiece(i,j));
                if(((i+j) % 2) == 0)sq.setBackground(Color.DARK_GRAY);
                else sq.setBackground(Color.WHITE);
                sp.add(sq);
            }

        }

        this.setContentPane(sp);
        this.setVisible(true);

    }
    Game game;
    Board board;

}
