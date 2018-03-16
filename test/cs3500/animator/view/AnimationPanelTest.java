//package cs3500.animator.view;
//
//import org.junit.Test;
//
//import java.awt.event.ActionEvent;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import cs3500.animator.model.AbstractAnimation;
//import cs3500.animator.model.AbstractShape;
//import cs3500.animator.model.AnimationType;
//import cs3500.animator.model.BasicModel;
//import cs3500.animator.model.ChangeColorAnimation;
//import cs3500.animator.model.ChangeScaleAnimation;
//import cs3500.animator.model.ColorInfo;
//import cs3500.animator.model.MoveAnimation;
//import cs3500.animator.model.Oval;
//import cs3500.animator.model.Position2D;
//import cs3500.animator.model.ReadOnlyModel;
//import cs3500.animator.model.Rectangle;
//import cs3500.animator.model.ShapeType;
//
//import static org.junit.Assert.assertEquals;
///**
// * Tests the AnimationPanel class.
// */
//public class AnimationPanelTest {
//  Position2D p1 = new Position2D((float) 200.0, (float) 200.0);
//  Position2D p2 = new Position2D((float) 300.0, (float) 300.0);
//  Position2D p3 = new Position2D((float) 500.0, (float) 100.0);
//  Position2D p4 = new Position2D((float) 500.0, (float) 400.0);
//  Position2D p5 = new Position2D((float) 50.0, (float) 700.0);
//  Position2D p6 = new Position2D((float) 70.0, (float) 400.0);
//  ShapeType rectype = ShapeType.RECTANGLE;
//  ShapeType ovalType = ShapeType.OVAL;
//  ColorInfo blue1 = new ColorInfo((float)0.0, (float) 0.0,(float) 1.0);
//  ColorInfo red1 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
//  ColorInfo green1 = new ColorInfo((float)0.0, (float) 1.0,(float) 0.0);
//  ColorInfo color1 = new ColorInfo((float)0.78, (float) 0.6,(float) 0.8);
//  AnimationType moveT = AnimationType.MOVE;
//  AnimationType colorChangeT = AnimationType.COLORCHANGE;
//  AnimationType scaleChangeT = AnimationType.SCALECHANGE;
//  AbstractAnimation moveCh1 = new MoveAnimation(moveT, "R",
//          10, 50, p1, p2);
//  AbstractAnimation moveCh2 = new MoveAnimation(moveT, "C",
//          20, 70, p3, p4);
//  AbstractAnimation colorCh1 = new ChangeColorAnimation(colorChangeT, "C",
//          50, 80, blue1, green1);
//  AbstractShape rect1 = new Rectangle("R",rectype,
//          red1, p1, 1, 100, (float) 50.0, (float)100.0);
//  AbstractShape oval1 = new Oval("C",ovalType,
//          blue1, p3, 6, 100, (float) 60.0,  (float) 30.0);
//  AbstractShape rect2 = new Rectangle("R2",rectype,
//          red1, p1, 1, 100, (float) 50.0, (float) 100.0);
//  AbstractShape oval2 = new Oval("K2",ovalType,
//          blue1, p2, 10, 20, (float) 2.8, (float) 9.05);
//  AbstractShape rect3 = new Rectangle("R1",rectype,
//          red1, p1, 1, 100, (float) 50.0, (float) 100.0);
//  AbstractShape oval3 = new Oval("K2",ovalType,
//          blue1, p2, 10, 20, (float) 2.8, (float) 9.05);
//  BasicModel model;
//  AnimationPanel a;
//
//
//  /**
//   * Initializes environmental variables for each test.
//   */
//  void init() {
//    AbstractShape rect1 = new Rectangle("R", rectype,
//            red1, p1, 1, 100, (float) 50.0, (float) 100.0);
//    AbstractShape rect2 = new Rectangle("R2", rectype,
//            red1, p1, 1, 100, (float) 50.0, (float) 100.0);
//    AbstractShape oval1 = new Oval("C",ovalType,
//            blue1, p3, 6, 100, (float) 60.0,  (float) 30.0);
//    ArrayList<Float> valueChange1 = new ArrayList();
//    ArrayList<Float> valueChangeTo1 = new ArrayList();
//    valueChange1.add((float) 50.0);
//    valueChange1.add((float) 100.0);
//    valueChangeTo1.add((float) 25.0);
//    valueChangeTo1.add((float) 100.0);
//    AbstractAnimation moveCh1 = new MoveAnimation(moveT, "R",
//            10, 50, p1, p2);
//    AbstractAnimation move1ChSpecial = new MoveAnimation(moveT, "R2",
//            10, 50, p1, p2);
//    AbstractAnimation move1ChSpecial2 = new MoveAnimation(moveT, "R2",
//            51, 62, p1, p2);
//    AbstractAnimation scaleCh1 = new ChangeScaleAnimation(scaleChangeT,
//            "R2", rectype, 30, 70, valueChange1, valueChangeTo1);
//    AbstractAnimation moveCh2 = new MoveAnimation(moveT, "C",
//            20, 70, p3, p4);
//    AbstractAnimation colorCh1 = new ChangeColorAnimation(colorChangeT, "C",
//            50, 80, blue1, green1);
//    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
//    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
//    ArrayList<Integer> record = new ArrayList<>();
//    model = new BasicModel(listOfShapes, mapAnimations1, record);
//    model.addShape(rect1);
//    model.addShape(rect2);
//    model.addShape(oval1);
//    model.addAnimation(moveCh1);
//    // if this start time exist the same one, just animations's shape name is different
//    // we can add it
//    model.addAnimation(move1ChSpecial);
//    // add different type of animations for same shape R2: changeScale one,
//    // overlap animation time
//    model.addAnimation(scaleCh1);
//    // add different type of animations: changeColor one
//    model.addAnimation(colorCh1);
//    // add same name, overlapping time interval, but different animation type
//    model.addAnimation(moveCh2);
//    // add close time interval animation for one existing animation that has
//    // same type of animation and same shape name
//    model.addAnimation(move1ChSpecial2);
//    a = new AnimationPanel(new ReadOnlyModel(model),1);
//
//  }
//
//  /**
//   * tests the Getter methods for AnimationPanel by making they get the
//   * right value field values.
//   */
//  @Test
//  public void testGetters() {
//    init();
//    ArrayList<AbstractShape> test = new ArrayList<AbstractShape>();
//    test.add(rect1);
//    test.add(rect2);
//    test.add(oval1);
//    assertEquals(true, 1 == a.getSpeed());
//    assertEquals(true, a.getNowTick() == -1);
//    for (int i = 0; i < 3; i += 1) {
//      assertEquals(true, test.get(i).getName().equals(a.getNowShapes().get(i).getName()));
//      assertEquals(true, test.get(i).getAppear()
//              == (a.getNowShapes().get(i).getAppear()));
//      assertEquals(true, test.get(i).getDisappear()
//              == (a.getNowShapes().get(i).getDisappear()));
//      assertEquals(true, test.get(i).getColor().equals(
//              a.getNowShapes().get(i).getColor()));
//      assertEquals(true, test.get(i).getName().equals(
//              a.getNowShapes().get(i).getName()));
//      assertEquals(true, test.get(i).getHeight()
//              == a.getNowShapes().get(i).getHeight());
//      assertEquals(true, test.get(i).getWidth()
//              == a.getNowShapes().get(i).getWidth());
//      assertEquals(true, test.get(i).getType()
//              == a.getNowShapes().get(i).getType());
//    }
//  }
//
//  /**
//   * Tests the setSpeed method for AnimationPanel by making sure it sets the right value.
//   */
//  @Test
//  public void testSetSpeed() {
//    init();
//    a.setSpeed(3.0);
//    assertEquals(true, 3.0 == a.getSpeed());
//  }
//
//  /**
//   * Tests the startAnimation method by making sure the animation is started.
//   */
//  @Test
//  public void testStartAnimation() {
//    init();
//    ActionEvent event = new ActionEvent("hi", 1, "Select All");
//    a.startAnimation();
//    a.actionPerformed(event);
//    assertEquals(0,a.getNowTick());
//  }
//
//  /**
//   * Tests the action listener within the Animation Panel class by making sure, it
//   * properly renders shapes at the correct time.
//   */
//  @Test
//  public void testActionListner() {
//    init();
//    ActionEvent event = new ActionEvent("hi", 1, "Select All");
//    a.startAnimation();
//    a.actionPerformed(event);
//    assertEquals(0,a.getNowTick());
//    a.setSpeed(50000);
//    a.actionPerformed(event);
//    a.actionPerformed(event);
//    a.actionPerformed(event);
//    assertEquals(3,a.getNowTick());
//  }
//}
