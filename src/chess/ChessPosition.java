package chess;

import gameboard.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row){
        if (column < 'a' || column > 'h' || row < 1 || row > 8)
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition() {
        // matrix_row = 8 - chess_row
        // matrix_colum = chess_column - 97('a' unicode)
        return new Position(8 - row, (int) column - 97);
    }

    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) (position.getColumn() + 97), 8 - position.getRow() );
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
