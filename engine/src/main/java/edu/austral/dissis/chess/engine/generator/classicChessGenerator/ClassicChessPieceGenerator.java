package edu.austral.dissis.chess.engine.generator.classicChessGenerator;

import edu.austral.dissis.chess.engine.enums.PieceColor;
import edu.austral.dissis.chess.engine.enums.PieceName;
import edu.austral.dissis.chess.engine.generator.PieceGenerator;
import edu.austral.dissis.chess.engine.pieces.Piece;
import edu.austral.dissis.chess.engine.validators.basicMovesValidators.*;
import edu.austral.dissis.chess.engine.validators.classicChessMoveValidators.JustForwardMoveValidator;

public class ClassicChessPieceGenerator implements PieceGenerator {

    @Override
    public Piece generatePiece(PieceName pieceName, PieceColor pieceColor) {
        return switch (pieceName) {
            case PAWN -> generatePawn(pieceColor);
            case ROOK -> generateRook(pieceColor);
            case KNIGHT -> generateKnight(pieceColor);
            case BISHOP -> generateBishop(pieceColor);
            case QUEEN -> generateQueen(pieceColor);
            case KING -> generateKing(pieceColor);
        };
    }

    private Piece generatePawn(PieceColor pieceColor){
        Piece piece = new Piece(PieceName.PAWN, pieceColor);

        piece.addMoveValidator(new HorizontalMoveValidator(1));
        piece.addMoveValidator(new ClearPathValidator());
        piece.addMoveValidator(new JustForwardMoveValidator(pieceColor));

        return piece;
    }

    private Piece generateRook(PieceColor pieceColor){
        Piece piece = new Piece(PieceName.ROOK, pieceColor);

        piece.addMoveValidator(new HorizontalMoveValidator());
        piece.addMoveValidator(new VerticalMoveValidator());
        piece.addMoveValidator(new ClearPathValidator());

        return piece;
    }

    private Piece generateKnight(PieceColor pieceColor){
        Piece piece = new Piece(PieceName.KNIGHT, pieceColor);

        piece.addMoveValidator(new KnightMoveValidator());

        return piece;
    }

    private Piece generateBishop(PieceColor pieceColor){
        Piece piece = new Piece(PieceName.BISHOP, pieceColor);

        piece.addMoveValidator(new DiagonalMoveValidator());
        piece.addMoveValidator(new ClearPathValidator());

        return piece;
    }

    private Piece generateQueen(PieceColor pieceColor){
        Piece piece = new Piece(PieceName.QUEEN, pieceColor);

        piece.addMoveValidator(new HorizontalMoveValidator());
        piece.addMoveValidator(new VerticalMoveValidator());
        piece.addMoveValidator(new DiagonalMoveValidator());
        piece.addMoveValidator(new ClearPathValidator());

        return piece;
    }

    private Piece generateKing(PieceColor pieceColor){
        Piece piece = new Piece(PieceName.KING, pieceColor);

        piece.addMoveValidator(new HorizontalMoveValidator(1));
        piece.addMoveValidator(new VerticalMoveValidator(1));
        piece.addMoveValidator(new DiagonalMoveValidator(1));
        piece.addMoveValidator(new ClearPathValidator());

        return piece;
    }




}
