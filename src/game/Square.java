package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Martial on 06/12/2015.
 */

public class Square extends JButton implements MouseListener {
    Piece piece;
    BoardWindow board;
    Cell cell;
    String name;
    Image img;
    Color back_color;

    public Square(Cell cell, BoardWindow board, Color back_color){
        super(cell.name());
        this.cell = cell;
        this.piece = board.board.getPiece(cell);
        if(this.piece != null) this.img = this.piece.img;
        this.setBackground(cell.color());
        this.addMouseListener(this);
        this.name = cell.name();
        this.board = board;
        this.back_color = back_color;
    }

    public static Square square_from_index(int i, int j, BoardWindow board, Color back_color){
        Cell c = Cell.get_cell(i,j);
        return new Square(c, board, back_color);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Color back_color = this.back_color;
        GradientPaint gp = new GradientPaint(0, 0, this.getBackground(), 0, 80, back_color, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        if(this.piece!=null) g2d.drawImage(this.img,x,y,this.getWidth(),this.getHeight(), this);

        g2d.setColor(java.awt.Color.green);
        g2d.drawString(this.name, (this.getWidth() / 2 - 5), (this.getHeight() / 2) + 5);
    }

    int x;
    int y;

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
