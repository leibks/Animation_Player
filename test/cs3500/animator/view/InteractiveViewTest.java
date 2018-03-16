//package cs3500.animator.view;
//
//import org.junit.Test;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ItemEvent;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.concurrent.TimeUnit;
//
//import javax.swing.JCheckBox;
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
//
//
///**
// * Tests the interactiveView class and its methods that control the animation panel.
// */
//public class InteractiveViewTest {
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
//  ActionEvent hi;
//  ViewInteractive interactive;
//
//
//  /**
//   * Initializes environmental variables for each test.
//   */
//  void init() {
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
//    hi = new ActionEvent("hi",1,"Select All");
//    interactive = new InteractiveView(new ReadOnlyModel(model), 1);
//  }
//
//
//  /**
//   * Tests the startAnimation method by making sure the state of InteractiveView has been
//   * started.
//   */
//  @Test
//  public void testStartAnimation() {
//    init();
//    InteractiveView inter = ((InteractiveView)interactive);
//    inter.startAnimation();
//    try {
//      TimeUnit.SECONDS.sleep(2);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    assertEquals(0,inter.getNowTick());
//  }
//
//  /**
//   * Tests all the getter methods in interactive view by making sure they return the right
//   * value.
//   */
//  @Test
//  public void testGetters() {
//    init();
//    InteractiveView inter = ((InteractiveView)interactive);
//    assertEquals(true, 1 == inter.getSpeed());
//    assertEquals(false, inter.getStop());
//    assertEquals(100,inter.getEndTime());
//    assertEquals("Interactive View",inter.getViewType());
//    assertEquals(-1,inter.getNowTick());
//    assertEquals(false,inter.getLoopSet());
//    SVGView svg = new SVGView(new ReadOnlyModel(model),1);
//    assertEquals(svg.getOutput().toString(),inter.getOutput().toString());
//    ArrayList<AbstractShape> test = new ArrayList<AbstractShape>();
//    test.add(rect1);
//    test.add(rect2);
//    test.add(oval1);
//    for (int i = 0; i < 3; i += 1) {
//      assertEquals(true,
//              test.get(i).getName().equals(inter.getNowShapes().get(i).getName()));
//      assertEquals(true, test.get(i).getAppear()
//              == (inter.getNowShapes().get(i).getAppear()));
//      assertEquals(true, test.get(i).getDisappear()
//              == (inter.getNowShapes().get(i).getDisappear()));
//      assertEquals(true, test.get(i).getColor().equals(
//              inter.getNowShapes().get(i).getColor()));
//      assertEquals(true, test.get(i).getName().equals(
//              inter.getNowShapes().get(i).getName()));
//      assertEquals(true, test.get(i).getHeight() ==
//              inter.getNowShapes().get(i).getHeight());
//      assertEquals(true, test.get(i).getWidth() ==
//              inter.getNowShapes().get(i).getWidth());
//      assertEquals(true, test.get(i).getType() ==
//              inter.getNowShapes().get(i).getType());
//    }
//  }
//
//  /**
//   * Tests the restart method by making sure the state of InteractiveView has been
//   * restarted.
//   */
//  @Test
//  public void testRestart() {
//    init();
//    InteractiveView inter = ((InteractiveView)interactive);
//    inter.startAnimation();
//    try {
//      TimeUnit.SECONDS.sleep(2);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    assertEquals(0,inter.getNowTick());
//    inter.restart();
//    assertEquals(-1,inter.getNowTick());
//  }
//
//  /**
//   * Tests the pauseOrResumerPanel method by making sure the state of InteractiveView has been
//   * paused or resumed.
//   */
//  @Test
//  public void pauseOrResumerPanel() {
//    init();
//    InteractiveView inter = ((InteractiveView)interactive);
//    inter.startAnimation();
//    try {
//      TimeUnit.SECONDS.sleep(2);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    assertEquals(0,inter.getNowTick());
//    inter.pauseOrResumerPanel();
//    assertEquals(0,inter.getNowTick());
//    inter.pauseOrResumerPanel();
//    try {
//      TimeUnit.SECONDS.sleep(2);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//    assertEquals(1,inter.getNowTick());
//  }
//
//  /**
//   * Tests the setSpeed method in InteractiveView by making sure it sets the right speed.
//   */
//  @Test
//  public void testSetSpeed() {
//    init();
//    interactive.setSpeed(30);
//    assertEquals(true, 30 == interactive.getSpeed());
//  }
//
//  /**
//   * Tests the SetLoop method within InteractiveView to make sure it properly
//   * sets looping at the right time.
//   */
//  @Test
//  public void testSetLoop() {
//    init();
//    interactive.setLoop();
//    assertEquals(true,interactive.getLoopSet());
//    interactive.setLoop();
//    assertEquals(false, interactive.getLoopSet());
//  }
//
//  /**
//   * Tests the ItemListener in InteractiveView, by making sure that it selects
//   * all the boxes when given a relevant command.
//   */
//  @Test
//  public void testItemListener() {
//    init();
//    JCheckBox selectAll = new JCheckBox("Check All");
//    selectAll.setSelected(false);
//    selectAll.setActionCommand("Select All");
//    ItemEvent test = new ItemEvent(selectAll, 2, "hi", 1);
//    InteractiveView inter = ((InteractiveView)interactive);
//    inter.itemStateChanged(test);
//    JCheckBox[] checkBoxes = inter.getCheckBoxes();
//    for ( int i = 0; i < checkBoxes.length; i += 1) {
//      assertEquals(true, checkBoxes[i].isSelected());
//    }
//    selectAll = new JCheckBox("Check All");
//    selectAll.setSelected(true);
//    selectAll.setActionCommand("Select All");
//    test = new ItemEvent(selectAll, 2, "hi", 2);
//    inter.itemStateChanged(test);
//    checkBoxes = inter.getCheckBoxes();
//    for ( int i = 0; i < checkBoxes.length; i += 1) {
//      assertEquals(false, checkBoxes[i].isSelected());
//    }
//  }
//
//  /**
//   * Tests the ItemListener in InteractiveView, by making sure that it selects
//   * some boxes when given a relevant command.
//   */
//  @Test
//  public void testItemListener2() {
//    init();
//    ReadOnlyModel model1 = new ReadOnlyModel(model);
//    InteractiveView view1 = new InteractiveView(model1, 5.0);
//    assertEquals(false, view1.getCheckBoxes()[0].isSelected());
//    assertEquals(false, view1.getCheckBoxes()[2].isSelected());
//    JCheckBox selectOne = new JCheckBox("C");
//    JCheckBox selectOne2 = new JCheckBox("R");
//    selectOne.setActionCommand("C");
//    selectOne2.setActionCommand("R");
//    ItemEvent test = new ItemEvent(view1.getCheckBoxes()[0], 1, "C", 0);
//    ItemEvent test2 = new ItemEvent(view1.getCheckBoxes()[2], 2, "R", 0);
//    view1.itemStateChanged(test);
//    view1.itemStateChanged(test2);
//    assertEquals(false, view1.getCheckBoxes()[0].isSelected());
//    assertEquals(false, view1.getCheckBoxes()[2].isSelected());
//  }
//
//}
