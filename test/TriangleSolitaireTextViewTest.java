import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * test class for the method in TriangleSolitaireTextView class.
 */
public class TriangleSolitaireTextViewTest {
  MarbleSolitaireModel type1;
  MarbleSolitaireView type1v;
  MarbleSolitaireModel type2;
  MarbleSolitaireView type2v;
  MarbleSolitaireModel type3;
  MarbleSolitaireView type3v;
  MarbleSolitaireModel type4;
  MarbleSolitaireView type4v;

  @Before
  public void setup() {
    type1 = new TriangleSolitaireModel();
    type1v = new TriangleSolitaireTextView(type1);
    type2 = new TriangleSolitaireModel(6);
    type2v = new TriangleSolitaireTextView(type2);
    type3 = new TriangleSolitaireModel(2, 2);
    type3v = new TriangleSolitaireTextView(type3);
    type4 = new TriangleSolitaireModel(3, 1, 0);
    type4v = new TriangleSolitaireTextView(type4);
  }

  @Test
  public void testToString() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", type1v.toString());
    assertEquals("     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O", type2v.toString());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O _\n" +
            " O O O O\n" +
            "O O O O O", type3v.toString());
    assertEquals("  O\n" +
            " _ O\n" +
            "O O O", type4v.toString());
  }

}