package cs3500.animator.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Test the read only mode only works for getting methods.
 */
public class ReadOnlyModelTest {
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
  AbstractAnimation colorCh1 = new ChangeColorAnimation(colorChangeT, "C",
          50, 80, blue1, green1);
  AbstractShape rect1 = new Rectangle("R",rectype,
          red1, p1, 1, 100, (float) 50.0, (float)100.0);
  AbstractShape oval1 = new Oval("C",ovalType,
          blue1, p3, 6, 100, (float) 60.0,  (float) 30.0);
  AbstractShape rect2 = new Rectangle("R2",rectype,
          red1, p1, 1, 100, (float) 50.0, (float) 100.0);
  AbstractShape oval2 = new Oval("K2",ovalType,
          blue1, p2, 10, 20, (float) 2.8, (float) 9.05);
  AbstractShape rect3 = new Rectangle("R1",rectype,
          red1, p1, 1, 100, (float) 50.0, (float) 100.0);
  AbstractShape oval3 = new Oval("K2",ovalType,
          blue1, p2, 10, 20, (float) 2.8, (float) 9.05);
  ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
  HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
  ArrayList<Integer> record = new ArrayList<>();
  BasicModel model1 = new BasicModel(listOfShapes, mapAnimations1, record);
  ReadOnlyModel model = new ReadOnlyModel(model1);


  // Test getShapesInfor method for getting shapes information in model.
  @Test
  public void testGetShapesInfor() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model1 = new BasicModel(listOfShapes, mapAnimations1, record);
    ReadOnlyModel model = new ReadOnlyModel(model1);
    //this is one empty model
    model1.addShape(rect1);
    model1.addShape(oval1);
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
    BasicModel model1 = new BasicModel(listOfShapes, mapAnimations1, record);
    ArrayList<AbstractAnimation> list1 = new ArrayList<>();
    ArrayList<AbstractAnimation> list2 = new ArrayList<>();
    model1.addShape(rect1);
    model1.addShape(oval1);
    model1.addAnimation(moveCh1);
    model1.addAnimation(moveCh2);
    HashMap<Integer, ArrayList<AbstractAnimation>> check = new HashMap<>();
    list1.add(moveCh1);
    list2.add(moveCh2);
    check.put(10, list1);
    check.put(20, list2);
    ReadOnlyModel model = new ReadOnlyModel(model1);
    assertEquals(check, model.getAnimationInfor());
  }

  // Test getTimeUseInfor method for getting time use information in model.
  @Test
  public void testGetTimeUseInfor() throws Exception {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
    ArrayList<Integer> record = new ArrayList<>();
    BasicModel model1 = new BasicModel(listOfShapes, mapAnimations1, record);
    ArrayList<AbstractAnimation> list1 = new ArrayList<>();
    ArrayList<AbstractAnimation> list2 = new ArrayList<>();
    model1.addShape(rect1);
    model1.addShape(oval1);
    model1.addAnimation(moveCh1);
    model1.addAnimation(moveCh2);
    list1.add(moveCh1);
    list2.add(moveCh2);
    ArrayList<Integer> check = new ArrayList<>();
    check.add(10);
    check.add(20);
    ReadOnlyModel model = new ReadOnlyModel(model1);
    assertEquals(check.get(0), model.getTimeUseInfor().get(0));
    assertEquals(check.get(1), model.getTimeUseInfor().get(1));
  }

  //Test addShape method does not work for readOnlyModel
  @Test (expected = UnsupportedOperationException.class)
  public void testAddShape() throws Exception {
    model.addShape(rect1);
  }

  //Test deleteShape method does not work for readOnlyModel
  @Test (expected = UnsupportedOperationException.class)
  public void testDeleteShape() throws Exception {
    model.deleteShape("R");
  }

  //Test addAnimation method does not work for readOnlyModel
  @Test (expected = UnsupportedOperationException.class)
  public void testAddAnimation() throws Exception {
    model.addAnimation(moveCh1);
  }

}