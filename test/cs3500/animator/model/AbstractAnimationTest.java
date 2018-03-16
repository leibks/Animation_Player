package cs3500.animator.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * The abstract animation test, which tests common methods for different methods,
 * but also test their different result of override method.
 */
public abstract class AbstractAnimationTest {
  Position2D p1 = new Position2D((float) 200.0, (float) 200.0);
  Position2D p2 = new Position2D((float) 300.0, (float) 300.0);
  Position2D p3 = new Position2D((float) 500.0, (float) 100.0);
  Position2D p4 = new Position2D( (float) 500.0,  (float) 400.0);
  ColorInfo blue1 = new ColorInfo((float) 0.0,(float) 0.0,(float) 1.0);
  ColorInfo green1 = new ColorInfo((float) 0.0,(float) 1.0,(float) 0.0);
  Position2D p5 = new Position2D((float) 50.0, (float) 700.0);
  Position2D p6 = new Position2D((float) 70.0, (float) 400.0);
  AnimationType moveT = AnimationType.MOVE;
  AnimationType colorChangeT = AnimationType.COLORCHANGE;
  AnimationType scaleChangeT = AnimationType.SCALECHANGE;
  ShapeType rectype = ShapeType.RECTANGLE;
  ShapeType ovalType = ShapeType.OVAL;

  /**
   *  Constructs an instance of the abstract animation class under test representing
   *  the shape given in common parameters for the animation.
   * @param shapeName the name of the given shape to animation
   * @param startTime the given start time of this animation
   * @param endTime the given end time of this animation
   * @return an instance of the animation class under test
   */
  protected abstract AbstractAnimation createAnimation(String shapeName,
                                                       int startTime, int endTime);

  /**
   * The subclass test of abstractAnimationTest for testing subclass: Move Shape animation.
   */
  public static final class MoveTest extends AbstractAnimationTest {

    @Override
    protected AbstractAnimation createAnimation(String shapeName, int startTime, int endTime) {
      return new MoveAnimation(moveT, shapeName,startTime, endTime, p5, p6);
    }

    AbstractAnimation move1 = new MoveAnimation(moveT, "R",
            10, 50, p1, p2);
    AbstractAnimation move2 = new MoveAnimation(moveT, "C",
            20, 70, p3, p4);

    // different result of printing the string of move information
    @Test
    public void getStringForMove() throws Exception {
      assertEquals("Shape R moves from (200.0,200.0)"
              + " to (300.0,300.0) from t=10 to t=50" , move1.getString());
      assertEquals("Shape C moves from (500.0,100.0) "
              + "to (500.0,400.0) from t=20 to t=70" , move2.getString());
    }

    // Test the method of getAnimationType.
    @Test
    public void testGetAnimationType() throws Exception {
      assertEquals(moveT, move1.getAnimationType());
    }

    //invalid given type of animation for move animation
    @Test (expected = IllegalArgumentException.class)
    public void illegalType() {
      AbstractAnimation move1 = new MoveAnimation(colorChangeT, "R",
              10, 50, p1, p2);
      assertEquals("Shape R moves from (200.0,200.0)"
              + " to (300.0,300.0) from t=10 to t=50" , move1.getString());
    }

    MoveAnimation move3 = new MoveAnimation(moveT, "R",
            10, 50, p3, p4);

    // Test getMoveFrom method to get position move from
    @Test
    public void testGetMoveFrom() throws  Exception {
      assertEquals(move3.getMoveFrom().getX(), 500.0, 0.000001);
      assertEquals(move3.getMoveFrom().getY(), 100.0, 0.000001);
    }

    // Test getMoveTo method to get position move to
    @Test
    public void testGetMoveTo() throws  Exception {
      assertEquals(move3.getMoveTo().getX(), 500.0, 0.000001);
      assertEquals(move3.getMoveTo().getY(), 400.0, 0.000001);
    }

