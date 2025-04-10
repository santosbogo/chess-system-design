package edu.austral.dissis.chess.validators.movevalidators.classic;

import edu.austral.dissis.chess.ChessHelper;
import edu.austral.dissis.engine.components.Board;
import edu.austral.dissis.engine.components.Coordinates;
import edu.austral.dissis.engine.components.Piece;
import edu.austral.dissis.engine.enums.PieceColor;
import edu.austral.dissis.engine.validators.movevalidators.MoveValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CantLeaveTheKingThreatenedMoveValidator implements MoveValidator {

  @Override
  public boolean validMove(Coordinates from, Coordinates to, Board board) {
    PieceColor color = board.getColorAt(from);

    Board simulatedBoard =
        new Board(board.getSizeX(), board.getSizeY(), new HashMap<>(board.getPieceDistribution()));
    Map<Coordinates, Piece> simulatedPieceDistribution =
        new HashMap<>(simulatedBoard.getPieceDistribution());

    Piece piece = simulatedPieceDistribution.remove(from);
    simulatedPieceDistribution.put(
        to,
        new Piece(
            piece.getPieceName(), piece.getColor(), piece.getMoveValidators(), piece.getId()));
    simulatedBoard =
        new Board(board.getSizeX(), board.getSizeY(), new HashMap<>(simulatedPieceDistribution));

    return !leavesKingThreatened(simulatedBoard, color);
  }

  private boolean isValidMove(Coordinates from, Coordinates to, Board board) {
    List<MoveValidator> validators = board.getPieceAt(from).getMoveValidators();

    for (MoveValidator validator : validators) {
      if (!(validator instanceof CantLeaveTheKingThreatenedMoveValidator)) {
        if (!validator.validMove(from, to, board)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean leavesKingThreatened(Board board, PieceColor kingColor) {
    Coordinates kingCoordinates = new ChessHelper().getKingCoordinates(board, kingColor);

    for (int x = 1; x <= board.getSizeX(); x++) {
      for (int y = 1; y <= board.getSizeY(); y++) {
        Coordinates from = new Coordinates(x, y);
        Piece fromPiece = board.getPieceAt(from);

        if (fromPiece != null && board.getColorAt(from) != kingColor) {
          if (isValidMove(from, kingCoordinates, board)) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
