package game;

/**
 * Created by Martial on 05/12/2015.
 */
public class PlayerColor {
    String color_name;
    java.awt.Color color_code;  //  don't know if this will be useful
    int direction;  //  +1 or -1 depending on the color
    int promotion_row;  //  last row to determine if you are eligible for promotion
    int starting_row;

    private PlayerColor(String color_name){
        this.color_name=color_name;
        if(color_name.toLowerCase().equals("white")){
            this.color_code = java.awt.Color.WHITE;
            this.direction = +1;
            this.promotion_row = 7;
            this.starting_row = 1;
        }
        else if(color_name.toLowerCase().equals("black")){
            this.color_code = java.awt.Color.BLACK;
            this.direction = -1;
            this.promotion_row = 0;
            this.starting_row = 6;
        }
        else{
            System.out.println("Bite : mauvaise couleur");
        }
    }
    public static final PlayerColor BLACK = new PlayerColor("Black");
    public static final PlayerColor WHITE = new PlayerColor("White");

    public static PlayerColor next(PlayerColor color){
        if (color == BLACK)
            return WHITE;
        return BLACK;
    }

    public String toString() {
        return color_name;
    }
}
