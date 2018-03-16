package cs3500.animator.model;

import org.junit.Test;

import cs3500.animator.model.Position2D;

import static org.junit.Assert.assertEquals;

/**
 * Test for class Position2D and its methods.
 */
public class Position2DTest {
  Position2D position1 = new Position2D((float)200.0, (float)200.0);
  Position2D position2 = new Position2D((float)300.0, (float)-300.0);
  Position2D position3 = new Position2D((float)-500.0, (float)100.0);
  Position2D position4 = new Position2D((float)46.98, (float)46.43);

  //Test for making string to represent the information of Position2D class.
  @Test
  public void testMakeString() throws Exception {
    assertEquals("(200.0,200.0)", position1.makeString());
    assertEquals("(300.0,-300.0)", position2.makeString());
    assertEquals("(-500.0,100.0)", position3.makeString());
    assertEquals("(46.98,46.43)", position4.makeString());

  }

}