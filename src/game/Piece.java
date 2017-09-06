package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Martial on 30/07/2015.
 */
abstract class Piece {
    PlayerColor color;
    String name;
    Image img;

    @Override
    public String toString(){
        return color + " - " + name;
    }

    protected Piece(PlayerColor color, String name){
        this.color=color;
        this.name = name;
        load_image();
    }
    
    protected void load_image(){
        try{
            this.img = ImageIO.read(new File(
                    "images/" + this.color.color_name.toLowerCase() + "_" + this.name.toLowerCase() + ".png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Piece White_King = new King(PlayerColor.WHITE);
    public static final Piece White_Queen = new Queen(PlayerColor.WHITE);
    public static final Piece White_Rook = new Rook(PlayerColor.WHITE);
    public static final Piece White_Bishop = new Bishop(PlayerColor.WHITE);
    public static final Piece White_Knight = new Knight(PlayerColor.WHITE);
    public static final Piece White_Pawn = new Pawn(PlayerColor.WHITE);

    public static final Piece Black_King = new King(PlayerColor.BLACK);
    public static final Piece Black_Queen = new Queen(PlayerColor.BLACK);
    public static final Piece Black_Rook = new Rook(PlayerColor.BLACK);
    public static final Piece Black_Bishop = new Bishop(PlayerColor.BLACK);
    public static final Piece Black_Knight = new Knight(PlayerColor.BLACK);
    public static final Piece Black_Pawn = new Pawn(PlayerColor.BLACK);

    public abstract boolean attacks(Cell from, Cell target, Set<Cell> occupied);
    public boolean moves(Cell from, Cell target, Board board){
        if (board.pieces.get(board.trait).containsKey(target)){
            System.out.println("Target cell " + target + " contains a piece of the same color : " + board.pieces.get(board.trait).get(target));
            return false;
        }
        return attacks(from, target, board.board.keySet());
    }
}

class King extends Piece{
    public King(PlayerColor color){
        super(color, "King");
    }

    static private boolean king_move(Cell from, Cell target){
        int diff_row = Math.abs(from.row - target.row);
        int diff_column = Math.abs(from.column - target.column);
        return Math.max(diff_column, diff_row) == 1;
    }

    @Override
    public boolean attacks(Cell from, Cell target, Set<Cell> occupied) {
        return king_move(from, target);
    }

    @Override
    public boolean moves(Cell from, Cell target, Board board) {
        return super.moves(from, target, board);
        // specific for castle please
    }

}
class Queen extends Piece{
    public Queen(PlayerColor color){
        super(color, "Queen");
    }
    @Override
    public boolean attacks(Cell from, Cell target, Set<Cell> occupied) {
        return Piece.Black_Bishop.attacks(from, target, occupied)
                || Piece.White_Rook.attacks(from, target, occupied) ;
    }
}
class Rook extends Piece{
    public Rook(PlayerColor color){
        super(color, "Rook");
    }
    @Override
    public boolean attacks(Cell from, Cell target, Set<Cell> occupied) {
        int diff_row = Math.abs(from.row - target.row);
        int diff_column = Math.abs(from.column - target.column);
        if (diff_column != 0 & diff_row != 0)
            return false;
        if (Math.max(diff_column, diff_row) == 1)
            return true;
        int next_row = from.row;
        int next_column = from.column;
        if(diff_column == 0){
            if (from.row < target.row)
                next_row++;
            else
                next_row--;
        }
        else if (from.column < target.column)
            next_column++;
        else
            next_column--;
        Cell next = Cell.get_cell(next_column, next_row);
        return !occupied.contains(next) && attacks(next, target, occupied);
    }
}
class Bishop extends Piece{
    public Bishop(PlayerColor color){
        super(color, "Bishop");
    }
    @Override
    public boolean attacks(Cell from, Cell target, Set<Cell> occupied) {
        int diff_row = Math.abs(from.row - target.row);
        int diff_column = Math.abs(from.column - target.column);
        if (diff_column != diff_row)
            return false;
        if (Math.max(diff_column, diff_row) == 1)
            return true;
        int next_row = from.row;
        int next_column = from.column;

        if (from.row < target.row)
            next_row++;
        else
            next_row--;
        if (from.column < target.column)
            next_column++;
        else
            next_column--;
        Cell next = Cell.get_cell(next_column, next_row);
        return !occupied.contains(next) && attacks(next, target, occupied);
    }
}
class Knight extends Piece{
    public Knight(PlayerColor color){
        super(color, "Knight");
    }
    @Override
    public boolean attacks(Cell from, Cell target, Set<Cell> occupied) {
        int diff_row = Math.abs(from.row - target.row);
        int diff_column = Math.abs(from.column - target.column);
        return (diff_row != 0)
                && (diff_column != 0)
                && (diff_column + diff_row == 3);
    }
}
class Pawn extends Piece{
    public Pawn(PlayerColor color){
        super(color, "Pawn");
    }
    @Override
    public boolean attacks(Cell from, Cell target, Set<Cell> occupied) {
        int diff_row = (from.row - target.row);
        int diff_column = Math.abs(from.column - target.column);
        return (diff_column == 1)
                && ((diff_row == 1 && color == PlayerColor.BLACK)
                || (diff_row == -1 && color == PlayerColor.WHITE));
    }
    @Override
    public boolean moves(Cell from, Cell target, Board board) {
        return true;
    }
}