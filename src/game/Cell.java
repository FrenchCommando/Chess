package game;

import java.awt.*;

/**
 * Created by Martial on 14/08/2017.
 */
public class Cell {
    public String toString() {
        return this.name();
    }

    @Override
    public int hashCode() {
        return 8 * column + row;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cell && (obj == this ||
                (row == ((Cell) obj).row) && (column == ((Cell) obj).column));
    }

    int row;
    int column;

    String name(){
        char char_col =(char)('A' + column);
        char char_row = (char) ('1' + row);
        return char_col + "" + char_row;
    }

    private Cell(int column , int row){
        this.column = column;
        this.row = row;
    }

    public static Cell get_cell(int column, int row){
        return Cells[row][column];
    }

    public Color color(){
        if(((row+column) % 2) == 0) return Color.DARK_GRAY;
        else return Color.WHITE;
    }

    public Cell add_row(int row_num){
        return get_cell(column, row + row_num);
    }

    public Cell add_column(int col_num){
        return get_cell(column + col_num, row);
    }

    public Cell add_column_row(int col_num, int row_num){
        return get_cell(column + col_num, row + row_num);
    }

    public static final Cell A1 = new Cell(0,0);
    public static final Cell B1 = new Cell(1,0);
    public static final Cell C1 = new Cell(2,0);
    public static final Cell D1 = new Cell(3,0);
    public static final Cell E1 = new Cell(4,0);
    public static final Cell F1 = new Cell(5,0);
    public static final Cell G1 = new Cell(6,0);
    public static final Cell H1 = new Cell(7,0);

    public static final Cell A2 = new Cell(0,1);
    public static final Cell B2 = new Cell(1,1);
    public static final Cell C2 = new Cell(2,1);
    public static final Cell D2 = new Cell(3,1);
    public static final Cell E2 = new Cell(4,1);
    public static final Cell F2 = new Cell(5,1);
    public static final Cell G2 = new Cell(6,1);
    public static final Cell H2 = new Cell(7,1);
    
    public static final Cell A3 = new Cell(0,2);
    public static final Cell B3 = new Cell(1,2);
    public static final Cell C3 = new Cell(2,2);
    public static final Cell D3 = new Cell(3,2);
    public static final Cell E3 = new Cell(4,2);
    public static final Cell F3 = new Cell(5,2);
    public static final Cell G3 = new Cell(6,2);
    public static final Cell H3 = new Cell(7,2);

    public static final Cell A4 = new Cell(0,3);
    public static final Cell B4 = new Cell(1,3);
    public static final Cell C4 = new Cell(2,3);
    public static final Cell D4 = new Cell(3,3);
    public static final Cell E4 = new Cell(4,3);
    public static final Cell F4 = new Cell(5,3);
    public static final Cell G4 = new Cell(6,3);
    public static final Cell H4 = new Cell(7,3);

    public static final Cell A5 = new Cell(0,4);
    public static final Cell B5 = new Cell(1,4);
    public static final Cell C5 = new Cell(2,4);
    public static final Cell D5 = new Cell(3,4);
    public static final Cell E5 = new Cell(4,4);
    public static final Cell F5 = new Cell(5,4);
    public static final Cell G5 = new Cell(6,4);
    public static final Cell H5 = new Cell(7,4);

    public static final Cell A6 = new Cell(0,5);
    public static final Cell B6 = new Cell(1,5);
    public static final Cell C6 = new Cell(2,5);
    public static final Cell D6 = new Cell(3,5);
    public static final Cell E6 = new Cell(4,5);
    public static final Cell F6 = new Cell(5,5);
    public static final Cell G6 = new Cell(6,5);
    public static final Cell H6 = new Cell(7,5);

    public static final Cell A7 = new Cell(0,6);
    public static final Cell B7 = new Cell(1,6);
    public static final Cell C7 = new Cell(2,6);
    public static final Cell D7 = new Cell(3,6);
    public static final Cell E7 = new Cell(4,6);
    public static final Cell F7 = new Cell(5,6);
    public static final Cell G7 = new Cell(6,6);
    public static final Cell H7 = new Cell(7,6);

    public static final Cell A8 = new Cell(0,7);
    public static final Cell B8 = new Cell(1,7);
    public static final Cell C8 = new Cell(2,7);
    public static final Cell D8 = new Cell(3,7);
    public static final Cell E8 = new Cell(4,7);
    public static final Cell F8 = new Cell(5,7);
    public static final Cell G8 = new Cell(6,7);
    public static final Cell H8 = new Cell(7,7);

    public static final Cell[][] Cells = {
            {A1,B1,C1,D1,E1,F1,G1,H1},
            {A2,B2,C2,D2,E2,F2,G2,H2},
            {A3,B3,C3,D3,E3,F3,G3,H3},
            {A4,B4,C4,D4,E4,F4,G4,H4},
            {A5,B5,C5,D5,E5,F5,G5,H5},
            {A6,B6,C6,D6,E6,F6,G6,H6},
            {A7,B7,C7,D7,E7,F7,G7,H7},
            {A8,B8,C8,D8,E8,F8,G8,H8}
    };
}
