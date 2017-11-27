package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

/**
 * Created by Martial on 06/12/2015.
 */

abstract public class Square extends JButton implements MouseListener {
    Piece piece;
    String name;
    Image img;
    Color back_color;

    public Square(String name, Piece piece, Color back_color){
        super(name);
        this.piece = piece;
        if(this.piece != null) this.img = this.piece.img;
        this.addMouseListener(this);
        this.name = name;
        this.back_color = back_color;
    }

    @Override
    public void paintComponent(Graphics g){
        System.out.println("Square printed");

        Graphics2D g2d = (Graphics2D)g;
        Color back_color = this.back_color;
        GradientPaint gp = new GradientPaint(0, 0, this.getBackground(), 0, 80, back_color, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        if(this.piece!=null) g2d.drawImage(this.img,x,y,this.getWidth(),this.getHeight(), this);

        g2d.setColor(java.awt.Color.green);
        g2d.drawString(this.name, (this.getWidth() / 2 - 5), (this.getHeight() / 2) + 5);
    }

    int x = 0;
    int y = 0;
}

class BoardSquare extends Square{
    BoardWindow board;
    Cell cell;

    public BoardSquare(Cell cell, BoardWindow board, Color back_color){
        super(cell.name(), board.board.getPiece(cell), back_color);
        this.cell = cell;
        this.setBackground(cell.color());
        this.board = board;
    }

    public static BoardSquare square_from_index(int i, int j, BoardWindow board, Color back_color){
        Cell c = Cell.get_cell(i,j);
        return new BoardSquare(c, board, back_color);
    }

    public void mouseClicked(MouseEvent event) {
        x = event.getX() - this.getWidth() / 2;
        y = event.getY() - this.getHeight() / 2;
        board.click(cell);
    }

    public void mouseEntered(MouseEvent event) {
        x = 0;
        y = 0;
        board.cellentered = cell;
    }

    public void mouseExited(MouseEvent event) {
        x = 0;
        y = 0;
    }

    public void mousePressed(MouseEvent event) {
        x = event.getX() - this.getWidth() / 2;
        y = event.getY() - this.getHeight() / 2;
        if(piece != null){
            board.cellpressed = cell;
        }
    }

    public void mouseReleased(MouseEvent event) {
        board.released(cell);
    }
}


class PromotionSquare extends Square{

    AtomicReference<Piece> atomicReference;

    public PromotionSquare(Piece piece, Color back_color, AtomicReference<Piece> atomicReference){
        super(piece.name, piece, back_color);
        this.atomicReference = atomicReference;
        this.setBackground(back_color);

        System.out.println("PromotionSquare built " + piece);
    }


    public void mouseClicked(MouseEvent event) {
        synchronized (atomicReference){
            atomicReference.set(piece);
            atomicReference.notifyAll();
            System.out.println("Piece selected" + piece);
        }
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }
}
