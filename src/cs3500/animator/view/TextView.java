package cs3500.animator.view;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import cs3500.animator.model.AbstractAnimation;
import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.ChangeColorAnimation;
import cs3500.animator.model.ChangeScaleAnimation;
import cs3500.animator.model.MoveAnimation;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.ShapeType;

/**
 * a view for the Animation generator application, which when given
 * an input file an output file, and a tempo of speed for an application,
 * produces a text block describing the animation in progess to a given output file.
 * If their is is not output file the text block is printed in System.out .
 */
public class TextView implements IViews {
  private ReadOnlyModel model;
  private double tempo;

  /**
   * Construct a TextView object for showing the textual description of an animation model.
   *
   * @param model the given model is the read only model to show a textual description
   * @param tempo the numbers of ticks per second
   */
  public TextView(ReadOnlyModel model, double tempo) {
    this.model = model;
    this.tempo = tempo;
  }

  @Override
  public Appendable getOutput() {
    ArrayList<AbstractShape> listOfShapes = model.getShapesInfor();
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = model.getAnimationInfor();
    ArrayList<Integer> timeUse = model.getTimeUseInfor();
    String result = "";
    if (listOfShapes.size() != 0) {
      result += "Shapes:" + "\r\n";
    }
    for (int i = 0; i < listOfShapes.size(); i++ ) {
      if (i == listOfShapes.size() - 1 && mapAnimations.size() == 0) {
        //last one shape and not animations
        result += this.getShapeInfor(listOfShapes.get(i));
      } else {
        result += this.getShapeInfor(listOfShapes.get(i)) + "\r\n" + "\r\n";
      }
    }
    Collections.sort(timeUse);
    for (int k = 0; k < timeUse.size(); k++ ) {
      ArrayList<AbstractAnimation> want = mapAnimations.get(timeUse.get(k));
      for (int j = 0; j < want.size(); j++) {
        //this is the last one, do not need the separate line
        if (k == timeUse.size() - 1 && j == want.size() - 1) {
          result += this.getAnimationString(want.get(j));
        } else {
          result +=  this.getAnimationString(want.get(j)) + "\r\n";
        }
      }
    }
    StringWriter w = new StringWriter();
    return w.append(result);
  }

  @Override
  public void setSpeed(double tempo) {
    this.tempo = tempo;
  }

  @Override
  public String getViewType() {
    return "Text View";
  }

  @Override
  public void setLoop() {
    throw new UnsupportedOperationException(
            "Text View Cannot Loop");
  }


  @Override
  public void showView() {
    throw new UnsupportedOperationException(
            "Text View Cannot Show View");
  }


  /**
   * Get the string that represent the information for different type of shapes.
   *
   * @param shape the given shape we want to get the string information
   * @return the String to describe the shape information
   * @throws IllegalArgumentException if the shape type does not exist.
   */
  private String getShapeInfor(AbstractShape shape) {
    String result = "";
    result += this.stringShapeTitle(shape);
    switch (shape.getType()) {
      case RECTANGLE:
        result += "Lower-left corner: " + shape.getPosition().makeString()  + ", "
                + "Width: " + String.format("%s", shape.getWidth()) + ", "
                + "Height: " + String.format("%s", shape.getHeight()) + ", "
                + "Color: " + shape.getColor().colorState() + "\r\n";
        break;
      case OVAL:
        result += "Center: " + shape.getPosition().makeString() + ", "
                + "X radius: " + String.format("%s", shape.getWidth()) + ", "
                + "Y radius: " + String.format("%s", shape.getHeight()) + ", "
                + "Color: " + shape.getColor().colorState() + "\r\n";
        break;
      default: throw new IllegalArgumentException("Invalid Shape Type to Get String");
    }
    result += this.appearShapeStringInfor(shape);
    return  result;
  }

