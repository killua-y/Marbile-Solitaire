package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * A class for the method to create the text view for triangle solitaire model.
 */
public class TriangleSolitaireTextView extends ATextView {

  /**
   * the construct takes in a marble solitaire model state.
   * @param model the marble solitaire model state
   * @throws IllegalArgumentException if the model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * the construct takes in a marble solitaire model state.
   * @param model the marble solitaire model state
   * @param output the output goes to controller
   * @throws IllegalArgumentException if the model or output is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable output)
          throws IllegalArgumentException {
    super(model, output);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.model.getBoardSize(); i++) {
      for (int space = Math.abs(i - this.model.getBoardSize()) - 2; space >= 0; space--) {
        result.append(" ");
      }
      for (int j = 0; j < this.model.getBoardSize(); j++) {
        result.append(slotToString(this.model.getSlotAt(i, j)) + " ");
      }
      for (int k = result.length() - 1; k >= 0; k--) {
        if (result.charAt(k) == ' ') {
          result.deleteCharAt(k);
        } else {
          break;
        }
      }
      result.append("\n");
    }
    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }
}