    Position2D pF = new Position2D((float) 200.0, (float) 200.0);
    Position2D pT = new Position2D((float) 300.0, (float) 300.0);
    Position2D pAfter1 = new Position2D((float) 220.0, (float) 220.0);
    AbstractAnimation moveDo = new MoveAnimation(moveT, "R",
            10, 60, pF, pT);
    ColorInfo red1 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 1, 100, (float) 50.0, (float)100.0);
    AbstractShape rect1Change = new Rectangle("R",rectype,
            red1, pAfter1, 1, 100, (float) 50.0, (float)100.0);

    //Test of doAnimation method and check if it can return one changing shape
    // after did this animation - rectangle
    @Test
    public void testDoAnimationForRectangle() {
      assertEquals(rect1Change.getPosition().getX(),
              moveDo.doAnimation(rect1, 20).getPosition().getX(), 0.000001);
      assertEquals(rect1Change.getPosition().getY(),
              moveDo.doAnimation(rect1, 20).getPosition().getY(), 0.000001);
    }

    Position2D pF2 = new Position2D((float) 150.0, (float) 200.0);
    Position2D pT2 = new Position2D((float) 550.0, (float) 400.0);
    Position2D pAfter2 = new Position2D((float) 470.0, (float) 360.0);
    AbstractAnimation moveDo2 = new MoveAnimation(moveT, "R",
            10, 60, pF2, pT2);
    ColorInfo color0 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
    AbstractShape oval1 = new Oval("R",ovalType,
            blue1, p3, 6, 100, (float) 60.0,  (float) 30.0);
    AbstractShape oval1Change = new Oval("R",ovalType,
            blue1, pAfter2, 6, 100, (float) 60.0,  (float) 30.0);

