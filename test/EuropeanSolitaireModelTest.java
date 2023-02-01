import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for test the method in EuropeanSolitaireModel class.
 */
public class EuropeanSolitaireModelTest {
  MarbleSolitaireModel type1;
  MarbleSolitaireView type1v;
  MarbleSolitaireModel type2;
  MarbleSolitaireView type2v;
  MarbleSolitaireModel type3;
  MarbleSolitaireView type3v;

  @Before
  public void setup() {
    type1 = new EuropeanSolitaireModel();
    type1v = new MarbleSolitaireTextView(type1);
    type2 = new EuropeanSolitaireModel(2, 3);
    type2v = new MarbleSolitaireTextView(type2);
    type3 = new EuropeanSolitaireModel(5);
    type3v = new MarbleSolitaireTextView(type3);
  }

  @Test
  public void ConstructorException() {
    try {
      MarbleSolitaireModel t1 = new EuropeanSolitaireModel(4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness must be positive odd number", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new EuropeanSolitaireModel(0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness must be positive odd number", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new EuropeanSolitaireModel(-3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness must be positive odd number", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new EuropeanSolitaireModel(-1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new EuropeanSolitaireModel(3, 7);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new EuropeanSolitaireModel(0, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)", e.getMessage());
    }
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, type1.getSlotAt(2,2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, type1.getSlotAt(3,3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, type1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, type1.getSlotAt(0,1));
  }

  @Test
  public void testGetScore() {
    assertEquals(36, type1.getScore());
    type1.move(5, 3, 3,3);
    type1.move(2, 3, 4,3);
    type1.move(0, 3, 2,3);
    assertEquals(33, type1.getScore());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, type1.getBoardSize());
    assertEquals(13, type3.getBoardSize());
  }

  @Test
  public void testInvalidMove() {
    try {
      type1.move(4,3,2,3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    type2.move(4, 3, 2, 3);
    type2.move(1, 3, 3, 3);
    type2.move(6, 3, 4, 3);
    type2.move(3, 3, 5, 3);
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O _ O", type2v.toString());
    try {
      // jump two slot
      type2.move(3,0,3,3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    try {
      // jump over an empty slot
      type2.move(5,3,3,3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    try {
      // from position is empty
      type2.move(6,3,4,3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    try {
      // to position has a marble
      type2.move(3,0,3,2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
  }

  @Test
  public void testMove() {
    // move up
    type2.move(4, 3, 2, 3);
    // move down
    type2.move(1, 3, 3, 3);
    type2.move(6, 3, 4, 3);
    type2.move(3, 3, 5, 3);
    // move to right
    type2.move(1, 1, 1, 3);
    // move to left
    type2.move(2, 5, 2, 3);
    assertEquals("    O O O\n" +
            "  _ _ O O O\n" +
            "O O O O _ _ O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O _ O", type2v.toString());
  }

  @Test
  public void testIsGameOver() {
    assertEquals(false, type2.isGameOver());
    type2.move(4, 3, 2, 3);
    type2.move(1, 3, 3, 3);
    type2.move(6, 3, 4, 3);
    type2.move(3, 3, 5, 3);
    type2.move(1, 1, 1, 3);
    type2.move(2, 5, 2, 3);
    type2.move(4, 1, 4, 3);
    type2.move(4, 4, 4, 2);
    type2.move(1, 4, 1, 2);
    type2.move(3, 5, 3, 3);
    type2.move(2, 2, 2, 4);
    type2.move(3, 2, 3, 4);
    type2.move(2, 4, 4, 4);
    type2.move(4, 5, 4, 3);
    type2.move(5, 2, 3, 2);
    type2.move(5, 4, 5, 2);
    type2.move(3, 1, 3, 3);
    type2.move(5, 1, 5, 3);
    type2.move(4, 3, 2, 3);
    type2.move(2, 0, 2, 2);
    type2.move(4, 0, 2, 0);
    type2.move(1, 2, 3, 2);
    assertEquals(true, type2.isGameOver());
  }


}