  /**
   * Get the string for type of shape.
   *
   * @param type the given shape type
   * @return the string to represent the type of shape
   */
  private String getShapeTypeString(ShapeType type) {
    switch (type) {
      case OVAL: return "oval";
      case RECTANGLE: return  "rectangle";
      //this default should be impossible because I limit the type in the constructor of shape
      default: return "Not correct shape";
    }
  }

  /**
   * Get the common title string for the shape, including name and type.
   *
   * @param shape the given shape we want to get the title string for it
   * @return the string for common title the shape
   */
  private String stringShapeTitle(AbstractShape shape) {
    String result = "";
    result += "Name: " + shape.getName() + "\r\n";
    result += "Type: " + this.getShapeTypeString(shape.getType()) + "\r\n";
    return  result;
  }

  /**
   * Get the String to represent the appearing and disappearing time of the shape.
   * @param shape the given shape we want to get the appear and disappear string for it
   * @return the String to represent the common appearing and disappearing time
   */
  private String appearShapeStringInfor(AbstractShape shape) {
    String result = "";
    result += "Appears at t=" + shape.getAppear() / tempo + "s" + "\r\n";
    result += "Disappears at t=" + shape.getDisappear() / tempo + "s";
    return  result;
  }

  /**
   * Using the string to describe the animation information.
   *
   * @param animation the given animation we want to show its string information
   * @return the text description of the animation
   */
  private String getAnimationString(AbstractAnimation animation) {
    String result = "";
    switch (animation.getAnimationType()) {
      case COLORCHANGE:
        ChangeColorAnimation want = (ChangeColorAnimation) animation;
        result += "Shape " + animation.getShapeName() + " changes color "
                + "from " + want.getChangeFrom().colorState() + " to "
                + want.getChangeTo().colorState() + " from t="
                + animation.getStartTime()  / tempo + "s"
                + " to t=" + animation.getEndTime()  / tempo + "s";
        break;
      case SCALECHANGE:
        ChangeScaleAnimation want1 = (ChangeScaleAnimation) animation;
        result += "Shape " + animation.getShapeName() + " scales "
                + this.showScaleInfor(want1.getChangeShapeType(),
                want1.getValueChangeFrom(), want1.getValueChangeTo())
                + " from t=" + animation.getStartTime()  / tempo + "s"
                + " to t=" + animation.getEndTime()   / tempo + "s";
        break;
      case MOVE:
        MoveAnimation want2 = (MoveAnimation) animation;
        result +=  "Shape " + animation.getShapeName() + " moves "
                + "from " + want2.getMoveFrom().makeString() + " to "
                + want2.getMoveTo().makeString() + " from t="
                +  animation.getStartTime() / tempo + "s"
                + " to t=" + animation.getEndTime()  / tempo + "s";
        break;
      default: throw  new IllegalArgumentException("Invalid Given Animation Command");
    }
    return result;
  }

  /**
   * Get the changing scale information basing on different shape's scale.
   *
   * @param type the given shape type
   * @param valueChange the list values of scale for given shape want to change from
   * @param valueChangeTo the list values of scale for given shape want to change to
   * @return the string to show the information of changing scale part in the shape
   */
  private String showScaleInfor(ShapeType type, ArrayList<Float> valueChange,
                                ArrayList<Float> valueChangeTo ) {
    String result = "";
    switch (type) {
      case RECTANGLE:
        result += "from Width: " + String.format("%s",  valueChange.get(0))
                + ", Height: " + String.format("%s", valueChange.get(1))
                + " to Width: " + String.format("%s",  valueChangeTo.get(0))
                + ", Height: " + String.format("%s", valueChangeTo.get(1));
        break;
      case OVAL:
        result += "from X radius: " + String.format("%s",  valueChange.get(0))
                + ", Y radius: "
                + String.format("%s", valueChange.get(1)) + " to X radius: "
                +  String.format("%s", valueChangeTo.get(0))
                + ", Y radius: " + String.format("%s",  valueChangeTo.get(1));
        break;
      default: result += "";
    }
    return result;
  }

}