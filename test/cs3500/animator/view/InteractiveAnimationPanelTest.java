//package cs3500.animator.view;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import cs3500.animator.model.AbstractAnimation;
//import cs3500.animator.model.AbstractShape;
//import cs3500.animator.model.AnimationType;
//import cs3500.animator.model.BasicModel;
//import cs3500.animator.model.ChangeColorAnimation;
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
///**
// * Test all methods in the InteractiveAnimationPanel Class.
// */
//public class InteractiveAnimationPanelTest {
//  Position2D p1 = new Position2D((float)200.0, (float)200.0);
//  Position2D p2 = new Position2D((float)300.0, (float)300.0);
//  Position2D p3 = new Position2D((float)500.0, (float)100.0);
//  Position2D p4 = new Position2D((float)500.0, (float)400.0);
//  ShapeType rectype = ShapeType.RECTANGLE;
//  ShapeType ovalType = ShapeType.OVAL;
//  ColorInfo blue1 = new ColorInfo((float)0.0, (float) 0.0,(float) 1.0);
//  ColorInfo red1 = new ColorInfo((float)1.0, (float) 0.0,(float) 0.0);
//  ColorInfo green1 = new ColorInfo((float)0.0, (float) 1.0,(float) 0.0);
//  AnimationType moveT = AnimationType.MOVE;
//  AnimationType colorChangeT = AnimationType.COLORCHANGE;
//  AbstractAnimation moveCh1 = new MoveAnimation(moveT, "R",
//          10, 50, p1, p2);
//  AbstractAnimation moveCh2 = new MoveAnimation(moveT, "C",
//          20, 70, p3, p4);
//  AbstractAnimation colorCh1 = new ChangeColorAnimation(colorChangeT, "C",
//          50, 80, blue1, green1);
//  AbstractAnimation move1ChSpecial = new MoveAnimation(moveT, "K2",
//          51, 62, p1, p2);
//  AbstractShape rect1 = new Rectangle("R",rectype,
//          red1, p1, 1, 100, (float) 50.0, (float)100.0);
//  AbstractShape oval1 = new Oval("C",ovalType,
//          blue1, p3, 6, 100, (float) 60.0, (float) 30.0);
//  AbstractShape rect2 = new Rectangle("R2",rectype,
//          red1, p1, 1, 100, (float) 50.0, (float) 100.0);
//  AbstractShape oval2 = new Oval("K2",ovalType,
//          blue1, p2, 10, 20, (float) 2.8, (float) 9.05);
//  AbstractShape rect3 = new Rectangle("R1",rectype,
//          red1, p1, 1, 100, (float) 50.0, (float) 100.0);
//  BasicModel modelB1 = new BasicModel();
//  BasicModel modelB2 = new BasicModel();
//  ArrayList<AbstractShape> listShapes = new ArrayList<>();
//  ArrayList<AbstractShape> listShapes1 = new ArrayList<>();
//  ArrayList<AbstractShape> listShapes2 = new ArrayList<>();
//  ArrayList<AbstractShape> listShapes3 = new ArrayList<>();
//
//  /**
//   * Initializes the all the environmental variables used for testing.
//   */
//  void init() {
//    modelB1.addShape(oval1);
//    modelB1.addShape(rect1);
//    modelB1.addAnimation(moveCh1);
//    modelB1.addAnimation(moveCh2);
//    modelB1.addAnimation(colorCh1);
//    listShapes.add(oval1);
//    listShapes.add(rect1);
//    listShapes.add(rect2);
//    listShapes2.add(oval1);
//    listShapes2.add(rect1);
//    modelB2.addShape(oval2);
//    modelB2.addShape(rect3);
//    modelB2.addAnimation(move1ChSpecial);
//    listShapes3.add(oval2);
//    listShapes3.add(rect3);
//  }
//
//  ReadOnlyModel model = new ReadOnlyModel(modelB1);
//  ReadOnlyModel model2 = new ReadOnlyModel(modelB2);
//  InteractiveAnimationPanel panel1 = new InteractiveAnimationPanel(model,
//          1.0, 1000);
//  InteractiveAnimationPanel panel2 = new InteractiveAnimationPanel(model2,
//          10.0, 2000);
//
//  // Test the getting stop method
//  @Test
//  public void testGetStop() throws Exception {
//    assertEquals(false, panel1.getStop());
//    assertEquals(false, panel2.getStop());
//    panel1.startAnimation(listShapes);
//    panel1.pauseOrResumerPanel();
//    assertEquals(true, panel1.getStop());
//  }
//
//  // Test for starting animation method
//  @Test
//  public void startAnimation() throws Exception {
//    assertEquals(listShapes2, panel1.getNowShapes());
//    panel1.startAnimation(listShapes);
//    assertEquals(listShapes, panel1.getNowShapes());
//    assertEquals(listShapes3, panel2.getNowShapes());
//    panel2.startAnimation(listShapes1);
//    assertEquals(listShapes1, panel2.getNowShapes());
//  }
//
//  // Test for restarting animation method
//  @Test
//  public void restart() throws Exception {
//    assertEquals(listShapes2, panel1.getNowShapes());
//    panel1.restart(listShapes);
//    assertEquals(listShapes, panel1.getNowShapes());
//    assertEquals(listShapes3, panel2.getNowShapes());
//    panel2.restart(listShapes);
//    assertEquals(listShapes, panel2.getNowShapes());
//  }
//
//  // Test the pause or resume panel method
//  @Test
//  public void pauseOrResumerPanel() throws Exception {
//    assertEquals(false, panel1.getStop());
//    assertEquals(false, panel2.getStop());
//    panel1.startAnimation(listShapes);
//    panel1.pauseOrResumerPanel();
//    assertEquals(true, panel1.getStop());
//    panel1.pauseOrResumerPanel();
//    assertEquals(false, panel1.getStop());
//    panel2.pauseOrResumerPanel();
//    panel2.pauseOrResumerPanel();
//    panel2.pauseOrResumerPanel();
//    assertEquals(true, panel2.getStop());
//  }
//
//}