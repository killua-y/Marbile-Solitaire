package cs3500.marblesolitaire.model.hw02;

/**
 * the abstract class for EnglishSolitaire, EuropeanSolitaire, and TriangleSolitaire.
 */
public abstract class AMarbleSolitaire implements MarbleSolitaireModel {
  protected int thickness;
  int sRow;
  int sCol;
  SlotState[][] board;

  /**
   * initialize the game board with arm thickness as 3 and an empty slot in the middle.
   */
  public AMarbleSolitaire() {
    this.thickness = 3;
    this.sRow = 3;
    this.sCol = 3;
    this.board = this.initializeBoard();
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * initialize the game board with arm thickness as 3
   * and an empty slot with the given row number and column number.
   *
   * @param sRow the row number of the empty slot on the board
   * @param sCol the column number of the empty slot on the board
   * @throws IllegalArgumentException if the row or the column are invalid or beyond
   *                                  the dimensions of the board
   */
  public AMarbleSolitaire(int sRow, int sCol) throws IllegalArgumentException {
    this.thickness = 3;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = this.initializeBoard();
    if (this.getSlotAt(sRow, sCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * initialize the game board with the provide arm thickness and an empty slot in the middle.
   *
   * @param thickness the arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is an even number
   */
  public AMarbleSolitaire(int thickness) throws IllegalArgumentException {
    if ((thickness % 2 == 0) || (thickness < 0)) {
      throw new IllegalArgumentException("Arm thickness must be positive odd number");
    }
    this.thickness = thickness;
    this.sRow = this.getBoardSize() / 2;
    this.sCol = this.getBoardSize() / 2;
    this.board = this.initializeBoard();
    this.board[sRow][sCol] = SlotState.Empty;
  }

  /**
   * initialize the game board with the provide arm thickness
   * and an empty slot with the given row number and column number.
   *
   * @param thickness the arm thickness of the board
   * @param sRow      the row number of the empty slot on the board
   * @param sCol      the column number of the empty slot on the board
   * @throws IllegalArgumentException if the arm thickness is an even number
   *                                  or the empty slot is beyond the dimensions of the board
   */
  public AMarbleSolitaire(int thickness, int sRow, int sCol) throws IllegalArgumentException {
    if ((thickness % 2 == 0) || (thickness < 0)) {
      throw new IllegalArgumentException("Arm thickness must be positive odd number");
    }
    this.thickness = thickness;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = this.initializeBoard();
    if (this.getSlotAt(sRow, sCol) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.board[sRow][sCol] = SlotState.Empty;
  }

  abstract protected SlotState[][] initializeBoard();

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
  public boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0
            || fromRow >= this.getBoardSize() || fromCol >= this.getBoardSize()
            || toRow >= this.getBoardSize() || toCol >= this.getBoardSize()) {
      return false;
    } else if (this.getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      return false;
    } else if (this.getSlotAt(toRow, toCol) != SlotState.Empty) {
      return false;
    } else if (!((fromRow == toRow && Math.abs(fromCol - toCol) == 2)
            || (fromCol == toCol && Math.abs(fromRow - toRow) == 2))) {
      return false;
    } else {
      return this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
              == SlotState.Marble;
    }
  }

  @Override
  public int getBoardSize() {
    return this.thickness * 3 - 2;
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
        if (this.canMove(i, j, i + 2, j)
                || this.canMove(i, j, i - 2, j)
                || this.canMove(i, j, i, j + 2)
                || this.canMove(i, j, i, j - 2)) {
          return false;
        }
      }
    }
    return true;
  }
}
