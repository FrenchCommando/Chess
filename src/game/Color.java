package game;

/**
 * Created by Martial on 05/12/2015.
 */
public class Color {
    String color_name;
    java.awt.Color color_code;
    private Color(String color_name){
        this.color_name=color_name;
        if(color_name.toLowerCase().equals("white")){
            this.color_code=java.awt.Color.WHITE;
        }
        else if(color_name.toLowerCase().equals("black")){
            this.color_code=java.awt.Color.BLACK;
        }
        else{
            System.out.println("Bite : mauvaise couleur");
        }
    }
    public static final Color BLACK = new Color("Black");
    public static final Color WHITE = new Color("White");
}
