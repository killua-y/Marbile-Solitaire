package cs3500.marblesolitaire.model.hw02;

/**
 * this class represent a marble solitaire model.
 */
public class EnglishSolitaireModel extends AMarbleSolitaire {

  /**
   * initialize the game board with arm thickness as 3 and an empty slot in the middle.
   */
  public EnglishSolitaireModel() {
    super();
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
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);
  }

  /**
   * initialize the game board with the provide arm thickness and an empty slot in the middle.
   *
   * @param thickness the arm thickness of the board
   * @throws IllegalArgumentException if the arm thickness is an even number
   */
  public EnglishSolitaireModel(int thickness) throws IllegalArgumentException {
    super(thickness);
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
  public EnglishSolitaireModel(int thickness, int sRow, int sCol) throws IllegalArgumentException {
    super(thickness, sRow, sCol);
  }

  /**
   * initialize the game board.
   *
   * @return the game board
   */
  @Override
  protected SlotState[][] initializeBoard() {
    SlotState[][] result = new SlotState[this.getBoardSize()][this.getBoardSize()];
    int invalidSize = thickness - 1;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (i < invalidSize && j < invalidSize) {
          result[i][j] = SlotState.Invalid;
        } else if (i < invalidSize && j > (invalidSize * 2)) {
          result[i][j] = SlotState.Invalid;
        } else if (i > (invalidSize * 2) && j < invalidSize) {
          result[i][j] = SlotState.Invalid;
        } else if (i > (invalidSize * 2) && j > (invalidSize * 2)) {
          result[i][j] = SlotState.Invalid;
        } else {
          result[i][j] = SlotState.Marble;
        }
      }
    }
    return result;
  }
}
