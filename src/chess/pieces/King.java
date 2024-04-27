package chess.pieces;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import gameboard.Board;
import gameboard.Position;

import java.util.Arrays;
import java.util.List;

public class King extends ChessPiece {
    private final List<Position> DIRECTIONS = Arrays.asList(
            new Position(-1, -1),
            new Position(-1, 0),
            new Position(-1, 1),
            new Position(0, -1),
            new Position(0, 1),
            new Position(1, -1),
            new Position(1, 0),
            new Position(1, 1)
    );

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
        //true == enemy piece
    }

    private boolean testRookCastling(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);
        p.setValues(position.getRow(), position.getColumn());
        for (Position temp : DIRECTIONS) {
            p.setValues(position.getRow() + temp.getRow(), position.getColumn() + temp.getColumn());
            if (getBoard().positionExists(p) && canMove(p)) matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(position.getRow(), position.getColumn());
        }

        // #Special move castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // #Specal move castling kingside Rook
            Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(posT1)) {
                Position p1 = new Position(position.getRow(), position.getColumn() + 1);
                Position p2 = new Position(position.getRow(), position.getColumn() + 2);
                if (!getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2)) {
                    matrix[p2.getRow()][p2.getColumn()] = true;
                }
            }
            Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(posT2)) {
                Position p1 = new Position(position.getRow(), position.getColumn() - 1);
                Position p2 = new Position(position.getRow(), position.getColumn() - 2);
                Position p3 = new Position(position.getRow(), position.getColumn() - 3);
                if (!getBoard().thereIsAPiece(p1) && !getBoard().thereIsAPiece(p2) && !getBoard().thereIsAPiece(p3)) {
                    matrix[p2.getRow()][p2.getColumn()] = true;
                }
            }
        }
        return matrix;
    }
}
