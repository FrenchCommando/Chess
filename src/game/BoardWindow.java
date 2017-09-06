package game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Martial on 05/12/2015.
 */
abstract class BoardWindow  extends JFrame {

    Board board;
    PlayerColor color;
    Color background_color = Color.BLACK;


    public BoardWindow(Board board, PlayerColor color) {
        this.setTitle("My game board -- " + color.color_name);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300, 300));
        this.setVisible(true);
        this.setResizable(true);
        this.setAlwaysOnTop(false);

        this.board = board;
        this.color = color;
        this.repaint();
    }

    @Override
    public void repaint(){
        //displays the board
        int side = Math.min(Math.min(this.getHeight(),this.getWidth()),800);

        BoardPanel sp;
        sp = new BoardPanel(this);
        sp.setLayout(new GridLayout(8, 8));
        sp.setSize(side - 60, side - 60);
        sp.setBounds(30, 30, side - 60, side - 60);
        sp.setMinimumSize(new Dimension(300,300));
        sp.setMaximumSize(new Dimension(800,800));
        sp.setPreferredSize(new Dimension(side - 60, side - 60));
        sp.revalidate();
        //builds the squares

        fill_squares(sp);

        sp.repaint();
        this.setContentPane(sp);
        this.setVisible(true);
    }

    abstract protected void fill_squares(BoardPanel sp);

    public void terminate(){
        this.setVisible(false);
        this.dispose();
    }

    Cell cellfrom = null;
    Cell cellentered = null;
    Cell cellpressed = null;

    public void click(Cell s){
        if(cellfrom == null){
            if(board.getPiece(s) == null){
                System.out.println("Empty cell " + s);
                return;
            }
            cellfrom = s;
            System.out.println("Cell Selected " + cellfrom.name());
            return;
        }
        if(cellfrom == s){
            System.out.println("Cell from identical to to" + cellfrom.name() + "-" +s.name());
            cellfrom = null;
            return;
        }
        board.move(cellfrom, s, color);
        cellfrom = null;
    }
    public void released(Cell s){
        if((cellpressed != null) & (cellpressed != cellentered)) {
            System.out.println("Cell Released " + cellentered.name());
            board.move(cellpressed, cellentered, color);
            cellpressed = null;
            cellentered = null;
        }
    }
}

class BoardWindowBlack extends BoardWindow{
    public BoardWindowBlack(Board board) {
        super(board, PlayerColor.BLACK);
        this.setLocation(1000, 100);
        this.background_color = Color.BLACK;
        super.repaint();
    }

    @Override
    protected void fill_squares(BoardPanel sp) {
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                Square sq = Square.square_from_index( 7 - j , i , this , this.background_color);
                sp.add(sq);
            }
        }
    }

}

class BoardWindowWhite extends BoardWindow{
    public BoardWindowWhite(Board board) {
        super(board, PlayerColor.WHITE);
        this.setLocation(200, 100);
        this.background_color = Color.WHITE;
        super.repaint();
    }

    @Override
    protected void fill_squares(BoardPanel sp) {
        for(int i = 0 ; i < 8 ; i++ ){
            for(int j = 0 ; j < 8 ; j++ ){
                Square sq = Square.square_from_index( j , 7 - i , this ,  this.background_color);
                sp.add(sq);
            }
        }
    }
}