    //Test of doAnimation method and check if it can return one changing shape
    // after did this animation - oval
    @Test
    public void testDoAnimationForOval() {
      assertEquals(oval1Change.getPosition().getX(),
              moveDo2.doAnimation(oval1, 50).getPosition().getX(), 0.000001);
      assertEquals(oval1Change.getPosition().getY(),
              moveDo2.doAnimation(oval1, 50).getPosition().getY(), 0.000001);
    }

  }

  /**
   * The subclass test of abstractAnimationTest for testing subclass:
   * changing color of shape animation.
   */
  public static final class ChangeColorTest extends AbstractAnimationTest {

    @Override
    protected AbstractAnimation createAnimation(String shapeName,int startTime, int endTime) {
      return new ChangeColorAnimation(colorChangeT, shapeName,
              startTime, endTime, color1, color2);
    }

    AbstractAnimation testColor1 = new ChangeColorAnimation(colorChangeT, "C",
            50, 80, blue1, green1);

    // different result of printing the string of move information
    @Test
    public void getStringForColorChange() throws Exception {
      assertEquals("Shape C changes color from (0.0,0.0,1.0) to"
              + " (0.0,1.0,0.0) from t=50 to t=80", testColor1.getString());
    }

    // Test the method of getAnimationType.
    @Test
    public void testGetAnimationType() throws Exception {
      assertEquals(colorChangeT, testColor1.getAnimationType());
    }

    //invalid given type of animation for move animation
    @Test (expected = IllegalArgumentException.class)
    public void illegalType() {
      AbstractAnimation testColor1 = new ChangeColorAnimation(moveT, "C",
              50, 80, blue1, green1);
      assertEquals("Shape C changes color from (0.0,0.0,1.0) to"
              + " (0.0,1.0,0.0) from t=50 to t=80", testColor1.getString());
    }

    ColorInfo color1 = new ColorInfo((float) 0.56,(float) 0.4,(float) 0.12213);
    ColorInfo color2 = new ColorInfo((float) 0.32,(float) 1.0,(float) 0.7);

    ChangeColorAnimation testColor2 = new ChangeColorAnimation(colorChangeT, "C",
            50, 80, color1, color2);

    // Test getMoveFrom method to get color change from
    @Test
    public void testGetMoveFrom() throws  Exception {
      assertEquals(testColor2.getChangeFrom().getRed(), 0.56, 0.000001);
      assertEquals(testColor2.getChangeFrom().getGreen(),0.4, 0.000001);
      assertEquals( testColor2.getChangeFrom().getBlue(), 0.12213, 0.000001);
    }

    // Test getMoveTo method to get color change to
    @Test
    public void testGetMoveTo() throws  Exception {
      assertEquals(testColor2.getChangeTo().getRed(), 0.32, 0.000001);
      assertEquals(testColor2.getChangeTo().getGreen(), 1.0, 0.000001);
      assertEquals(testColor2.getChangeTo().getBlue(), 0.7, 0.000001);
    }

    ColorInfo colorF = new ColorInfo((float)0.0, (float) 0.0,(float) 1.0);
    ColorInfo colorT = new ColorInfo((float)0.0, (float) 1.0,(float) 0.0);
    ColorInfo colorChange = new ColorInfo((float)0.0,
            (float) -1.333333373,(float) 2.33333325386);
    ChangeColorAnimation colorDo = new ChangeColorAnimation(colorChangeT, "C",
            50, 80, colorF, colorT);
    ColorInfo redX = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
    AbstractShape rect1 = new Rectangle("C",rectype,
            redX, p1, 1, 100, (float) 50.0, (float)100.0);
    AbstractShape rect1Change = new Rectangle("C",rectype,
            colorChange, p1, 1, 100, (float) 50.0, (float)100.0);

    //Test of doAnimation method and check if it can return one changing shape
    // after did this animation - rectangle
    @Test
    public void testDoAnimationForRectangle() {
      assertEquals(rect1Change.getColor().getRed(),
              colorDo.doAnimation(rect1, 10).getColor().getRed(), 0.000001);
      assertEquals(rect1Change.getColor().getGreen(),
              colorDo.doAnimation(rect1, 10).getColor().getGreen(), 0.000001);
      assertEquals(rect1Change.getColor().getBlue(),
              colorDo.doAnimation(rect1, 10).getColor().getBlue(), 0.000001);
    }


    ColorInfo colorF2 = new ColorInfo((float)0.45, (float) 0.0,(float) 0.80);
    ColorInfo colorT2 = new ColorInfo((float)0.0, (float) 0.5,(float) 0.5);
    ColorInfo colorChange2 = new ColorInfo((float) 0.225,
            (float) 0.25,(float) 0.65);
    ChangeColorAnimation colorDo2 = new ChangeColorAnimation(colorChangeT, "C",
            50, 80, colorF2, colorT2);
    ColorInfo red1 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
    AbstractShape oval1 = new Oval("C",ovalType,
            blue1, p3, 50, 200, (float) 60.0,  (float) 30.0);
    AbstractShape oval1Change = new Oval("C",ovalType,
            colorChange2, p3, 50, 200, (float) 60.0,  (float) 30.0);


    //Test of doAnimation method and check if it can return one changing shape
    // after did this animation - rectangle
    @Test
    public void testDoAnimationForOval() {
      assertEquals(oval1Change.getColor().getRed(),
              colorDo2.doAnimation(oval1, 65).getColor().getRed(), 0.000001);
      assertEquals(oval1Change.getColor().getGreen(),
              colorDo2.doAnimation(oval1, 65).getColor().getGreen(), 0.000001);
      assertEquals(oval1Change.getColor().getBlue(),
              colorDo2.doAnimation(oval1, 65).getColor().getBlue(), 0.000001);
    }

  }

  /**
   * The subclass test of abstractAnimationTest for test subclass:
   * changing scale of shape animation.
   */
  public static final class ChangeScaleTest extends AbstractAnimationTest {

    @Override
    protected AbstractAnimation createAnimation(String shapeName, int startTime, int endTime) {
      ArrayList<Float> listValue1 = new ArrayList<>();
      ArrayList<Float> listValue2 = new ArrayList<>();
      listValue1.add((float) 30.0);
      listValue1.add((float) 45.5);
      listValue2.add((float) 78.0);
      listValue2.add((float) 34.0);
      // because we have search the name of shape in list of shapes in the model,
      //individual name cannot get type, just default it to rectangle
      ShapeType shapeType = ShapeType.RECTANGLE;
      return new ChangeScaleAnimation(scaleChangeT, shapeName, shapeType,
              startTime, endTime, listValue1, listValue2);
    }

    // different result of printing the string of move information
    @Test
    public void getStringForScaleChange() throws Exception {
      // check for changing scale of rectangle
      ArrayList<Float> valueChange1 = new ArrayList();
      ArrayList<Float> valueChangeTo1 = new ArrayList();
      valueChange1.add((float) 50.0);
      valueChange1.add((float) 100.0);
      valueChangeTo1.add((float) 25.0);
      valueChangeTo1.add((float) 100.0);
      AbstractAnimation testScale1 = new ChangeScaleAnimation(scaleChangeT,
              "R", rectype, 51, 70,
              valueChange1, valueChangeTo1);
      assertEquals("Shape R scales from Width: 50.0, Height: "
              + "100.0 to Width: 25.0,"
              + " Height: 100.0 from t=51 to t=70", testScale1.getString());
      // check for changing scale of oval
      ArrayList<Float> valueChange2 = new ArrayList();
      ArrayList<Float> valueChangeTo2 = new ArrayList();
      valueChange2.add((float) 20.0);
      valueChange2.add((float) 10.0);
      valueChangeTo2.add((float) 30.0);
      valueChangeTo2.add((float) 40.0);
      AbstractAnimation testScale2 = new ChangeScaleAnimation(scaleChangeT,
              "O", ovalType, 20, 30,
              valueChange2, valueChangeTo2);
      assertEquals("Shape O scales from X radius: 20.0, Y radius: "
              + "10.0 to X radius: 30.0,"
              + " Y radius: 40.0 from t=20 to t=30", testScale2.getString());

    }

    // Test the method of getAnimationType.
    @Test
    public void testGetAnimationType() throws Exception {
      ArrayList<Float> valueChange1 = new ArrayList();
      ArrayList<Float> valueChangeTo1 = new ArrayList();
      valueChange1.add((float) 50.0);
      valueChange1.add((float) 100.0);
      valueChangeTo1.add((float) 25.0);
      valueChangeTo1.add((float) 100.0);
      AbstractAnimation testScale1 = new ChangeScaleAnimation(scaleChangeT,
              "R", rectype, 51, 70,
              valueChange1, valueChangeTo1);
      assertEquals(scaleChangeT, testScale1.getAnimationType());
    }

    //invalid given type of animation for move animation
    @Test (expected = IllegalArgumentException.class)
    public void illegalType() {
      ArrayList<Float> valueChange1 = new ArrayList();
      ArrayList<Float> valueChangeTo1 = new ArrayList();
      valueChange1.add((float) 50.0);
      valueChange1.add((float) 100.0);
      valueChangeTo1.add((float) 25.0);
      valueChangeTo1.add((float) 100.0);
      AbstractAnimation testScale1 = new ChangeScaleAnimation(moveT,
              "R", rectype, 51, 70,
              valueChange1, valueChangeTo1);
      assertEquals("Shape R scales from Width: 50.0, Height: "
              + "100.0 to Width: 25.0,"
              + " Height: 100.0 from t=51 to t=70", testScale1.getString());
    }

    // Test getValueChangeFrom and getValueChangeTo method to get scale change from
    // and scale change to
    @Test
    public void testGetMoveFromAndTo() throws  Exception {
      ArrayList<Float> valueChange1 = new ArrayList();
      ArrayList<Float> valueChangeTo1 = new ArrayList();
      valueChange1.add((float) 50.0);
      valueChange1.add((float) 100.0);
      valueChangeTo1.add((float) 25.0);
      valueChangeTo1.add((float) 80.0);
      ChangeScaleAnimation testScale1 = new ChangeScaleAnimation(scaleChangeT,
              "R", rectype, 51, 70,
              valueChange1, valueChangeTo1);
      assertEquals(testScale1.getValueChangeFrom().get(0), 50.0, 0.000001);
      assertEquals(testScale1.getValueChangeFrom().get(1), 100.0, 0.000001);
      assertEquals(testScale1.getValueChangeTo().get(0), 25.0, 0.000001);
      assertEquals(testScale1.getValueChangeTo().get(1),  80.0, 0.000001);
    }

    //Test for getting shape type of the shape that we want to do animation
    @Test
    public void testGetShapeType() {
      ArrayList<Float> valueChange1 = new ArrayList();
      ArrayList<Float> valueChangeTo1 = new ArrayList();
      valueChange1.add((float) 50.0);
      valueChange1.add((float) 100.0);
      valueChangeTo1.add((float) 25.0);
      valueChangeTo1.add((float) 80.0);
      ChangeScaleAnimation testScale1 = new ChangeScaleAnimation(scaleChangeT,
              "R", rectype, 51, 70,
              valueChange1, valueChangeTo1);
      assertEquals(rectype, testScale1.getChangeShapeType());
    }

    // Test of doAnimation method and check if it can return one changing shape
    // after did this animation - rectangle
    @Test
    public void testDoAnimationForRectangle() {
      ArrayList<Float> valueChange1 = new ArrayList();
      ArrayList<Float> valueChangeTo1 = new ArrayList();
      valueChange1.add((float) 70.0);
      valueChange1.add((float) 60.0);
      valueChangeTo1.add((float) 45.0);
      valueChangeTo1.add((float) 30.0);
      ChangeScaleAnimation scaleDo2 = new ChangeScaleAnimation(scaleChangeT,
              "C", rectype, 32, 60,
              valueChange1, valueChangeTo1);
      AbstractShape rect1 = new Rectangle("C",rectype,
              green1, p1, 1, 100, (float) 50.0, (float)100.0);
      AbstractShape rect1Change = new Rectangle("C",rectype,
              green1, p1, 1, 100, (float) 71.78571319580078,
              (float) 62.14285659790039);
      assertEquals(rect1Change.getWidth(),
              scaleDo2.doAnimation(rect1, 30).getWidth(), 0.000001);
      assertEquals(rect1Change.getHeight(),
              scaleDo2.doAnimation(rect1, 30).getHeight(), 0.000001);
    }


    // Test of doAnimation method and check if it can return one changing shape
    // after did this animation - rectangle
    @Test
    public void testDoAnimationForOval() {
      ArrayList<Float> valueChange1 = new ArrayList();
      ArrayList<Float> valueChangeTo1 = new ArrayList();
      valueChange1.add((float) 50.0);
      valueChange1.add((float) 100.0);
      valueChangeTo1.add((float) 25.0);
      valueChangeTo1.add((float) 80.0);
      ChangeScaleAnimation scaleDo2 = new ChangeScaleAnimation(scaleChangeT,
              "C", rectype, 32, 60,
              valueChange1, valueChangeTo1);

      AbstractShape oval1 = new Oval("C",ovalType,
              blue1, p3, 20, 100, (float) 60.0,  (float) 30.0);
      AbstractShape oval1Change = new Oval("C",ovalType,
              blue1, p3, 20, 100, (float) 51.78571319580078,
              (float) 101.4285659790039);
      assertEquals(oval1Change.getWidth(),
              scaleDo2.doAnimation(oval1, 30).getWidth(), 0.000001);
      assertEquals(oval1Change.getHeight(),
              scaleDo2.doAnimation(oval1, 30).getHeight(), 0.000001);
    }

  }


  AbstractAnimation any = createAnimation("Any", 10, 50);
  AbstractAnimation any2 = createAnimation("Any2", 60, 100);

  // Test the method of getShapeName.
  @Test
  public void testGetShapeName() throws Exception {
    assertEquals("Any", any.getShapeName());
    assertEquals("Any2", any2.getShapeName());
  }

  // Test the method of getStartTime.
  @Test
  public void tesGetStartTime() throws Exception {
    assertEquals(10, any.getStartTime());
    assertEquals(60, any2.getStartTime());
  }

  // Test the method of getEndTime.
  @Test
  public void tesGetEndTime() throws Exception {
    assertEquals(50, any.getEndTime());
    assertEquals(100, any2.getEndTime());
  }

  // Test the illegal shape class and its time is invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalAnimationTime() {

    createAnimation("R", 21, -20);
  }

  // Test the illegal shape class and its time is invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalAnimationTime2() {

    createAnimation("R", -12, 45);
  }

  // Test the illegal shape class and its time is invalid.
  @Test (expected = IllegalArgumentException.class)
  public void illegalAnimationTime3() {

    createAnimation("R", 21, 5);
  }

}