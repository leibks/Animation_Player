//package cs3500.animator.view;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import cs3500.animator.model.AbstractAnimation;
//import cs3500.animator.model.AbstractShape;
//import cs3500.animator.model.BasicModel;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * test the create method in the factory class.
// */
//public class ViewCreatorTest {
//  ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
//  HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations1 = new HashMap<>();
//  ArrayList<Integer> record = new ArrayList<>();
//  BasicModel model = new BasicModel(listOfShapes, mapAnimations1, record);
//
//
//  // Test the create method.
//  @Test
//  public void create() throws Exception {
//    IViews view1 = new ViewCreator(model, 10).create("text");
//    IViews view2 = new ViewCreator(model, 10).create("visual");
//    IViews view3 = new ViewCreator(model, 10).create("svg");
//    IViews view4 = new ViewCreator(model, 10).create("interactive");
//    assertEquals("Text View", view1.getViewType());
//    assertEquals("SVG View", view3.getViewType());
//    assertEquals("Visual View", view2.getViewType());
//    assertEquals("Interactive View", view4.getViewType());
//
//  }
//
//  //Test illegal create view for create method by invalid given name of view
//  @Test (expected = IllegalArgumentException.class)
//  public void invalidGivenNameView() throws Exception {
//    IViews view2 = new ViewCreator(model, 10).create("interactive1");
//  }
//
//}