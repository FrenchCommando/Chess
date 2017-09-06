package game;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Martial on 30/07/2015.
 */
public class Game {
    Scanner sc;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        new Game(sc);
        System.out.println("Do you want to play again?");
        String s = sc.next();
        System.out.println("message received "+s);
        while(s.toLowerCase().startsWith("y")){
            new Game(sc);
            System.out.println("Do you want to play again?");
            s = sc.next();
            System.out.println("message received "+s);
        }
    }
    public Game(Scanner sc){
        Board b = new Board();
        JOptionPane.showMessageDialog(b.window_b, " Your name ");
        System.out.println("Board filled, game started !");
        this.sc=sc;
        while (!b.isOver()) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        b.terminate();
        System.out.println("Game is over, thanks for playing");
    }


}
