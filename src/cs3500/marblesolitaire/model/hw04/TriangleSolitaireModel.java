package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * this class represent a TriangleSolitaire Model.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {
  private final int dimensions;
  private final int sRow;
  private final int sCol;
  private final SlotState[][] board;

  /**
   * initialize the game board with dimensions as 5 and an empty slot at 0,0.
   */
  public TriangleSolitaireModel() {
    this.dimensions = 5;
    this.sRow = 0;
    this.sCol = 0;
    this.board = this.initializeBoard();
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * initialize the game board with the provided dimensions and an empty slot in the 0,0 position.
   *
   * @param dimensions the dimensions of the board
   * @throws IllegalArgumentException if the arm thickness is non-positive.
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    if (dimensions <= 0) {
      throw new IllegalArgumentException("dimensions must be positive number");
    }
    this.dimensions = dimensions;
    this.sRow = 0;
    this.sCol = 0;
    this.board = this.initializeBoard();
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * initialize the game board with dimensions 6
   * and an empty slot with the given row number and column number.
   *
   * @param sRow the row number of the empty slot on the board
   * @param sCol the column number of the empty slot on the board
   * @throws IllegalArgumentException if the row or the column are invalid or beyond
   *                                  the dimensions of the board
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.dimensions = 5;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = this.initializeBoard();
    if (this.getSlotAt(sRow, sCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * initialize the game board with the provided dimensions
   * and an empty slot with the given row number and column number.
   *
   * @param dimensions the dimensions of the board
   * @param sRow       the row number of the empty slot on the board
   * @param sCol       the column number of the empty slot on the board
   * @throws IllegalArgumentException if the arm thickness is an even number
   *                                  or the empty slot is beyond the dimensions of the board
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol)
          throws IllegalArgumentException {
    if (dimensions <= 0) {
      throw new IllegalArgumentException("dimensions must be positive number");
    }
    this.dimensions = dimensions;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = this.initializeBoard();
    if (this.getSlotAt(sRow, sCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * initialize the game board.
   *
   * @return the game board
   */
  public SlotState[][] initializeBoard() {
    SlotState[][] result = new SlotState[this.getBoardSize()][this.getBoardSize()];
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (i < j) {
          result[i][j] = SlotState.Invalid;
        } else {
          result[i][j] = SlotState.Marble;
        }
      }
    }
    return result;
  }


  /**
   * allow player to move a marble to another position.
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is invalid.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (canMove(fromRow, fromCol, toRow, toCol)) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
    } else {
      throw new IllegalArgumentException("Invalid Move");
    }
  }

  /**
   * determine is it valid to move a marble to an empty position.
   *
   * @param fromRow the row number of the position to be moved from
   * @param fromCol the column number of the position to be moved from
   * @param toRow   the row number of the position to be moved to
   * @param toCol   the column number of the position to be moved to
   * @return return false if the move is not valid, true if the move is valid
   */
  private boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    boolean b = ((((fromRow - toRow) == 2) && (((fromCol - toCol) == 2)))
            || (((fromRow - toRow) == -2) && (((fromCol - toCol) == -2))));
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0
            || fromRow >= this.getBoardSize() || fromCol >= this.getBoardSize()
            || toRow >= this.getBoardSize() || toCol >= this.getBoardSize()) {
      return false;
    } else if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      return false;
    } else if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      return false;
    } else if (((fromRow == toRow) && (Math.abs(fromCol - toCol) == 2))
            || (((fromCol == toCol) && (Math.abs(fromRow - toRow) == 2)))
            || b) {
      return this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
              == SlotState.Marble;
    } else {
      return false;
    }
  }

  @Override
  public int getBoardSize() {
    return this.dimensions;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("beyond the dimensions of the board");
    }
    return this.board[row][col];
  }

  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j) == SlotState.Marble) {
          score += 1;
        }
      }
    }
    return score;
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.canMove(i, j, i, j + 2)
                || this.canMove(i, j, i, j - 2)
                || this.canMove(i, j, i + 2, j)
                || this.canMove(i, j, i - 2, j)
                || this.canMove(i, j, i + 2, j + 2)
                || this.canMove(i, j, i - 2, j - 2)) {
          return false;
        }
      }
    }
    return true;
  }
}


