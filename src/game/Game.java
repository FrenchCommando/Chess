package game;

import java.util.Scanner;

/**
 * Created by Martial on 30/07/2015.
 */
public class Game implements Runnable{

    final static Object lock = new Object();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        javax.swing.SwingUtilities.invokeLater(new Game(lock));
        System.out.println("Do you want to play again?");
        String s = sc.next();
        System.out.println("message received "+s);
        while(s.toLowerCase().startsWith("y")){
            javax.swing.SwingUtilities.invokeLater(new Game(lock));
            System.out.println("Do you want to play again?");
            s = sc.next();
            System.out.println("message received "+s);
        }
    }
    public void run(){
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Game(Object lock){
        Board b = new Board(lock);
        //JOptionPane.showMessageDialog(b.window_b, " Your name ");
        System.out.println("Board filled, game started !");
        synchronized (Game.lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        b.terminate();
        System.out.println("Game is over, thanks for playing");
    }
}
