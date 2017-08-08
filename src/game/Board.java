package game;

/**
 * Created by Martial on 30/07/2015.
 * contains a chess board, and a memory of all the pieces available
 */
public class Board {
    private Piece[][] board;
    public Board(){
        this.board=new Piece[8][8];
        this.board[0][0]= Piece.White_Rook;
        this.board[0][1]= Piece.White_Knight;
        this.board[0][2]= Piece.White_Bishop;
        this.board[0][3]= Piece.White_Queen;
        this.board[0][4]= Piece.White_King;
        this.board[0][5]= Piece.White_Bishop;
        this.board[0][6]= Piece.White_Knight;
        this.board[0][7]= Piece.White_Rook;
        this.board[1][0]= Piece.White_Pawn;
        this.board[1][1]= Piece.White_Pawn;
        this.board[1][2]= Piece.White_Pawn;
        this.board[1][3]= Piece.White_Pawn;
        this.board[1][4]= Piece.White_Pawn;
        this.board[1][5]= Piece.White_Pawn;
        this.board[1][6]= Piece.White_Pawn;
        this.board[1][7]= Piece.White_Pawn;
        this.board[7][0]= Piece.Black_Rook;
        this.board[7][1]= Piece.Black_Knight;
        this.board[7][2]= Piece.Black_Bishop;
        this.board[7][3]= Piece.Black_Queen;
        this.board[7][4]= Piece.Black_King;
        this.board[7][5]= Piece.Black_Bishop;
        this.board[7][6]= Piece.Black_Knight;
        this.board[7][7]= Piece.Black_Rook;
        this.board[6][0]= Piece.Black_Pawn;
        this.board[6][1]= Piece.Black_Pawn;
        this.board[6][2]= Piece.Black_Pawn;
        this.board[6][3]= Piece.Black_Pawn;
        this.board[6][4]= Piece.Black_Pawn;
        this.board[6][5]= Piece.Black_Pawn;
        this.board[6][6]= Piece.Black_Pawn;
        this.board[6][7]= Piece.Black_Pawn;
    }

    public Piece getPiece(int i, int j){
        return this.board[i][j];
    }

    public void move(String s) {
        char [] a = s.toCharArray();
        if(a.length!=4){return;}
        int l1 = a[0]-'A';
        int n1 = a[1]-'1';
        int l2 = a[2]-'A';
        int n2 = a[3]-'1';
        System.out.println(l1+" "+n1+" "+l2+" "+n2);
        if(l1<0||l1>7||n1<0||n1>7||l2<0||l2>7||n2<0||n2>7)return;
        Piece p = this.board[n1][l1];
        if(p==null){return;}
        this.board[n2][l2]=p;
        this.board[n1][l1]=null;
        System.out.println("Played :"+s);
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
