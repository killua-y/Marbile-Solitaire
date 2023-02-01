package cs3500.marblesolitaire;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * The main method class to create three different board according to the command-line arguments.
 */
public final class MarbleSolitaire {

  /**
   * create three different board according to the command-line arguments:
   * You must pass one of english, european, or triangular,
   * This argument will decide which board shape you should use.
   *
   * <p>You may optionally pass the two arguments -size N, where N is a number,
   * to specify the size of the board.
   *
   * <p>You may optionally pass the three arguments -hole R C, where R and C are numbers,
   * to specify the position of the initial hole.
   *
   * @param args The input command-line arguments
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model;
    String type = "";
    int size = 3;
    boolean sizeChanged = false;
    int row = 3;
    boolean rowChanged = false;
    int col = 3;
    boolean colChanged = false;
    MarbleSolitaireView view;
    MarbleSolitaireController controller;
    if (args[0].equalsIgnoreCase("english")) {
      type = "english";
    } else if (args[0].equalsIgnoreCase("european")) {
      type = "european";
    } else if (args[0].equalsIgnoreCase("triangle")) {
      type = "triangle";
    }
    // when pass 3 argument
    if (args.length == 3) {
      try {
        size = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[2] + " must be an integer.");
        System.exit(1);
      }
      sizeChanged = true;
    }
    // when pass 4 argument
    if (args.length == 4) {
      try {
        row = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[2] + " must be an integer.");
        System.exit(1);
      }
      rowChanged = true;
      try {
        col = Integer.parseInt(args[3]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[3] + " must be an integer.");
        System.exit(1);
      }
      colChanged = true;
    }
    // when pass 6 argument
    if (args.length == 6) {
      try {
        size = Integer.parseInt(args[2]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[2] + " must be an integer.");
        System.exit(1);
      }
      sizeChanged = true;
      try {
        row = Integer.parseInt(args[4]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[2] + " must be an integer.");
        System.exit(1);
      }
      rowChanged = true;
      try {
        col = Integer.parseInt(args[5]);
      } catch (NumberFormatException e) {
        System.err.println("Argument" + args[3] + " must be an integer.");
        System.exit(1);
      }
      colChanged = true;
    }

    if (!sizeChanged) {
      size = 3;
    }
    if (!rowChanged) {
      row = (size * 3 - 2) / 2;
    }
    if (!colChanged) {
      col = (size * 3 - 2) / 2;
    }
    if (type.equals("english")) {
      model = new EnglishSolitaireModel(size, row, col);
      view = new MarbleSolitaireTextView(model);
      controller = new MarbleSolitaireControllerImpl(model, view,
              new BufferedReader(new InputStreamReader(System.in)));
      controller.playGame();
    } else if (type.equals("european")) {
      model = new EuropeanSolitaireModel(size, row, col);
      view = new MarbleSolitaireTextView(model);
      controller = new MarbleSolitaireControllerImpl(model, view,
              new BufferedReader(new InputStreamReader(System.in)));
      controller.playGame();
    } else if (type.equals("triangle")) {
      if (!sizeChanged) {
        size = 5;
      }
      if (!rowChanged) {
        row = 0;
      }
      if (!colChanged) {
        col = 0;
      }
      model = new TriangleSolitaireModel(size, row, col);
      view = new TriangleSolitaireTextView(model);
      controller = new MarbleSolitaireControllerImpl(model, view,
              new BufferedReader(new InputStreamReader(System.in)));
      controller.playGame();
    }
  }
}
