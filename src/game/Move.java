package game;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Martial on 06/09/2017.
 */
public abstract class Move {

    Cell from;
    Cell to;
    Board board;

    public Move(Cell from, Cell to, Board board){
        this.from = from;
        this.to = to;
        this.board = board;
    }

    boolean promotion = false;

    static Move move_factory(Cell from, Cell to, Board board){
        Piece p = board.board.get(from);
        if (p instanceof King){
            if (Math.abs(from.column - to.column) == 2)
                return new MoveCastle(from, to, board);
            return new MoveRegular(from, to, board);
        }
        if (p instanceof Pawn){
            if ((from.column != to.column) && (board.board.get(to) == null))
                return new MoveEnPassant(from, to, board);
        }
        return new MoveRegular(from, to, board);
    }

    abstract public boolean apply();
    abstract public boolean check_valid();
}

class MoveRegular extends Move{

    public MoveRegular(Cell from, Cell to, Board board) {
        super(from, to, board);
    }

    public void apply_common(){
        Piece p = this.board.board.get(from);
        if(p instanceof King)
            this.board.king.put(this.board.trait,to);
        this.board.board.put(to, p);
        this.board.board.remove(from);
        this.board.pieces.get(PlayerColor.next(this.board.trait)).remove(to);
        this.board.pieces.get(this.board.trait).remove(from);
        this.board.pieces.get(this.board.trait).put(to,p);
    }

    public boolean temp_apply(){
        this.board.current_move = this;
        Piece p = this.board.board.get(from);
        if (!p.moves(from, to, board)){
            System.out.println("Move failed "+p+" moving from "+from+" to "+to);
            return false;
        }
        apply_common();
        return true; //apply();
    }

    public boolean apply(){
        apply_common();

        if(this.promotion){
            //open a promotion window to choose the new Piece

            final AtomicReference<Piece> p = new AtomicReference<Piece>();
            Runnable pieceRunnable = () ->{
                new Promotion(board.trait, p);
                System.out.println("End Promotion Runnable");
            };

            ExecutorService executor = Executors.newFixedThreadPool(4);
            executor.submit(pieceRunnable);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                synchronized (p){
                    p.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Lock back to the Move thread");

            executor.shutdown();

            Piece promoted = p.get();

            System.out.println("Move :: Promoted is " + promoted);

            this.board.board.remove(to);
            board.board.put(to, promoted);
            this.board.pieces.get(this.board.trait).remove(to);
            this.board.pieces.get(this.board.trait).put(to,promoted);
            System.out.println("MovePostNotified");
        }


        return true;
    }

    public boolean check_valid(){
        Board temp = new Board(board);
        MoveRegular temp_move = new MoveRegular(from, to, temp);
        boolean valid = temp_move.temp_apply() && !temp.king_attacked(temp.trait);
        if (valid && temp_move.promotion){
            this.promotion = true;
        }
        return valid;
    }
}

class MoveEnPassant extends MoveRegular{

    public MoveEnPassant(Cell from, Cell to, Board board) {
        super(from, to, board);
        int direction = board.trait.direction;
        int start_row = PlayerColor.next(board.trait).starting_row;
        captured = Cell.get_cell(to.column,from.row);
        isEnPassant =
                (from.row == (start_row - 2 * direction))
                && (to.row == (start_row - direction))
                && (Math.abs(from.column - to.column) == 1)
                && (board.current_move.from == Cell.get_cell(to.column, start_row))
                && (board.current_move.to == captured);
    }

    boolean isEnPassant;
    Cell captured;

    @Override
    public boolean check_valid(){
        return isEnPassant && super.check_valid();
    }

    @Override
    public boolean apply(){
        this.board.board.remove(captured);
        this.board.pieces.get(PlayerColor.next(this.board.trait)).remove(captured);
        return super.apply();
    }
}


class MoveCastle extends Move{
    // with Castle, you don need to check if the resulting move is valid or not
    // the check is done before the move

    public MoveCastle(Cell from, Cell to, Board board) {
        super(from, to, board);
    }// CastleKing and CastleQueen ?

    @Override
    public boolean apply() {

        return false;
    }

    @Override
    public boolean check_valid() {
        // you need to check three cells
        // current, middle and end
        // amd of course if castle is true itself
        return false;
    }


}

