package game;

import java.awt.*;
import java.util.*;

/**
 * Created by Martial on 30/07/2015.
 * contains a chess board, and a memory of all the pieces available
 */
public class Board {
    public BoardWindow window_w;
    public BoardWindow window_b;

    public Map<Cell, Piece> board;

    public Dictionary<PlayerColor, Map<Cell, Piece>> pieces;
    public Dictionary<PlayerColor, Castle> castle; // true if castle is allowed

    public Dictionary<PlayerColor, Cell> king;

    public Move current_move;

    private void init_board(){
        this.board=new HashMap<Cell, Piece>();
        this.board.put(Cell.A1, Piece.White_Rook);
        this.board.put(Cell.B1, Piece.White_Knight);
        this.board.put(Cell.C1, Piece.White_Bishop);
        this.board.put(Cell.D1, Piece.White_Queen);
        this.board.put(Cell.E1, Piece.White_King);
        this.board.put(Cell.F1, Piece.White_Bishop);
        this.board.put(Cell.G1, Piece.White_Knight);
        this.board.put(Cell.H1, Piece.White_Rook);
        this.board.put(Cell.A2, Piece.White_Pawn);
        this.board.put(Cell.B2, Piece.White_Pawn);
        this.board.put(Cell.C2, Piece.White_Pawn);
        this.board.put(Cell.D2, Piece.White_Pawn);
        this.board.put(Cell.E2, Piece.White_Pawn);
        this.board.put(Cell.F2, Piece.White_Pawn);
        this.board.put(Cell.G2, Piece.White_Pawn);
        this.board.put(Cell.H2, Piece.White_Pawn);
        this.board.put(Cell.A8, Piece.Black_Rook);
        this.board.put(Cell.B8, Piece.Black_Knight);
        this.board.put(Cell.C8, Piece.Black_Bishop);
        this.board.put(Cell.D8, Piece.Black_Queen);
        this.board.put(Cell.E8, Piece.Black_King);
        this.board.put(Cell.F8, Piece.Black_Bishop);
        this.board.put(Cell.G8, Piece.Black_Knight);
        this.board.put(Cell.H8, Piece.Black_Rook);
        this.board.put(Cell.A7, Piece.Black_Pawn);
        this.board.put(Cell.B7, Piece.Black_Pawn);
        this.board.put(Cell.C7, Piece.Black_Pawn);
        this.board.put(Cell.D7, Piece.Black_Pawn);
        this.board.put(Cell.E7, Piece.Black_Pawn);
        this.board.put(Cell.F7, Piece.Black_Pawn);
        this.board.put(Cell.G7, Piece.Black_Pawn);
        this.board.put(Cell.H7, Piece.Black_Pawn);
    }

    private void init_pieces(){
        this.pieces=new Hashtable<PlayerColor, Map<Cell, Piece>>();
        this.pieces.put(PlayerColor.WHITE,new HashMap<Cell, Piece>());
        this.pieces.get(PlayerColor.WHITE).put(Cell.A1, Piece.White_Rook);
        this.pieces.get(PlayerColor.WHITE).put(Cell.B1, Piece.White_Knight);
        this.pieces.get(PlayerColor.WHITE).put(Cell.C1, Piece.White_Bishop);
        this.pieces.get(PlayerColor.WHITE).put(Cell.D1, Piece.White_Queen);
        this.pieces.get(PlayerColor.WHITE).put(Cell.E1, Piece.White_King);
        this.pieces.get(PlayerColor.WHITE).put(Cell.F1, Piece.White_Bishop);
        this.pieces.get(PlayerColor.WHITE).put(Cell.G1, Piece.White_Knight);
        this.pieces.get(PlayerColor.WHITE).put(Cell.H1, Piece.White_Rook);
        this.pieces.get(PlayerColor.WHITE).put(Cell.A2, Piece.White_Pawn);
        this.pieces.get(PlayerColor.WHITE).put(Cell.B2, Piece.White_Pawn);
        this.pieces.get(PlayerColor.WHITE).put(Cell.C2, Piece.White_Pawn);
        this.pieces.get(PlayerColor.WHITE).put(Cell.D2, Piece.White_Pawn);
        this.pieces.get(PlayerColor.WHITE).put(Cell.E2, Piece.White_Pawn);
        this.pieces.get(PlayerColor.WHITE).put(Cell.F2, Piece.White_Pawn);
        this.pieces.get(PlayerColor.WHITE).put(Cell.G2, Piece.White_Pawn);
        this.pieces.get(PlayerColor.WHITE).put(Cell.H2, Piece.White_Pawn);
        this.pieces.put(PlayerColor.BLACK,new HashMap<Cell, Piece>());
        this.pieces.get(PlayerColor.BLACK).put(Cell.A8, Piece.Black_Rook);
        this.pieces.get(PlayerColor.BLACK).put(Cell.B8, Piece.Black_Knight);
        this.pieces.get(PlayerColor.BLACK).put(Cell.C8, Piece.Black_Bishop);
        this.pieces.get(PlayerColor.BLACK).put(Cell.D8, Piece.Black_Queen);
        this.pieces.get(PlayerColor.BLACK).put(Cell.E8, Piece.Black_King);
        this.pieces.get(PlayerColor.BLACK).put(Cell.F8, Piece.Black_Bishop);
        this.pieces.get(PlayerColor.BLACK).put(Cell.G8, Piece.Black_Knight);
        this.pieces.get(PlayerColor.BLACK).put(Cell.H8, Piece.Black_Rook);
        this.pieces.get(PlayerColor.BLACK).put(Cell.A7, Piece.Black_Pawn);
        this.pieces.get(PlayerColor.BLACK).put(Cell.B7, Piece.Black_Pawn);
        this.pieces.get(PlayerColor.BLACK).put(Cell.C7, Piece.Black_Pawn);
        this.pieces.get(PlayerColor.BLACK).put(Cell.D7, Piece.Black_Pawn);
        this.pieces.get(PlayerColor.BLACK).put(Cell.E7, Piece.Black_Pawn);
        this.pieces.get(PlayerColor.BLACK).put(Cell.F7, Piece.Black_Pawn);
        this.pieces.get(PlayerColor.BLACK).put(Cell.G7, Piece.Black_Pawn);
        this.pieces.get(PlayerColor.BLACK).put(Cell.H7, Piece.Black_Pawn);
    }

