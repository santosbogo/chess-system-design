package edu.austral.dissis.chess.engine.pieces;

import edu.austral.dissis.chess.engine.coordinates.Coordinates;
import edu.austral.dissis.chess.engine.enums.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(PieceColor pieceColor){
        super(pieceColor);
    }

    @Override
    public List<Coordinates> possibleMoves() {
        List<Coordinates> posibleMoves = new ArrayList<>();

        //TODO RULES

        return posibleMoves;
    }

    @Override
    public PieceColor getColor() {
        return pieceColor;
    }

    @Override
    public void kill() {
        throw new IllegalStateException("King can't be killed!");
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
