package game;

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
        System.out.println("Board filled, game started !");
        this.sc=sc;
        BoardWindow bw = new BoardWindow(this,b);
        for(int i = 0; i<5;i++){//while(true){//b.isMate()!=true){
            String s = sc.next();
            System.out.println("message received "+s);
            b.move(s);
            bw.setVisible(false);
            bw = new BoardWindow(this,b);
        }
        //b.outcome();
        System.out.println("Game is over, thanks for playing");
        bw.setVisible(false);;
        bw.dispose();
    }
}
