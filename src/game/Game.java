package game;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Martial on 30/07/2015.
 */
public class Game{

    final static Lock game_lock = new ReentrantLock();
    final static Lock board_lock = new ReentrantLock();

    public static void main(String[] args){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Scanner sc = new Scanner(System.in);
        executor.submit(() -> {
            game_lock.lock();
            new Game();
        });
        try {
            TimeUnit.SECONDS.sleep(1);
            game_lock.lock();
            game_lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Do you want to play again?");
        String s = sc.next();
        System.out.println("message received "+s);
        while(s.toLowerCase().startsWith("y")){
            executor.submit(() -> {
                game_lock.lock();
                new Game();
            });
            try {
                TimeUnit.SECONDS.sleep(1);
                game_lock.lock();
                game_lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Do you want to play again?");
            s = sc.next();
            System.out.println("message received "+s);
        }
        executor.shutdown();
        sc.close();
        System.out.println("Code terminated");
    }

    public Game(){
        Board b = new Board(board_lock);
        //JOptionPane.showMessageDialog(b.window_b, " Your name ");
        System.out.println("Board filled, game started !");
        synchronized (Game.board_lock){
            try {
                board_lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        b.terminate();
        game_lock.unlock();
        System.out.println("Game is over, thanks for playing");
    }
}
