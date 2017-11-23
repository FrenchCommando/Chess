package game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Martial on 06/09/2017.
 */
public class Promotion extends JFrame implements Runnable{


    PlayerColor color;
    Color background_color = Color.BLACK;

    boolean selected = false;
    AtomicReference<Piece> piece;
    final Object obj_;

    public Promotion(PlayerColor color, Object obj, AtomicReference<Piece> p) {
        obj_ = obj;
        piece = p;
        this.color = color;

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

        sp.repaint();
        this.setContentPane(sp);
        this.setVisible(true);
        System.out.println("Promotion All Painted");
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
        piece.set(p);
        notify_done();
    }

    public synchronized void notify_done() {

        synchronized (obj_){
            obj_.notifyAll();
        }
        System.out.println("PromotionPreNotified");
        synchronized (obj_){
            try {
                obj_.wait();
                System.out.println("PromotionWaiting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PromotionNotified");
        terminate();
    }

    @Override
    public void run() {
        this.setTitle("Promotion - " + color.color_name + " -- Please select Promoted Piece");

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
        synchronized (obj_){
            try {
                obj_.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
