package test;

import game.Board;
import game.Cell;
import game.PlayerColor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Martial on 07/09/2017.
 */
public class TestCheckMate {
    final static Lock lock = new ReentrantLock();
    public static void main(String[] args){
        Board b = new Board(lock);
        b.move(Cell.E2,Cell.E4, PlayerColor.WHITE);
        b.move(Cell.E7,Cell.E5, PlayerColor.BLACK);
        b.move(Cell.F1,Cell.C4, PlayerColor.WHITE);
        b.move(Cell.F8,Cell.C5, PlayerColor.BLACK);
        b.move(Cell.D1,Cell.F3, PlayerColor.WHITE);
        b.move(Cell.B7,Cell.B6, PlayerColor.BLACK);
        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        b.terminate();
        System.out.println("Game Terminated");
    }

}
