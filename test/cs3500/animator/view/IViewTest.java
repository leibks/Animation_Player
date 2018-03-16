//package cs3500.animator.view;
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
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * A class that tests all non void IView methods in the IView classes.
// */
//public class IViewTest {
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
//          blue1, p3, 6, 100, (float) 60.0, (float) 30.0);
//  BasicModel model = new BasicModel();
//
//  /**
//   * Initializes the all the environmental variables used for testing.
//   */
//  void init() {
//    model.addShape(oval1);
//    model.addShape(rect1);
//    model.addAnimation(moveCh1);
//    model.addAnimation(moveCh2);
//    model.addAnimation(colorCh1);
//    ArrayList<Float> valueChange1 = new ArrayList();
//    ArrayList<Float> valueChangeTo1 = new ArrayList();
//    valueChange1.add((float) 50.0);
//    valueChange1.add((float) 100.0);
//    valueChangeTo1.add((float) 25.0);
//    valueChangeTo1.add((float) 100.0);
//    AbstractAnimation scaleCh1 = new ChangeScaleAnimation(scaleChangeT,
//            "R", rectype, 30, 70, valueChange1, valueChangeTo1);
//    AbstractAnimation scaleCh2 = new ChangeScaleAnimation(scaleChangeT,
//            "C", ovalType, 30, 70, valueChange1, valueChangeTo1);
//    model.addAnimation(scaleCh1);
//    model.addAnimation(scaleCh2);
//  }
//
//  /**
//   * Tests the getOutput of the SVGView class, by making sure it outputs a correct SVG file.
//   */
//  @Test
//  public void testSVGOutput() {
//    init();
//    IViews svg = new SVGView(new ReadOnlyModel(model),20);
//    assertEquals("<svg width=\"10000\" height=\"10000\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "\n" +
//            "<ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" " +
//            "fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\"" +
//            " begin=\"300.0ms\" dur=\"4700.0ms\"  fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" " +
//            "begin=\"5000.0ms\" fill=\"freeze\" />    <animate attributeType=\"xml\" " +
//            "begin=\"2500.0ms\" dur=\"1500.0ms\" attributeName=\"fill\" from=\"" +
//            "rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2500.0ms\" " +
//            "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"2500.0ms\" " +
//            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
//            + "    <animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"2000.0ms\" " +
//                    "attributeName=\"rx\" from=\"25.0\" to=\"50.0\" fill=\"freeze\" />\n" +
//                    "    <animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"" +
//            "2000.0ms\" attributeName=\"ry\" from=\"100.0\" to=\"100.0\"" +
//            " fill=\"freeze\" />\n"
//            + "</ellipse>\n" +
//            "\n" +
//            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" " +
//            "fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\"" +
//            " begin=\"50.0ms\" dur=\"4950.0ms\"  fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\"" +
//            " begin=\"5000.0ms\" fill=\"freeze\" />    <animate attributeType=\"xml\"" +
//            " begin=\"500.0ms\" dur=\"2000.0ms\" attributeName=\"x\" from=\"200.0\" " +
//            "to=\"300.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"2000.0ms\" " +
//            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"2000.0ms\" " +
//            "attributeName=\"width\" from=\"25.0\" to=\"50.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"2000.0ms\" " +
//            "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "\n" +
//            "</svg>",svg.getOutput().toString());
//  }
//
//  /**
//   * Tests the getOutput of the SVGView class, by making sure it outputs a correct SVG file,
//   * for loop text output.
//   */
//  @Test
//  public void testSVGOutputLoop() {
//    init();
//    SVGView svg = new SVGView(new ReadOnlyModel(model),20);
//    svg.prepareLoop(100, true);
//    assertEquals("<svg width=\"10000\" height=\"10000\" version=\"1.1\"\n" +
//            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
//            "\n" +
//            "<rect>\n" +
//            "   <animate id=\"base\" begin=\"0;base.end\" dur=\"5000.0ms\" " +
//            "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
//            "</rect><ellipse id=\"C\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\"" +
//            " ry=\"30.0\" fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\"" +
//            " begin=\"base.begin+300.0ms\" dur=\"4700.0ms\"  fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" " +
//            "begin=\"base.begin+5000.0ms\" fill=\"freeze\" />   " +
//            " <animate attributeType=\"xml\" begin=\"base.begin+2500.0ms\" " +
//            "dur=\"1500.0ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" " +
//            "to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\"" +
//            " attributeName=\"fill\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\" " +
//            "dur=\"2500.0ms\" attributeName=\"cx\" from=\"500.0\" to=\"500.0\" " +
//            "fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\" " +
//            "attributeName=\"cx\" to=\"500.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\" " +
//            "dur=\"2500.0ms\" attributeName=\"cy\" from=\"100.0\" to=\"400.0\"" +
//            " fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\"" +
//            " attributeName=\"cy\" to=\"100.0\" fill=\"freeze\" />\n"
//            + "    <animate attributeType=\"xml\" begin=\"base.begin+1500.0ms\""
//            + " dur=\"2000.0ms\" attributeName=\"rx\" from=\"25.0\" to=\"50.0\" " +
//            "fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\"" +
//            " attributeName=\"rx\" to=\"60.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.begin+1500.0ms\" " +
//            "dur=\"2000.0ms\" attributeName=\"ry\" from=\"100.0\" to=\"100.0\"" +
//            " fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\" " +
//            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\" />\n"
//            + "</ellipse>\n" + "\n" +
//            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\"" +
//            " fill=\"rgb(255,0,0)\" visibility=\"hidden\" >\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"visible\"" +
//            " begin=\"base.begin+50.0ms\" dur=\"4950.0ms\"  fill=\"freeze\" />\n" +
//            "<animate attributeType=\"xml\" attributeName=\"visibility\" to=\"hidden\" " +
//            "begin=\"base.begin+5000.0ms\" fill=\"freeze\" />    <animate" +
//            " attributeType=\"xml\" begin=\"base.begin+500.0ms\" dur=\"2000.0ms\"" +
//            " attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\" " +
//            "attributeName=\"x\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.begin+500.0ms\" " +
//            "dur=\"2000.0ms\" attributeName=\"y\" from=\"200.0\" to=\"300.0\"" +
//            " fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\"" +
//            " attributeName=\"y\" to=\"200.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.begin+1500.0ms\" " +
//            "dur=\"2000.0ms\" attributeName=\"width\" from=\"25.0\" to=\"50.0\"" +
//            " fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\" " +
//            "attributeName=\"width\" to=\"50.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.begin+1500.0ms\" " +
//            "dur=\"2000.0ms\" attributeName=\"height\" from=\"100.0\"" +
//            " to=\"100.0\" fill=\"freeze\" />\n" +
//            "    <animate attributeType=\"xml\" begin=\"base.end\" dur=\"10ms\" " +
//            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\" />\n" +
//            "</rect>\n" +
//            "\n" +
//            "</svg>",svg.getOutput().toString());
//  }
//
//
//  /**
//   * Tests the getOutput of the TextView class, by making sure it outputs a correct textblock.
//   */
//  @Test
//  public void testTextOutput() {
//    init();
//    IViews text = new TextView(new ReadOnlyModel(model),20);
//    assertEquals("Shapes:\r\n" +
//                    "Name: C\r\n" +
//                    "Type: oval\r\n" +
//                    "Center: (500.0,100.0), X radius: 60.0, " +
//                    "Y radius: 30.0, Color: (0.0,0.0,1.0)\r\n" +
//                    "Appears at t=0.3s\r\n" +
//                    "Disappears at t=5.0s\r\n" +
//                    "\r\n" +
//                    "Name: R\r\n" +
//                    "Type: rectangle\r\n" +
//                    "Lower-left corner: (200.0,200.0), Width: 50.0, " +
//                    "Height: 100.0, Color: (1.0,0.0,0.0)\r\n" +
//                    "Appears at t=0.05s\r\n" +
//                    "Disappears at t=5.0s\r\n" +
//                    "\r\n" +
//                    "Shape R moves from (200.0,200.0) to (300.0,300.0) " +
//                    "from t=0.5s to t=2.5s\r\n" +
//                    "Shape C moves from (500.0,100.0) to (500.0,400.0) " +
//                    "from t=1.0s to t=3.5s\r\n" + "Shape R scales from Width: 50.0, " +
//                    "Height: 100.0 to Width: 25.0, Height: 100.0 from t=1.5s " +
//                    "to t=3.5s\r\n" + "Shape C scales from X radius: 50.0, Y radius: " +
//                    "100.0 to X radius: 25.0, Y radius: 100.0 from t=1.5s to t=3.5s\r\n" +
//                    "Shape C changes color from (0.0,0.0,1.0) " +
//                    "to (0.0,1.0,0.0) from t=2.5s to t=4.0s",
//            text.getOutput().toString());
//  }
//
//  //illegal set loop in the Text View
//  @Test (expected = UnsupportedOperationException.class)
//  public void illegalSetLoop() throws Exception {
//    IViews text = new TextView(new ReadOnlyModel(model),20);
//    text.setLoop();
//  }
//
//  //illegal show view in the Text View
//  @Test (expected = UnsupportedOperationException.class)
//  public void illegalShowView() throws Exception {
//    IViews text = new TextView(new ReadOnlyModel(model),20);
//    text.showView();
//  }
//
//  //illegal set loop in the Visual View
//  @Test (expected = UnsupportedOperationException.class)
//  public void illegalSetLoopVisual() throws Exception {
//    IViews visual = new VisualAnimationView(new ReadOnlyModel(model),20);
//    visual.setLoop();
//  }
//
//
//  //illegal getOutput method in the Visual View
//  @Test (expected = UnsupportedOperationException.class)
//  public void illegalgetOutputVisual() throws Exception {
//    IViews visual = new VisualAnimationView(new ReadOnlyModel(model),20);
//    visual.getOutput();
//  }
//
//  //illegal show view in the SVG View
//  @Test (expected = UnsupportedOperationException.class)
//  public void illegalShowViewSVG() throws Exception {
//    IViews svg = new SVGView(new ReadOnlyModel(model),20);
//    svg.showView();
//  }
//
//}
