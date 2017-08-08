package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Martial on 05/12/2015.
 */
public class BoardPanel extends JPanel{
    JFrame jf;

    public BoardPanel(JFrame jf){
        super();
        this.jf=jf;
    }

    public void paintComponent(Graphics g){
        int side = Math.min(Math.min(this.jf.getHeight(),this.jf.getWidth()),800);
        try {
            Image img = ImageIO.read(new File("images/images.jpg"));
            g.drawImage(img, 0, 0, side, side, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
