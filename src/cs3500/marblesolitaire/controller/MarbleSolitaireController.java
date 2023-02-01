package cs3500.marblesolitaire.controller;

/**
 * represent the interface for control the game.
 */
public interface MarbleSolitaireController {
  /**
   * play a new game of Marble Solitaire.
   * @throws IllegalStateException if the input or output cannot be processed.
   */
  void playGame() throws IllegalStateException ;
}
