package game;

/**
 * Created by Martial on 18/08/2017.
 */
public class Castle {
    boolean king = true; //true if allowed
    boolean queen = true;
    public Castle(){}
    public Castle(Castle castle){
        this.king = castle.king;
        this.queen = castle.queen;
    }
}
