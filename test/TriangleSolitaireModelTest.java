import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test class for test the method in TriangleSolitaireModel class.
 */
public class TriangleSolitaireModelTest {
  MarbleSolitaireModel type1;
  MarbleSolitaireView type1v;
  MarbleSolitaireModel type4;
  MarbleSolitaireView type4v;

  @Before
  public void setup() {
    type1 = new TriangleSolitaireModel();
    type1v = new TriangleSolitaireTextView(type1);
    type4 = new TriangleSolitaireModel(6, 4, 2);
    type4v = new TriangleSolitaireTextView(type4);
  }

  @Test
  public void testInitializeBoard() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", type1v.toString());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(5, type1.getBoardSize());
    assertEquals(6, type4.getBoardSize());
  }

  @Test
  public void testGetSlotAtException() {
    try {
      type1.getSlotAt(999999, 999999);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
    try {
      type1.getSlotAt(6, 6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
    try {
      type1.getSlotAt(-1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
  }

  @Test
  public void testGetSlotAtTri() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, type1.getSlotAt(1,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, type1.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, type1.getSlotAt(0,3));
  }

  @Test
  public void testConstructorException() {
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModel(0, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0,1)", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModel(-1, -1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModel(6,6, 6);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("beyond the dimensions of the board", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModel(0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("dimensions must be positive number", e.getMessage());
    }
    try {
      MarbleSolitaireModel t1 = new TriangleSolitaireModel(-1, 0 ,1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("dimensions must be positive number", e.getMessage());
    }
  }


  @Test
  public void testInvalidMove() {
    try {
      type1.move(0, 2, 0, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    try {
      type1.move(1, 1, 1, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
  }

  @Test
  public void testInvalidMove2() {
    type4.move(4, 4, 4, 2);
    type4.move(2, 2, 4, 4);
    type4.move(2, 0, 2, 2);
    assertEquals("     O\n" +
            "    O O\n" +
            "   _ _ O\n" +
            "  O O O _\n" +
            " O O O _ O\n" +
            "O O O O O O", type4v.toString());
    try {
      type4.move(2, 1, 4, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    try {
      type4.move(0, 0, 2, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    try {
      type4.move(4, 2, 4, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    try {
      type4.move(4, 2, 4, 4);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
    type4.move(4, 1, 4, 3);
    assertEquals("     O\n" +
            "    O O\n" +
            "   _ _ O\n" +
            "  O O O _\n" +
            " O _ _ O O\n" +
            "O O O O O O", type4v.toString());
    try {
      type4.move(4, 0, 4, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
  }


  @Test
  public void testMove1() {
    type1.move(2, 0, 0, 0);
    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", type1v.toString());
  }

  @Test
  public void testMove2() {
    type1.move(2, 2, 0, 0);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O", type1v.toString());
  }

  @Test
  public void testMove3() {
    type4.move(2, 0, 4, 2);
    assertEquals("     O\n" +
            "    O O\n" +
            "   _ O O\n" +
            "  O _ O O\n" +
            " O O O O O\n" +
            "O O O O O O", type4v.toString());
  }

  @Test
  public void testMove4() {
    type4.move(2, 2, 4, 2);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O _\n" +
            "  O O _ O\n" +
            " O O O O O\n" +
            "O O O O O O", type4v.toString());
  }

  @Test
  public void testMove5() {
    type4.move(4, 0, 4, 2);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " _ _ O O O\n" +
            "O O O O O O", type4v.toString());
  }

  @Test
  public void testMove6() {
    type4.move(4, 4, 4, 2);
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O _ _\n" +
            "O O O O O O", type4v.toString());
  }

  @Test
  public void testGetScore() {
    assertEquals(20, type4.getScore());
    type4.move(4, 4, 4, 2);
    type4.move(2, 2, 4, 4);
    type4.move(2, 0, 2, 2);
    assertEquals("     O\n" +
            "    O O\n" +
            "   _ _ O\n" +
            "  O O O _\n" +
            " O O O _ O\n" +
            "O O O O O O", type4v.toString());
    assertEquals(17, type4.getScore());
  }

  @Test
  public void testisGameOver() {
    assertEquals(false, type1.isGameOver());
    MarbleSolitaireModel t1 = new TriangleSolitaireModel(3);
    t1.move(2,0,0,0);
    t1.move(2,2,2,0);
    t1.move(0,0,2,2);
    assertEquals(true, t1.isGameOver());
  }





}