    private void init_king(){
        this.king = new Hashtable<PlayerColor, Cell>();
        this.king.put(PlayerColor.WHITE, Cell.E1);
        this.king.put(PlayerColor.BLACK, Cell.E8);
    }

    private void init_castle(){
        this.castle = new Hashtable<PlayerColor, Castle>();
        this.castle.put(PlayerColor.WHITE, new Castle());
        this.castle.put(PlayerColor.BLACK, new Castle());
    }

    private void build_windows(){
        this.window_w = new BoardWindowWhite(this);
        this.window_b = new BoardWindowBlack(this);
    }

    public PlayerColor trait = PlayerColor.WHITE;

    boolean over = false;
    public boolean isOver(){
        return over;
    }

    public Board(){
        this.init_board();
        this.init_pieces();
        this.init_king();
        this.init_castle();
        this.build_windows();
    } // I need another constructor with a given position
    public void terminate(){
        window_b.terminate();
        window_w.terminate();
    }

    public Piece getPiece(Cell cell){
        return this.board.get(cell);
    }

    public void move(Cell from, Cell to, PlayerColor color) {
        Piece p = this.board.get(from);
        if(p==null){
            System.out.println("Not Valid :"+ from.name() + to.name());
            return;
        }
        if(!color.equals(trait)){
            System.out.println("Not Valid : Trait aux "+ trait.color_name);
            System.out.println("\t"+ color.color_name + " - Attendez votre tour");
            return;
        }
        if(!p.color.equals(color)){
            System.out.println("Not Valid : illicit color : Player "+ color.color_name +" tried to move a piece of the color "+p.color+" --- Cells "+ from.name() + to.name());
            return;
        }
        Move current =  Move.move_factory(from, to, this);
        if(!current.check_valid()) { // prints error message
            return;
        }

        if(current.apply()){  //I don't use p, I can get it again
            if(this.check_terminated()){
                over = true;
                System.out.println("Game Over");
            }
            else{
                trait = PlayerColor.next(trait);
                System.out.println("Trait aux "+ trait.color_name);

            }
            window_b.background_color = random_color(0);
            window_w.background_color = random_color(3);
            window_w.repaint();
            window_b.repaint();
        }
    }

    static final Color[] color_list = {
            Color.GRAY, Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.PINK
    };
    static private Color random_color(int i){
        long time = System.nanoTime() + i;
        int size = color_list.length;
        int index = (int) Math.abs(time % size);
        return color_list[index];
    }

    private boolean check_terminated(){
        boolean terminated = false;
        if(king_attacked()){
            terminated = true;
            // check if any accessible cells is an escape cell
            Cell king_cell = king.get(trait);
            for( Cell[] cell_array : Cell.Cells)
                for( Cell target : cell_array)
                    if(Move.move_factory(king_cell, target, this).check_valid())
                        terminated = false;
        }
        return terminated;
    }// check whether there is checkmate or draw
    // increments counters for termination - and special final situations


    /*
    check if a move is allowed by computing the entailed attacked cells by the opponent,
    and checking that the cell of the own king is not there,
    this list is built after each potential move, so don't need to store it
     */
    public boolean king_attacked(){
        Set<Cell> occupied = new HashSet<Cell>(this.board.keySet());
        Cell king_cell = this.king.get(trait);
        for(Map.Entry<Cell, Piece> pair :
                this.pieces.get(PlayerColor.next(this.trait)).entrySet()){
            Cell c = pair.getKey();
            Piece p = pair.getValue();
            if (p.attacks(c,king_cell,occupied)) {
                System.out.println(p + " in " + c + " attacks the " + trait + " King in " + king_cell);
                return true;
            }
        }
        return false;
    }

    public Board(Board b){
        this.copy_board(b.board);
        this.copy_pieces(b.pieces);
        this.trait = b.trait;
        this.copy_king(b.king.get(b.trait));
        this.copy_castle(b.castle.get(b.trait));
    }

    private void copy_board(Map<Cell, Piece> board){
        this.board = new HashMap<Cell, Piece>(board);
    }
    private void copy_pieces(Dictionary<PlayerColor, Map<Cell, Piece>> pieces){
        this.pieces = new Hashtable<PlayerColor, Map<Cell, Piece>>();
        this.pieces.put(PlayerColor.WHITE, new HashMap<Cell, Piece>(pieces.get(PlayerColor.WHITE)));
        this.pieces.put(PlayerColor.BLACK, new HashMap<Cell, Piece>(pieces.get(PlayerColor.BLACK)));
    }
    private void copy_king(Cell king){
        this.king = new Hashtable<PlayerColor, Cell>();
        this.king.put(this.trait, king);
    }
    private void copy_castle(Castle castle) {
        this.castle = new Hashtable<PlayerColor, Castle>();
        this.castle.put(this.trait, new Castle(castle));
    }

}
