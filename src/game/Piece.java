package game;

/**
 * Created by Martial on 30/07/2015.
 */
public class Piece {
    String color,piece,image;
    private Piece(String color, String piece){
        this.color=color;this.piece=piece;
        this.image="images\\"+color.toLowerCase()+"_"+piece.toLowerCase()+".png";
    }
    public static final Piece White_King = new Piece("White","King");
    public static final Piece White_Queen = new Piece("White","Queen");
    public static final Piece White_Rook = new Piece("White","Rook");
    public static final Piece White_Bishop = new Piece("White","Bishop");
    public static final Piece White_Knight = new Piece("White","Knight");
    public static final Piece White_Pawn = new Piece("White","Pawn");
    public static final Piece Black_King = new Piece("Black","King");
    public static final Piece Black_Queen = new Piece("Black","Queen");
    public static final Piece Black_Rook = new Piece("Black","Rook");
    public static final Piece Black_Bishop = new Piece("Black","Bishop");
    public static final Piece Black_Knight = new Piece("Black","Knight");
    public static final Piece Black_Pawn = new Piece("Black","Pawn");


}