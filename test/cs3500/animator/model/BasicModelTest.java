package cs3500.animator.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Test for the model class and its public methods.
 */
public class BasicModelTest {
  Position2D p1 = new Position2D((float) 200.0, (float) 200.0);
  Position2D p2 = new Position2D((float) 300.0, (float) 300.0);
  Position2D p3 = new Position2D((float) 500.0, (float) 100.0);
  Position2D p4 = new Position2D((float) 500.0, (float) 400.0);
  ShapeType rectype = ShapeType.RECTANGLE;
  ShapeType ovalType = ShapeType.OVAL;
  ColorInfo blue1 = new ColorInfo((float)0.0, (float) 0.0,(float) 1.0);
  ColorInfo red1 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
  ColorInfo green1 = new ColorInfo((float)0.0, (float) 1.0,(float) 0.0);
  AnimationType moveT = AnimationType.MOVE;
  AnimationType colorChangeT = AnimationType.COLORCHANGE;
  AnimationType scaleChangeT = AnimationType.SCALECHANGE;
  AbstractAnimation moveCh1 = new MoveAnimation(moveT, "R",
          10, 50, p1, p2);
  AbstractAnimation moveCh2 = new MoveAnimation(moveT, "C",
          20, 70, p3, p4);
  AbstractShape rect1 = new Rectangle("R",rectype,
          red1, p1, 1, 100, (float) 50.0, (float)100.0);
  AbstractShape oval1 = new Oval("C",ovalType,
          blue1, p3, 6, 100, (float) 60.0,  (float) 30.0);
  AbstractShape rect2 = new Rectangle("R2",rectype,
          red1, p1, 1, 100, (float) 50.0, (float) 100.0);
  AbstractShape oval2 = new Oval("K2",ovalType,
          blue1, p2, 10, 20, (float) 2.8, (float) 9.05);
  AbstractShape oval3 = new Oval("K2",ovalType,
          blue1, p2, 10, 20, (float) 2.8, (float) 9.05);


