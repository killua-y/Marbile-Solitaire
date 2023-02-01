package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * a abstract class for the class MarbleSolitaireTextView and TriangleSolitaireTextView.
 */
public abstract class ATextView implements MarbleSolitaireView {
  MarbleSolitaireModelState model;
  final Appendable output;

  /**
   * the construct takes in a marble solitaire model state.
   * @param model the marble solitaire model state
   * @throws IllegalArgumentException if the model is null
   */
  public ATextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("model or output cannot be null");
    }
    this.model = model;
    this.output = System.out;
  }

  /**
   * the construct takes in a marble solitaire model state.
   * @param model the marble solitaire model state
   * @param output the output goes to controller
   * @throws IllegalArgumentException if the model or output is null
   */
  public ATextView(MarbleSolitaireModelState model, Appendable output)
          throws IllegalArgumentException {
    if ((output == null) || (model == null)) {
      throw new IllegalArgumentException("model or output cannot be null");
    }
    this.model = model;
    this.output = output;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < this.model.getBoardSize(); i++) {
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

  /**
   * switch a slot state into a string.
   *
   * @param slot the slot state.
   * @return a single string which shows the state of a slot
   */
  public String slotToString(MarbleSolitaireModelState.SlotState slot) {
    String toReturn = "";

    switch (slot) {
      case Empty:
        toReturn = "_";
        break;
      case Marble:
        toReturn = "O";
        break;
      case Invalid:
        toReturn = " ";
        break;
      default:
        throw new IllegalArgumentException("Invalid slot type.");
    }
    return toReturn;
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      this.output.append(this.toString() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Cannot transmit output.");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot transmit output.");
    }
  }

}
