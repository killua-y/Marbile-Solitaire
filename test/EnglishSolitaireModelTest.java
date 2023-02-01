import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * test for EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {

  @Test
  public void testModelIllegal() {
    try {
      MarbleSolitaireModel type2a = new EnglishSolitaireModel(0,0);
      MarbleSolitaireModel type4b = new EnglishSolitaireModel(3, 0,0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,0)", e.getMessage());
    }
    try {
      MarbleSolitaireModel type2b = new EnglishSolitaireModel(-1,0);
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
    try {
      MarbleSolitaireModel type3a = new EnglishSolitaireModel(2);
      MarbleSolitaireModel type4a = new EnglishSolitaireModel(2, 1,1);
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness must be positive odd number", e.getMessage());
    }
  }

  @Test
  public void testInitializeBoard() {
    MarbleSolitaireView type1 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    MarbleSolitaireView type2 = new MarbleSolitaireTextView(
            new EnglishSolitaireModel(3,2));
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", type1.toString());

    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", type2.toString());
  }

  @Test
  public void testMoveIllegal() {
    EnglishSolitaireModel type1 = new EnglishSolitaireModel();
    try {
      type1.move(0, 3, 3,3);
      type1.move(0, 3, 2,3);
      type1.move(2, 3, 4,3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
  }

  @Test
  public void testMove() {
    MarbleSolitaireModel type1 = new EnglishSolitaireModel();
    MarbleSolitaireView type1view = new MarbleSolitaireTextView(type1);
    type1.move(3,1,3,3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", type1view.toString());
    type1.move(1,2,3,2);
    assertEquals("    O O O\n"
            + "    _ O O\n"
            + "O O _ O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", type1view.toString());
  }

  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel type1 = new EnglishSolitaireModel();
    MarbleSolitaireModel type3 = new EnglishSolitaireModel(5);
    assertEquals(7,type1.getBoardSize());
    assertEquals(13,type3.getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    MarbleSolitaireModel type1 = new EnglishSolitaireModel();
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, type1.getSlotAt(3,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, type1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, type1.getSlotAt(3,3));
  }

  @Test
  public void testGetSlotAtIllegal() {
    MarbleSolitaireModel type1 = new EnglishSolitaireModel();
    try {
      type1.getSlotAt(-1,0);
      type1.getSlotAt(10,10);
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
  }

  @Test
  public void testGetScore() {
    MarbleSolitaireModel type1 = new EnglishSolitaireModel();
    assertEquals(32, type1.getScore());
    type1.move(3,1,3,3);
    assertEquals(31, type1.getScore());
  }

  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel type1 = new EnglishSolitaireModel();
    assertEquals(false, type1.isGameOver());
    type1.move(3,5,3,3);
    type1.move(3,2,3,4);
    type1.move(3,0,3,2);
    type1.move(1,3,3,3);
    type1.move(4,3,2,3);
    type1.move(6,3,4,3);
    assertEquals(true, type1.isGameOver());
  }
}
