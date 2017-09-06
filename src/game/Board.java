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
    private Dictionary<PlayerColor, Castle> castle; // true if castle is allowed

    private Dictionary<PlayerColor, Cell> king;

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
    }
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
        if(!this.check_valid(from, to)) { // prints error message
            return;
        }
        if(this.apply(from, to, true)){  //I don't use p, I can get it again
            this.check_terminated();
            trait = PlayerColor.next(trait);
            System.out.println("Trait aux "+ trait.color_name);
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

    private boolean apply(Cell from, Cell to, boolean print){
        Piece p = this.board.get(from);
        if (!p.moves(from, to, this)){
            System.out.println("Move failed "+p+" moving from "+from+" to "+to);
            return false;
        }
        if(p instanceof King)
            this.king.put(trait,to);
        this.board.put(to, p);
        this.board.remove(from);
        this.pieces.get(PlayerColor.next(trait)).remove(to);
        this.pieces.get(trait).remove(from);
        this.pieces.get(trait).put(to,p);
        if(print)
            System.out.println("Played :"+ from.name() + to.name());
        return true;
    } // do specifics for castle and promotion
    // check for "en passant" or castle

    private void check_terminated(){
        if(this.board.get(Cell.F4) == Piece.Black_Bishop){
            over = true;
        }
    }// check whether there is checkmate or draw
    // increments counters for termination - and special final situations


    /*
    check if a move is allowed by computing the entailed attacked cells by the opponent,
    and checking that the cell of the own king is not there,
    this list is built after each potential move, so don't need to store it
     */
    private boolean king_attacked(){
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

    private boolean check_valid(Cell from, Cell to) {
        Board temp = new Board(this);
        // don't forget to check about castle validity
        return temp.apply(from, to, false) && !temp.king_attacked();
    }

    private Board(Board b){
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




    /*
    private boolean checkmate;
    public boolean isMate(){
        return checkmate;
    }
    private String outcome;
    public void outcome(){
        if(this.outcome=="w"){
            System.out.println("White won ! Congratulations !");
        }
        if(this.outcome=="b"){
            System.out.println("Black won ! Congratulations !");
        }
        if(this.outcome=="d"){
            System.out.println("It's a draw ! Congratulations !");
        }
    }

    public void move(String s){
        if(s=="wsurrender"){
            System.out.println("White surrendered");
            outcome="b";return;
        }
        if(s=="bsurrender"){
            System.out.println("Black surrendered");
            outcome="w";return;
        }
        if(s.length()==4||s.length()==5){
            String move = s.toLowerCase();
            if(this.isamove(move)){
                System.out.println("Moved "+s);return;
            }
        }
        System.out.println("Problem occured, can't do the move !");
    }

    String toplay;//w or b

    public boolean isamove(String move){
        char[] c = move.toCharArray();
        int m1 = (int) c[0] -97;
        int m2 = (int) c[1];
        int m3 = (int) c[2] -97;
        int m4 = (int) c[3];
        String promotion = "";
        if(move.length()==5){
            promotion = Character.toString(c[4]);
        }
        if(m1<8&&m1>=0&&m2<8&&m2>=0&&m3<8&&m3>=0&&m4<8&&m4>=0&&(promotion==""||promotion=="q"||promotion=="r"||promotion=="k"||promotion=="b")){
            if(board[m2][m1] instanceof Piece){
                Piece p = board[m2][m1];
                if(p.color==toplay){
                    //check if the move is allowed
                    boolean licitmove=false;
                    boolean ispromotion=false;
                    boolean iscastle=false;
                    boolean ispawncapture=false;
                    boolean isenpassant=false;
                    if(m1==m3||m2==m4){
                        licitmove=false;
                    }
                    else if(p.piece=="rook"){//check si y a rien sur le chemin
                        if(m1==m3||m2==m4){
                            licitmove=true;
                        }
                    }
                    else if(p.piece=="bishop"){
                        if((m1-m3)==(m2-m4)||(m1-m3)==(m4-m2)){
                            licitmove=true;
                        }
                    }
                    else if(p.piece=="knight"){
                        int diff1=Math.abs(m1-m3),diff2=Math.abs(m2-m4);
                        if((diff1==1&&diff2==2)||(diff1==2&&diff2==1)){
                            licitmove=true;
                        }
                    }
                    else if(p.piece=="queen"){
                        if(m1==m3||m2==m4){
                            licitmove=true;
                        }
                        if((m1-m3)==(m2-m4)||(m1-m3)==(m4-m2)){
                            licitmove=true;
                        }
                    }
                    else if(p.piece=="king"){
                        if(Math.abs(m1-m3)<=1||Math.abs(m2-m4)<=1){
                            licitmove=true;
                        }
                        if(m1==m3&&Math.abs(m2-m4)==2){
                            if(toplay=="w"&&m1==0||toplay=="w"&&m1==7){

                            }
                        }
                    }

                    //care of "castle", "en passant", "promotion" or pawn capture
                    //update the position in a new board
                    //update the counts, check for potential checks
                    //replace the board by the new board
                    //check for checkmate, stalemate, fifty-move, three-repetition, or other specifics
                    //update the "toplay"
                }
            }
        }
        return false;
    }
    String lastmove;//for the en passant rule
    Board lastboard;//for the repetition rule
    boolean repeated;//for the repetation rule
    boolean whitecastleksallowed;//updated when king or rook moves, or after castle, during board replacement (only checks movement, not the king check or the case check)
    boolean whitecastleqsallowed;
    boolean blackcastleksallowed;
    boolean blackcastleqsallowed;
    */
}
