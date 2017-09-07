package game;

/**
 * Created by Martial on 06/09/2017.
 */
public class Move {
    Cell from;
    Cell to;
    Board board;
    public Move(Cell from, Cell to, Board board){
        this.from = from;
        this.to = to;
        this.board = board;
    }
    public boolean apply(boolean check){
        Piece p = this.board.board.get(from);
        if (!p.moves(from, to, board)){
            System.out.println("Move failed "+p+" moving from "+from+" to "+to);
            return false;
        }
        return apply();
    }

    public boolean apply(){
        Piece p = this.board.board.get(from);
        if(p instanceof King)
            this.board.king.put(this.board.trait,to);
        this.board.board.put(to, p);
        this.board.board.remove(from);
        this.board.pieces.get(PlayerColor.next(this.board.trait)).remove(to);
        this.board.pieces.get(this.board.trait).remove(from);
        this.board.pieces.get(this.board.trait).put(to,p);

        return true;
    }

    public boolean check_valid(){
        Board temp = new Board(board);
        Move temp_move = new Move(from, to, temp);
        boolean valid = temp_move.apply(true) && !temp.king_attacked();
        promotion = temp.promotion;
        return valid;
    }

    boolean promotion = false;
}
