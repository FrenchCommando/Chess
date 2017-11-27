package game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Martial on 06/09/2017.
 */
public class Promotion extends JFrame {

    PlayerColor color;
    Color background_color = Color.BLACK;

    final AtomicReference<Piece> piece;

    PromotionWindow window;

    public Promotion(PlayerColor color, AtomicReference<Piece> p) {

        piece = p;
        this.color = color;


        Callable<PromotionWindow> promotionWindowCallable = () -> new PromotionWindow(this);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<PromotionWindow> promotionWindowFuture = executorService.submit(promotionWindowCallable);

        //EventQueue.invokeLater(() -> new PromotionWindow(this));


        try {
            window = promotionWindowFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        try {
            System.out.println("Promotion :: waiting for p");
            Piece piece = p.get();
            while(piece == null){
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Promotion :: waiting for p - loop");
                piece = p.get();
            }
            System.out.println("Promotion  :: post :: waiting for p");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.terminate();
        System.out.println("Promotion code out");

        executorService.shutdown();
    }

}
