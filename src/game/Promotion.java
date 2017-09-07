package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Martial on 06/09/2017.
 */
public class Promotion extends JFrame {

    PlayerColor color;
    Color background_color = Color.BLACK;

    boolean selected = false;
    Piece piece;

    public Promotion(PlayerColor color) {
        this.setTitle("Promotion - " + color.color_name + " -- Please select Promoted Piece");
        this.setSize(300, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 300));
        this.setVisible(true);
        this.setResizable(true);
        this.setAlwaysOnTop(false);

        this.color = color;

        this.setLocation(750, 200);

        this.repaint();
    }

    @Override
    public void repaint(){
        //displays the board
        int side = Math.min(Math.min(this.getHeight(),this.getWidth()),800);

        BoardPanel sp;
        sp = new BoardPanel(this);
        sp.setLayout(new GridLayout(4, 2));
        sp.setSize(side - 60, side - 60);
        sp.setBounds(30, 30, side - 60, side - 60);
        sp.setMinimumSize(new Dimension(300,300));
        //sp.setMaximumSize(new Dimension(800,800)); // doesn't work anyways
        sp.setPreferredSize(new Dimension(side - 60, side - 60));
        sp.revalidate();
        //builds the squares

        fill_squares(sp);


        sp.repaint();
        this.setContentPane(sp);
        this.setVisible(true);
    }

    protected void fill_squares(BoardPanel sp){
        for(Piece p : Piece.promotable(this.color)){
            Square sq = new PromotionSquare(p,this.background_color,this);
            sp.add(sq);
        }
    }

    public void terminate(){
        this.setVisible(false);
        this.dispose();
    }

    public void chosen(Piece p){
        selected = true;
        piece = p;
        terminate();
    }

    public boolean isSelected(){
        return selected;
    }
}
