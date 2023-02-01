package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents operations that should be offered by
 * a text view for the Marble solitaire game.
 */
public class MarbleSolitaireTextView extends ATextView {

  /**
   * the construct takes in a marble solitaire model state.
   * @param model the marble solitaire model state
   * @throws IllegalArgumentException if the model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    super(model);
  }

  /**
   * the construct takes in a marble solitaire model state.
   * @param model the marble solitaire model state
   * @param output the output goes to controller
   * @throws IllegalArgumentException if the model or output is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable output)
          throws IllegalArgumentException {
    super(model, output);
  }


}
