package cs3500.animator.model;

import org.junit.Test;

import cs3500.animator.model.ColorInfo;

import static org.junit.Assert.assertEquals;

/**
 * Test for class ColorInfo and its methods.
 */
public class ColorInfoTest {
  ColorInfo blue1 = new ColorInfo((float)0.0, (float) 0.0,(float) 1.0);
  ColorInfo red1 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
  ColorInfo green1 = new ColorInfo((float)0.0, (float) 1.0,(float) 0.0);
  ColorInfo color1 = new ColorInfo((float)0.78, (float) 0.6,(float) 0.8);


  //Test for creating one string to represent the information of this class.
  @Test
  public void testColorState() throws Exception {
    assertEquals("(0.0,0.0,1.0)", blue1.colorState());
    assertEquals("(1.0,0.0,0.0)", red1.colorState());
    assertEquals("(0.0,1.0,0.0)", green1.colorState());
    assertEquals("(0.78,0.6,0.8)", color1.colorState());
  }

  // Test get red, green and blue part get methods in the ColorInfor class.
  @Test
  public void testGetColorMethod() {
    assertEquals(blue1.getRed(),  0.0, 0.000001);
    assertEquals(blue1.getGreen(), 0.0, 0.000001);
    assertEquals(blue1.getBlue(), 1.0, 0.000001);
    assertEquals(color1.getRed(), 0.78,0.000001 );
    assertEquals(color1.getGreen(),  0.6, 0.000001);
    assertEquals(color1.getBlue(), 0.8, 0.000001);
  }

}