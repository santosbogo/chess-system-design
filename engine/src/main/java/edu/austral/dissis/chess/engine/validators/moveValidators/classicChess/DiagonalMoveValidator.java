package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class DiagonalMoveValidator implements MoveValidator {
  private final int maxSquares;

  public DiagonalMoveValidator(int maxSquares) {
    this.maxSquares = maxSquares;
  }

  public DiagonalMoveValidator() {
    this.maxSquares = 2147483647;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    return isDiagonalMove(from, to) && isInRange(from, to);
  }

  private boolean isDiagonalMove(Coordinates from, Coordinates move) {
    return from.getX() - move.getX() == from.getY() - move.getY();
  }

  private boolean isInRange(Coordinates from, Coordinates to) {
    return Math.abs(to.getX() - from.getX()) <= maxSquares;
  }
}
