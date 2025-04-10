package edu.austral.dissis.chess.validators.movevalidators.classic;

import edu.austral.dissis.chess.ChessHelper;
import edu.austral.dissis.chess.enums.ChessPieceNames;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.move.Mover;
import edu.austral.dissis.engine.referees.MoveReferee;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;
import java.util.Collections;

public class LongCastleMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {

    if (from.getY() != to.getY() || to.getX() != 3) {
      return false;
    }

    // From is a king, never moved, not in check
    Piece king = board.getPieceAt(from);
    if (king == null
        || king.getPieceName() != ChessPieceNames.KING
        || !king.isFirstMove()
        || new ChessHelper().isSquareThreatened(board, from)) {
      return false;
    }

    PieceColor color = king.getColor();

    // In column A there is a rook, never moved
    Piece rook = board.getPieceAt(new Coordinates('A', from.getY()));
    if (rook == null || rook.getPieceName() != ChessPieceNames.ROOK || !rook.isFirstMove()) {
      return false;
    }

    if (!board.isEmptySquare(new Coordinates('B', from.getY()))) {
      return false;
    }

    // Can King move two valid steps to the left?
    if (new MoveReferee(color, board).isValidMove(from, new Coordinates('D', from.getY()))) {
      Mover mover =
          new Mover(board, Collections.emptyList(), from, new Coordinates('D', from.getY()));
      board = mover.getNextBoard();
      from = new Coordinates('D', from.getY()); // Update king coordinates
      return new MoveReferee(color, board).isValidMove(from, new Coordinates('C', from.getY()));
    }
    return false;
  }
}
