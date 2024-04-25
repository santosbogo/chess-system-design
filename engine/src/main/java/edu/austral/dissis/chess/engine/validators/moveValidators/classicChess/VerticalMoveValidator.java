package edu.austral.dissis.chess.engine.validators.moveValidators.classicChess;

import edu.austral.dissis.chess.engine.buenos.Board;
import edu.austral.dissis.chess.engine.buenos.Coordinates;
import edu.austral.dissis.chess.engine.validators.moveValidators.MoveValidator;

public class VerticalMoveValidator implements MoveValidator {
  private final int maxSquares;

  public VerticalMoveValidator(int maxSquares) {
    this.maxSquares = maxSquares;
  }

  public VerticalMoveValidator() {
    this.maxSquares = 2147483647;
  }

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    return isVerticalMove(from, to) && isInRange(from, to);
  }

  private boolean isVerticalMove(Coordinates from, Coordinates move) {
    return from.getY() == move.getY();
  }

  private boolean isInRange(Coordinates from, Coordinates to) {
    return Math.abs(to.getY() - from.getY()) <= maxSquares;
  }
}
