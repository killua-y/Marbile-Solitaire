package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * represent a controller that control the marble solitaire game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;

  /**
   * the constructor for a controller that control the marble solitaire game.
   * @param model the model of the game
   * @param view the text view of the game
   * @param input the input from the player
   * @throws IllegalArgumentException when the model or view or input is null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("the model or view or input cannot be null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);
    List<String> moveList = new ArrayList<String>();
    Boolean isGameEnd = false;

    this.createBoard();

    while (!model.isGameOver()) {
      if (scan.hasNext()) {
        String position = scan.next();
        if (position.equalsIgnoreCase("q")) {
          this.endBoard();
          return;
        } else {
          try {
            if (Integer.parseInt(position) > 0) {
              moveList.add(position);
            }
          } catch (NumberFormatException e) {
            // ignore the input
          }
        }
        if (moveList.size() >= 4) {
          try {
            this.model.move(Integer.parseInt(moveList.get(0)) - 1,
                    Integer.parseInt(moveList.get(1)) - 1,
                    Integer.parseInt(moveList.get(2)) - 1,
                    Integer.parseInt(moveList.get(3)) - 1);
            moveList = new ArrayList<String>();
            this.createBoard();
          } catch (IllegalArgumentException e) {
            try {
              this.view.renderMessage("Invalid move, try again!");
              moveList = new ArrayList<String>();
            } catch (IOException ex) {
              throw new IllegalStateException("cannot move");
            }
          }
        }
      } else {
        throw new IllegalStateException("input cannot be empty");
      }
    }
    try {
      this.view.renderMessage("Game over!\n");
      this.createBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot end the game.");
    }
  }

  /**
   * Create the Board.
   */
  private void createBoard() {
    try {
      this.view.renderBoard();
      this.view.renderMessage("Score: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Cannot create the board.");
    }
  }

  /**
   * End the game.
   */
  private void endBoard() {
    try {
      this.view.renderMessage("Game quit!\n");
      this.view.renderMessage("State of game when quit:\n");
      this.view.renderBoard();
      this.view.renderMessage("Score: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Cannot end the game.");
    }
  }

}
