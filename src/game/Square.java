package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Martial on 06/12/2015.
 */
public class Square extends JButton{
    private String name;
    private Piece piece;
    public Square(int i, int j, Piece piece){
        super((char)('A'+j)+""+(i+1));
        this.name=(char)('A'+j)+""+(i+1);
        this.piece = piece;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp = new GradientPaint(0, 0, this.getBackground(), 0, 80, java.awt.Color.RED, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        try {
            if(this.piece!=null) g2d.drawImage(ImageIO.read(new File(this.piece.image)),0,0,this.getWidth(),this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.setColor(java.awt.Color.green);
        g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth()/ 2 /4), (this.getHeight() / 2) + 5);
    }
}
