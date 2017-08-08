package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Martial on 21/08/2015.
 */
public class Panneau extends JPanel {
    public void paintComponent(Graphics g){
        /*
        //Vous verrez cette phrase chaque fois que la méthode sera invoquée
        System.out.println("Je suis exécutée !");
        g.fillOval(20, 20, 75, 75);

        int x1 = this.getWidth()/4;
        int y1 = this.getHeight()/4;
        g.fillOval(x1, y1, this.getWidth()/2, this.getHeight()/2);
        g.drawOval(x1+1, y1+1, this.getWidth()/2, this.getHeight()/2);

        g.drawRect(10, 10, 50, 60);
        g.fillRect(65, 65, 30, 40);

        g.drawRoundRect(10, 10, 30, 50, 10, 10);
        g.fillRoundRect(55, 65, 55, 30, 5, 5);

        g.drawLine(0, 0, this.getWidth(), this.getHeight());
        g.drawLine(0, this.getHeight(), this.getWidth(), 0);

        int x[] = {20, 30, 50, 60, 60, 50, 30, 20};
        int y[] = {30, 20, 20, 30, 50, 60, 60, 50};
        g.drawPolygon(x, y, 8);

        int x2[] = {50, 60, 80, 90, 90, 80, 60, 50};
        int y2[] = {60, 50, 50, 60, 80, 90, 90, 80};
        g.fillPolygon(x2, y2, 8);

        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("Tiens ! Le Site du Zéro !", 10, 20);

        try {
            Image img = ImageIO.read(new File("images/images.jpg"));
            //g.drawImage(img, 0, 0, this);
            //Pour une image de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp, gp2, gp3, gp4, gp5, gp6;
        gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);
        gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
        gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
        gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
        gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
        gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 20, this.getHeight());
        g2d.setPaint(gp2);
        g2d.fillRect(20, 0, 20, this.getHeight());
        g2d.setPaint(gp3);
        g2d.fillRect(40, 0, 20, this.getHeight());
        g2d.setPaint(gp4);
        g2d.fillRect(60, 0, 20, this.getHeight());
        g2d.setPaint(gp5);
        g2d.fillRect(80, 0, 20, this.getHeight());
        g2d.setPaint(gp6);
        g2d.fillRect(100, 0, 40, this.getHeight());
        */

        try {
            Image img = ImageIO.read(new File("images/images.jpg"));
            //g.drawImage(img, 0, 0, this);
            //Pour une image de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}