  // Test getShapesInfor method for getting shapes information in model.
  @Test
  public void testGetShapesInfor() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model = new BasicModel(listOfShapes, mapAnimations1, record);
    //this is one empty model
    model.addShape(rect1);
    model.addShape(oval1);
    ArrayList<AbstractShape> check = new ArrayList<>();
    check.add(rect1);
    check.add(oval1);
    assertEquals(check, model.getShapesInfor());
  }

  // Test getAnimationInfor method for getting animations information in model
  @Test
  public void testGetAnimationsInfor() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model = new BasicModel(listOfShapes, mapAnimations1, record);
    ArrayList<AbstractAnimation> list1 = new ArrayList<>();
    ArrayList<AbstractAnimation> list2 = new ArrayList<>();
    model.addShape(rect1);
    model.addShape(oval1);
    model.addAnimation(moveCh1);
    model.addAnimation(moveCh2);
    HashMap<Integer, ArrayList<AbstractAnimation>> check = new HashMap<>();
    list1.add(moveCh1);
    list2.add(moveCh2);
    check.put(10, list1);
    check.put(20, list2);
    assertEquals(check, model.getAnimationInfor());
  }

  // Test getTimeUseInfor method for getting time use information in model.
  @Test
  public void testGetTimeUseInfor() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model = new BasicModel(listOfShapes, mapAnimations1, record);
    ArrayList<AbstractAnimation> list1 = new ArrayList<>();
    ArrayList<AbstractAnimation> list2 = new ArrayList<>();
    model.addShape(rect1);
    model.addShape(oval1);
    model.addAnimation(moveCh1);
    model.addAnimation(moveCh2);
    list1.add(moveCh1);
    list2.add(moveCh2);
    ArrayList<Integer> check = new ArrayList<>();
    check.add(10);
    check.add(20);
    assertEquals(check.get(0), model.getTimeUseInfor().get(0));
    assertEquals(check.get(1), model.getTimeUseInfor().get(1));
  }

  // Test addShape method for adding one shape into the model.
  @Test
  public void testAddShape() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model2 = new BasicModel(listOfShapes, mapAnimations1, record);
    model2.addShape(rect2);
    model2.addShape(oval2);
    assertEquals(rect2, model2.getShapesInfor().get(0));
    assertEquals(oval2, model2.getShapesInfor().get(1));
  }

  // Test invalid parameters for addShape method to add one shape into the model.
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddShape() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model2 = new BasicModel(listOfShapes, mapAnimations1, record);
    model2.addShape(oval3);
    //repeating shape name
    model2.addShape(oval2);
  }

  // Test deleteShape method for deleting one shape in the model.
  @Test
  public void testDeleteShape() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model2 = new BasicModel(listOfShapes, mapAnimations1, record);
    model2.addShape(oval3);
    assertEquals(oval3, model2.getShapesInfor().get(0));
    model2.deleteShape("K2");
    assertEquals(0, model2.getShapesInfor().size());
  }

  // Test invalid parameters for deleteShape methods to delete one shape in the model.
  @Test (expected = IllegalArgumentException.class)
  public void invalidDeleteShape() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model2 = new BasicModel(listOfShapes, mapAnimations1, record);
    model2.addShape(rect2);
    //cannot find the shape name
    model2.deleteShape("What");
  }

  // Test addAnimation method for adding one animation in the model.
  @Test
  public void addAnimation() throws Exception {
    AbstractShape rect1 = new Rectangle("R", rectype,
            red1, p1, 1, 100, (float) 50.0, (float) 100.0);
    AbstractShape rect2 = new Rectangle("R2", rectype,
            red1, p1, 1, 100, (float) 50.0, (float) 100.0);
    AbstractShape oval1 = new Oval("C",ovalType,
            blue1, p3, 6, 100, (float) 60.0,  (float) 30.0);
    ArrayList<Float> valueChange1 = new ArrayList();
    ArrayList<Float> valueChangeTo1 = new ArrayList();
    valueChange1.add((float) 50.0);
    valueChange1.add((float) 100.0);
    valueChangeTo1.add((float) 25.0);
    valueChangeTo1.add((float) 100.0);
    AbstractAnimation moveCh1 = new MoveAnimation(moveT, "R",
            10, 50, p1, p2);
    AbstractAnimation move1ChSpecial = new MoveAnimation(moveT, "R2",
            10, 50, p1, p2);
    AbstractAnimation move1ChSpecial2 = new MoveAnimation(moveT, "R2",
            51, 62, p1, p2);
    AbstractAnimation scaleCh1 = new ChangeScaleAnimation(scaleChangeT,
            "R2", rectype, 30, 70, valueChange1, valueChangeTo1);
    AbstractAnimation moveCh2 = new MoveAnimation(moveT, "C",
            20, 70, p3, p4);
    AbstractAnimation colorCh1 = new ChangeColorAnimation(colorChangeT, "C",
            50, 80, blue1, green1);
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model = new BasicModel(listOfShapes, mapAnimations1, record);
    model.addShape(rect1);
    model.addShape(rect2);
    model.addShape(oval1);
    model.addAnimation(moveCh1);
    // if this start time exist the same one, just animations's shape name is different
    // we can add it
    model.addAnimation(move1ChSpecial);
    // add different type of animations for same shape R2: changeScale one,
    // overlap animation time
    model.addAnimation(scaleCh1);
    // add different type of animations: changeColor one
    model.addAnimation(colorCh1);
    // add same name, overlapping time interval, but different animation type
    model.addAnimation(moveCh2);
    // add close time interval animation for one existing animation that has
    // same type of animation and same shape name
    model.addAnimation(move1ChSpecial2);
    ArrayList<AbstractShape> test1 = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> test2 = new HashMap<>();
    test1.add(rect1);
    test1.add(rect2);
    test1.add(oval1);
    ArrayList<AbstractAnimation> abAnimation1 = new ArrayList<AbstractAnimation>();
    ArrayList<AbstractAnimation> abAnimation2 = new ArrayList<AbstractAnimation>();
    ArrayList<AbstractAnimation> abAnimation3 = new ArrayList<AbstractAnimation>();
    ArrayList<AbstractAnimation> abAnimation4 = new ArrayList<AbstractAnimation>();
    ArrayList<AbstractAnimation> abAnimation5 = new ArrayList<AbstractAnimation>();
    abAnimation1.add(moveCh1);
    abAnimation1.add(move1ChSpecial);
    abAnimation2.add(scaleCh1);
    abAnimation3.add(colorCh1);
    abAnimation4.add(moveCh2);
    abAnimation5.add(move1ChSpecial2);
    test2.put(10, abAnimation1);
    test2.put(30, abAnimation2);
    test2.put(50, abAnimation3);
    test2.put(20, abAnimation4);
    test2.put(51, abAnimation5);
    assertEquals(test1, model.getShapesInfor());
    assertEquals(test2, model.getAnimationInfor());
  }

  // Test addAnimation for adding one animation in the model, cannot find that shape
  // to do animation
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimation() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model2 = new BasicModel(listOfShapes, mapAnimations1, record);
    model2.addShape(rect1);
    model2.addShape(rect2);
    //cannot find the same shape name to fit the animation
    model2.addAnimation(moveCh2);
  }

  // Test addAnimation for adding one animation in the model, start time of animation
  // overstep the appearing time of the shape it wants to animated
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimationTime() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model3 = new BasicModel(listOfShapes, mapAnimations, record);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 10, 100, (float) 50.0, (float) 100.0);
    AbstractAnimation moveCh = new MoveAnimation(moveT, "R",
            9, 50, p1, p2);
    model3.addShape(rect1);
    model3.addAnimation(moveCh);
  }

  // Test addAnimation for adding one animation in the model, end time of animation
  // overstep the disappearing time of the shape it wants to animated
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimationTime2() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model3 = new BasicModel(listOfShapes, mapAnimations, record);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 10, 100, (float) 50.0, (float) 100.0);
    AbstractAnimation moveCh = new MoveAnimation(moveT, "R",
            11, 101, p1, p2);
    model3.addShape(rect1);
    model3.addAnimation(moveCh);
  }

  // Test addAnimation for adding one animation in the model, start and end time of animation
  // overstep the appearing and disappearing time of the shape it wants to animated
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimationTime3() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model3 = new BasicModel(listOfShapes, mapAnimations, record);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 10, 100, (float) 50.0, (float) 100.0);
    AbstractAnimation moveCh = new MoveAnimation(moveT, "R",
            2, 200, p1, p2);
    model3.addShape(rect1);
    model3.addAnimation(moveCh);
  }

  // Test addAnimation for adding one animation in the model, time interval of animation
  // overlap the time interval of another existing animation in model for the same shape name and
  // same animation type: endTime overlap
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimationOverlap() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model3 = new BasicModel(listOfShapes, mapAnimations, record);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 10, 100, (float) 50.0, (float) 100.0);
    AbstractAnimation moveCh = new MoveAnimation(moveT, "R",
            10, 20, p1, p2);
    AbstractAnimation moveCh2 = new MoveAnimation(moveT, "R",
            18, 30, p2, p1);
    model3.addShape(rect1);
    model3.addAnimation(moveCh);
    model3.addAnimation(moveCh2);
  }

  // Test addAnimation for adding one animation in the model, time interval of animation
  // overlap the another animation time interval for the same shape name and
  // same animation type: startTime overlap
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimationOverlap2() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model3 = new BasicModel(listOfShapes, mapAnimations, record);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 10, 100, (float) 50.0, (float) 100.0);
    AbstractAnimation moveCh = new MoveAnimation(moveT, "R",
            40, 70, p1, p2);
    AbstractAnimation moveCh2 = new MoveAnimation(moveT, "R",
            20, 40, p2, p1);
    model3.addShape(rect1);
    model3.addAnimation(moveCh);
    model3.addAnimation(moveCh2);
  }

  // Test addAnimation for adding one animation in the model, time interval of animation
  // overlap the another animation time interval for the same shape name and
  // same animation type: startTime overlap for changing scale
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimationOverlapChangeColor() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model3 = new BasicModel(listOfShapes, mapAnimations, record);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 10, 100, (float) 50.0, (float) 100.0);
    ArrayList<Float> valueChange1 = new ArrayList();
    ArrayList<Float> valueChangeTo1 = new ArrayList();
    valueChange1.add((float) 50.0);
    valueChange1.add((float) 100.0);
    valueChangeTo1.add((float) 25.0);
    valueChangeTo1.add((float) 100.0);
    AbstractAnimation scaleCh = new ChangeScaleAnimation(scaleChangeT, "R",
            rectype, 40, 70, valueChange1, valueChangeTo1);
    AbstractAnimation scaleCh2 = new ChangeScaleAnimation(scaleChangeT, "R",
            rectype, 20, 40, valueChangeTo1, valueChange1);
    model3.addShape(rect1);
    model3.addAnimation(scaleCh);
    model3.addAnimation(scaleCh2);
  }

  // Test addAnimation for adding one animation in the model, time interval of animation
  // overlap the another animation time interval for the same shape name and
  // same animation type: startTime overlap for changing color
  @Test (expected = IllegalArgumentException.class)
  public void invalidAddAnimationOverlapChangeScale() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model3 = new BasicModel(listOfShapes, mapAnimations, record);
    AbstractShape rect1 = new Rectangle("R",rectype,
            red1, p1, 10, 100, (float) 50.0, (float) 100.0);
    AbstractAnimation scaleCh = new ChangeColorAnimation(colorChangeT, "R",
            40, 70, red1, blue1);
    AbstractAnimation scaleCh2 = new ChangeColorAnimation(colorChangeT, "R",
            20, 40, blue1, red1);
    model3.addShape(rect1);
    model3.addAnimation(scaleCh);
    model3.addAnimation(scaleCh2);
  }

}