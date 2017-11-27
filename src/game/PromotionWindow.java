package game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Martial on 26/11/2017.
 */
public class PromotionWindow extends JFrame {

    Promotion promotion;

    public PromotionWindow(Promotion promotion) {

        this.promotion = promotion;

        this.setTitle("Promotion - " + promotion.color.color_name + " -- Please select Promoted Piece");

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        int size = Math.min(width,height) / 5 ;

        this.setSize(size, size * 4);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(50, 50));
        //this.setVisible(true);
        this.setResizable(true);
        this.setAlwaysOnTop(false);


        this.setLocation( width * 2 / 5, height / 10);

        this.repaint();

        try {
            System.out.println("PromotionWindow :: waiting for p");
            Piece piece = promotion.piece.get();
            while(piece == null){
                repaint();
                TimeUnit.SECONDS.sleep(10);
                piece = promotion.piece.get();
                System.out.println("PromotionWindow :: waiting for p - loop");
            }
            System.out.println("PromotionWindow  :: post :: waiting for p");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void repaint(){
        //displays the board
        int side = Math.min(Math.min(this.getHeight(),this.getWidth()),800);

        BoardPanel sp = new BoardPanel();
        //this.add(sp);
        sp.setLayout(new GridLayout(4, 2));
        sp.setSize(side - 60, side - 60);
        sp.setBounds(30, 30, side - 60, side - 60);
        sp.setMinimumSize(new Dimension(300,300));
        //sp.setMaximumSize(new Dimension(800,800)); // doesn't work anyways
        sp.setPreferredSize(new Dimension(side - 60, side - 60));
        sp.revalidate();
        //builds the squares

        fill_squares(sp);
        System.out.println("Promotion Square filled");

        sp.revalidate();
        sp.repaint();
        this.setContentPane(sp);
        this.setVisible(true);
        sp.setVisible(true);
        sp.paintComponent(sp.getGraphics());
        System.out.println("Promotion All Painted");
    }

    protected void fill_squares(BoardPanel sp){
        for(Piece p : Piece.promotable(promotion.color)){
            Square sq = new PromotionSquare(p,promotion.background_color,promotion.piece);
            sp.add(sq);
        }
    }

    public void terminate(){
        this.setVisible(false);
        this.dispose();
    }

}