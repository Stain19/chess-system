package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends ChessPiece {
    private final List<Position> DIRECTIONS = Arrays.asList(
            new Position(-2,-1),
            new Position(-2,1),
            new Position(-1,-2),
            new Position(-1,2),
            new Position(1,-2),
            new Position(1,2),
            new Position(2,-1),
            new Position(2,1)
    );
    public Knight(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
        //true == enemy piece
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);
        p.setValues(position.getRow(), position.getColumn());
        for(Position temp : DIRECTIONS){
            p.setValues(position.getRow() + temp.getRow(), position.getColumn() + temp.getColumn());
            if(getBoard().positionExists(p) && canMove(p)) matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(position.getRow(), position.getColumn());
        }
        return matrix;
    }
}
