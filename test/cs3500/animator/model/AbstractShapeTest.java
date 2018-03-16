package cs3500.animator.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The abstract shape test, which tests common methods for different methods,
 * but also test their different result of override method.
 */
public abstract class AbstractShapeTest {
  Position2D position1 = new Position2D((float) 200.0, (float) 200.0);
  Position2D position2 = new Position2D((float) 300.0, (float) 300.0);
  Position2D position3 = new Position2D((float) 500.0, (float) 100.0);
  ShapeType rectype = ShapeType.RECTANGLE;
  ShapeType ovalType = ShapeType.OVAL;
  ColorInfo blue1 = new ColorInfo((float)0.0, (float) 0.0,(float) 1.0);
  ColorInfo red1 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
  ColorInfo green1 = new ColorInfo((float)0.0, (float) 1.0,(float) 0.0);
  ColorInfo color1 = new ColorInfo((float)0.78, (float) 0.6,(float) 0.8);

  /**
   *  Constructs an instance of the abstract shape class
   *  under test representing the shape given in common parameters for the shape.
   * @param name the name of the shape
   * @param color the color of this shape
   * @param position the position of this shape
   * @param appears the appears time of the shape
   * @param disappears the disappears time of the shape
   * @return an instance of the shape class under test
   */
  protected abstract AbstractShape createShape(String name, ColorInfo color, Position2D position,
                                               int appears, int disappears);

  /**
   * The subclass test of abstractShapeTest for testing rectangle.
   */
  public static final class RectangleTest extends AbstractShapeTest {
    @Override
    protected  AbstractShape createShape(String name, ColorInfo color, Position2D position,
                                         int appears, int disappears) {
      float width = (float) 50.0;
      float height = (float) 100.0;
      return new Rectangle(name, ShapeType.RECTANGLE, color, position, appears,
              disappears, width, height);
    }

    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, position1, 1, 100, (float) 50.0, (float) 100.0);

    // different result of printing the string of rectangle information
    @Test
    public void getInfor() throws Exception {
      assertEquals(
              "Name: R\r\n" + "Type: rectangle\r\n"
                      + "Lower-left corner: (200.0,200.0), Width: 50.0, Height:"
                      + " 100.0, Color: (1.0,0.0,0.0)\r\n" + "Appears at t=1\r\n"
                      + "Disappears at t=100", rect1.getInfor());
    }

    // Test for getting type of the shape.
    @Test
    public void testForGetType() {
      assertEquals(rectype, rect1.getType());
    }

    //invalid given type of shape for the shape
    @Test (expected = IllegalArgumentException.class)
    public void illegalType() {
      AbstractShape rect1 = new Rectangle("R", ovalType,
              red1, position1, 1, 100, (float) 50.0, (float) 100.0);
      assertEquals(
              "Name: R\r\n" + "Type: rectangle\r\n"
                      + "Lower-left corner: (200.0,200.0), Width: 50.0, Height:"
                      + " 100.0, Color: (1.0,0.0,0.0)\r\n" + "Appears at t=1\r\n"
                      + "Disappears at t=100", rect1.getInfor());
    }

    // Check get weight part of rectangle class by using getWidth method.
    @Test
    public void testGetWidth() {
      assertEquals(rect1.getWidth(), 50.0, 0.000001);
    }

