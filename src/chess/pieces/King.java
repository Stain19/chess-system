package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Position;

import java.util.Arrays;
import java.util.List;

public class King extends ChessPiece {
    private final List<Position> DIRECTIONS = Arrays.asList(
            new Position(-1,-1),
            new Position(-1,0),
            new Position(-1,1),
            new Position(0,-1),
            new Position(0,1),
            new Position(1,-1),
            new Position(1,0),
            new Position(1,1)
            );
    public King(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
        //true == enemy piece
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0,0);
        p.setValues(position.getRow(), position.getColumn());
        for(Position temp : DIRECTIONS){
            p.setValues(p.getRow() + temp.getRow(), p.getColumn() + temp.getColumn());
            if(getBoard().positionExists(p) && canMove(p)) matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(position.getRow(), position.getColumn());
        }
        return matrix;
    }
}
