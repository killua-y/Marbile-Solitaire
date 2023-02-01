import org.junit.Before;
import org.junit.Test;


import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

/**
 * test class of MarbleSolitaireTextViewTest class.
 */
public class MarbleSolitaireTextViewTest {
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
    type1 = new EuropeanSolitaireModel();
    type1v = new MarbleSolitaireTextView(type1);
    type2 = new EuropeanSolitaireModel(5);
    type2v = new MarbleSolitaireTextView(type2);
    type3 = new EuropeanSolitaireModel(1,1);
    type3v = new MarbleSolitaireTextView(type3);
    type4 = new EuropeanSolitaireModel(5, 1, 3);
    type4v = new MarbleSolitaireTextView(type4);
  }

  @Test
  public void testToStringEnglish() {
    MarbleSolitaireView etype1 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    MarbleSolitaireView etype3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5));
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", etype1.toString());
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", etype3.toString());
  }

  @Test
  public void testToStringEuropean() {
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", type1v.toString());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", type2v.toString());
    assertEquals("    O O O\n" +
            "  _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", type3v.toString());
    assertEquals("        O O O O O\n" +
            "      _ O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", type4v.toString());
  }
}