    // Check get height part of rectangle class by using getHeight method.
    @Test
    public void testGetHeight() {
      assertEquals(rect1.getHeight(), 100.0, 0.000001);
    }

  }

  /**
   * The subclass test of abstractShapeTest for testing oval.
   */
  public static final class OvalTest extends AbstractShapeTest {
    @Override
    protected  AbstractShape createShape(String name, ColorInfo color,
                                         Position2D position, int appears, int disappears) {
      float xRadius = (float) 60.0;
      float yRadius = (float) 30.0;
      return new Oval(name, ShapeType.OVAL, color, position, appears,
              disappears, xRadius, yRadius);
    }

    AbstractShape oval1 = new Oval("C", ovalType,
            blue1, position3, 6, 100, (float) 60.0, (float) 30.0);

    // different result of printing the string of oval information
    @Test
    public void getInfor() throws Exception {
      assertEquals(
              "Name: C\r\n" + "Type: oval\r\n" + "Center: (500.0,100.0), "
                      + "X radius: 60.0, " + "Y radius: 30.0, Color: (0.0,0.0,1.0)\r\n"
                      + "Appears at t=6\r\n" + "Disappears at t=100", oval1.getInfor());
    }

    // Test for getting type of the shape.
    @Test
    public void testForGetType() {
      assertEquals(ovalType, oval1.getType());
    }

    //invalid given type of shape for the shape
    @Test (expected = IllegalArgumentException.class)
    public void illegalType() {
      AbstractShape oval1 = new Oval("C", rectype,
              blue1, position3, 6, 100, (float) 60.0, (float) 30.0);
      assertEquals(
              "Name: C\r\n" + "Type: oval\r\n" + "Center: (500.0,100.0), "
                      + "X radius: 60.0, " + "Y radius: 30.0, Color: (0.0,0.0,1.0)\r\n"
                      + "Appears at t=6\r\n" + "Disappears at t=100", oval1.getInfor());
    }

    // Check get xRadius part of rectangle class by using getWidth method.
    @Test
    public void testGetWidth() {
      assertEquals(oval1.getWidth(), 60.0, 0.000001);
    }

    // Check get yRadius part of rectangle class by using getHeight method.
    @Test
    public void testGetHeight() {
      assertEquals(oval1.getHeight(), 30.0, 0.000001);
    }

  }

  AbstractShape rec1 = createShape("R2", green1,
          position2, 10, 30);
  AbstractShape oval1 = createShape("O2", color1,
          position1, 3, 40);

  // Test for getPosition method
  @Test
  public void testGetPosition() {
    assertEquals(rec1.getPosition().getX(), 300, 0.000001);
    assertEquals(rec1.getPosition().getY(), 300, 0.000001);
    assertEquals(oval1.getPosition().getX(), 200, 0.000001);
    assertEquals(oval1.getPosition().getY(), 200, 0.000001);
  }


  //Test for getColorInfor method
  @Test
  public void testGetColor() {
    assertEquals(rec1.getColor().getRed(), 0.0, 0.000001);
    assertEquals(rec1.getColor().getGreen(), 1.0, 0.000001);
    assertEquals(rec1.getColor().getBlue(), 0.0, 0.000001);
    assertEquals(oval1.getColor().getRed(), 0.78, 0.000001);
    assertEquals(oval1.getColor().getGreen(), 0.6, 0.000001);
    assertEquals(oval1.getColor().getBlue(), 0.8, 0.000001);
  }

  // Test for getting name of the shape.
  @Test
  public void testForGetName() {
    assertEquals("R2", rec1.getName());
    assertEquals("O2", oval1.getName());
  }

  // Test for getting appear time of the shape.
  @Test
  public void testForGetAppear() {
    assertEquals(10, rec1.getAppear());
  }

  // Test for getting disappear time of the shape.
  @Test
  public void testForGetDisappear() {
    assertEquals(40, oval1.getDisappear());
  }

  // Test the illegal shape class and its appear nad disappear invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalShapeTime() {

    createShape("Any", green1, position2, -1, 30);
  }

  // Test the illegal shape class and its appear nad disappear invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalShapeTime2() {

    createShape("Any", green1, position2, 9, -5);
  }

  // Test the illegal shape class and its appear nad disappear invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalShapeTime3() {

    createShape("Any", green1, position2, 9, 4);
  }

  // Test the illegal shape class and its position invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalShapePosition() {
    createShape("Any",  green1, null, 3, 4);
  }

  // Test the illegal shape class and its color invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalShapeColor() {

    createShape("Any",  null, position1, 3, 4);
  }


}