package cs3500.animator.view;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cs3500.animator.model.AbstractAnimation;
import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.AnimationType;
import cs3500.animator.model.ChangeColorAnimation;
import cs3500.animator.model.ChangeScaleAnimation;
import cs3500.animator.model.ModelOperations;
import cs3500.animator.model.MoveAnimation;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.ShapeType;

/**
 * a view for the Animation generator application, which when given
 * an input file an output file, and a tempo of speed for an application,
 * produces a corresponding svg animation file displaying the animation.
 */
public class SVGView implements IViews {
  private final ModelOperations model;
  private double tempo;
  private boolean loopOrNot;
  private int endTime;

  /**
   * Constructs a SVGView view object which creates an SVG file that
   * can display a video.
   *
   * @param model the given model is the read only model to convert to an SVG file
   * @param tempo the numbers of ticks per second
   */
  public SVGView(ReadOnlyModel model, double tempo)  {
    this.tempo = tempo;
    this.model = model;
    this.loopOrNot = false;
    this.endTime = 0;
  }

  /**
   * Prepare the showing svg information by given endTime and loopOrNot.
   *
   * @param endTime  world end time from the list of shapes we want to show svg
   * @param loopOrNot decide do we need to loop
   */
  public void prepareLoop(int endTime, boolean loopOrNot) {
    this.endTime = endTime;
    if (loopOrNot) {
      this.loopOrNot = true;
    } else {
      this.loopOrNot = false;
    }
  }

  @Override
  public void setSpeed(double tempo) {
    this.tempo = tempo;
  }

  @Override
  public String getViewType() {
    return "SVG View";
  }

  @Override
  public void setLoop() {
    if (loopOrNot) {
      this.loopOrNot = false;
    } else {
      this.loopOrNot = true;
    }
  }

  @Override
  public void showView() {
    throw new UnsupportedOperationException(
            "SVG View Cannot Show View");
  }

  @Override
  public Appendable getOutput() {
    String svgContents = "<svg width=\"10000\" height=\"10000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n\n";

    if (loopOrNot) {
      svgContents += "<rect>\n" +
              "   <animate id=\"base\" begin=\"0;base.end\" dur=\""
              + (1000 * (1.0 / this.tempo)) * endTime + "ms" + "\" " +
              "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
              "</rect>";
    }

    ArrayList<AbstractShape> shapelist = this.model.getShapesInfor();
    for (int i = 0; i < shapelist.size(); i += 1) {
      AbstractShape shape = shapelist.get(i);
      svgContents += getSVGShape(shape.getType(), shape);
    }

    svgContents += "</svg>";
    StringWriter sw = new StringWriter();
    return sw.append(svgContents);

  }

  /**
   * Produces a string representing an svg shape used in an svg animation.
   *
   * @param type - the ShapeType of the given shape.
   * @param shape - the given AbstractShape.
   * @return the SVG shape that represents the given ShapeType.
   * @throws IllegalArgumentException if the shape type does not exist.
   */
  private String getSVGShape(ShapeType type, AbstractShape shape) {
    String result = "";
    if (type == ShapeType.OVAL) {
      result +=  "<ellipse id=\"" + shape.getName() + "\" cx=\""
              + shape.getPosition().getX() + "\" cy=\"" + shape.getPosition().getY()
              + "\"" + " rx=\"" + shape.getWidth()
              + "\" ry=\"" + shape.getHeight()
              + "\" fill=\"rgb(" + (int)Math.floor(shape.getColor().getRed() * 255)
              + "," + (int)Math.floor(shape.getColor().getGreen() * 255)
              + "," + (int)Math.floor(shape.getColor().getBlue() * 255)
              + ")\" visibility=\"hidden\" >\n";
      result += this.setVisibleInformation((shape.getAppear() * (1000 * (1.0 / this.tempo))),
              (shape.getDisappear() * (1000 * (1.0 / this.tempo))));
      result += getSVGAnimations(shape, "rx","ry","cx","cy");
      result += "</ellipse>\n\n";
      return  result;
    } else if (type == ShapeType.RECTANGLE) {
      result += "<rect id=\"" + shape.getName() + "\" x=\""
              + shape.getPosition().getX() + "\" y=\"" + shape.getPosition().getY()
              + "\"" + " width=\"" + shape.getWidth()
              + "\" height=\"" + shape.getHeight()
              + "\" fill=\"rgb(" + (int)Math.floor(shape.getColor().getRed() * 255)
              + "," + (int)Math.floor(shape.getColor().getGreen() * 255)
              + "," + (int)Math.floor(shape.getColor().getBlue() * 255)
              + ")\" visibility=\"hidden\" >\n";
      result += this.setVisibleInformation((shape.getAppear() * (1000 * (1.0 / this.tempo))),
              (shape.getDisappear() * (1000 * (1.0 / this.tempo))));
      result += getSVGAnimations(shape, "width","height", "x", "y");
      result += "</rect>\n\n";
      return result;
    } else {
      throw new IllegalArgumentException("shape dose not exist");
    }
  }

  /**
   * Get the string information to set the shape to visible or hidden during time interval.
   *
   * @param appear the appearing time
   * @param disappear the disappearing time
   * @return the string represents the hidden or visible
   */
  private String setVisibleInformation(double appear, double disappear) {
    String loopInfo = "";
    if (loopOrNot) {
      loopInfo = "base.begin+";
    }
    return "<animate attributeType=\"xml\" attributeName=\"visibility\"" +
            " to=\"visible\" begin=\"" + loopInfo + appear + "ms" + "\" dur=\""
            + (disappear - appear) + "ms" + "\" " + " fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" attributeName=\"visibility\" " +
            "to=\"hidden\" begin=\"" + loopInfo + disappear + "ms\" fill=\"freeze\" />";
  }

  /**
   * Produces a string encapsulating all the animations associated with the given shape SVG.
   *
   * @param shape - the given AbstractShape.
   * @param xString  - a string that represents the x-dimension of the Shape in the Animation
   * @param yString  - a string that represents the y-dimension of the Shape in the Animation
   * @param xPos - a string that represents the x-posistion of the Shape in the Animation
   * @param yPos - a string that represents the x-posistion of the Shape in the Animation
   * @return a string representing an svg block, that encapsulates all the svg animations
   *                    a given shape has.
   */
  private String getSVGAnimations(AbstractShape shape, String xString, String yString,
                                  String xPos, String yPos) {
    String returnString = "";
    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations = this.model.getAnimationInfor();
    for (Map.Entry<Integer, ArrayList<AbstractAnimation>> entry : mapAnimations.entrySet()) {
      ArrayList<AbstractAnimation> entryList = entry.getValue();
      for (AbstractAnimation a : entryList) {
        if (a.getShapeName().equals(shape.getName()) ) {
          returnString += getSVGAnimationText(shape, a, xString, yString, xPos, yPos);
        }
      }
    }
    return returnString;
  }

  /**
   * Returns a block of text that represents the SVG animation of the given AbstractAnimation.
   *
   * @param shape - the given AbstractShape.
   * @param animation - the given AbstractAnimation to get the SVG animation information from
   * @param xString  - a string that represents the x-dimension of the Shape in the Animation
   * @param yString  - a string that represents the y-dimension of the Shape in the Animation
   * @param xPos - a string that represents the x-posistion of the Shape in the Animation
   * @param yPos - a string that represents the x-posistion of the Shape in the Animation
   * @return the given's AbstractAnimations SVG text information.
   */
  public String getSVGAnimationText(AbstractShape shape, AbstractAnimation animation,
                                     String xString, String yString,
                                     String xPos, String yPos) {
    String returnString = "";
    String loopInformation = "";
    if (loopOrNot) {
      loopInformation = "base.begin+";
    }
    if (animation.getAnimationType() == AnimationType.MOVE) {
      MoveAnimation move = (MoveAnimation) animation;
      returnString += this.repeatingTimeInfor(xPos, loopInformation, move) +
              + move.getMoveFrom().getX()
              + "\" to=\"" + move.getMoveTo().getX() + "\" fill=\"freeze\" />\n";
      returnString += this.getLoopInformation(xPos, String.valueOf(shape.getPosition().getX()));
      returnString += this.repeatingTimeInfor(yPos, loopInformation, move) +
              + move.getMoveFrom().getY()
              + "\" to=\"" + move.getMoveTo().getY()
              + "\" fill=\"freeze\" />\n";
      returnString += this.getLoopInformation(yPos, String.valueOf(shape.getPosition().getY()));
    } else if (animation.getAnimationType() == AnimationType.SCALECHANGE) {
      ChangeScaleAnimation change = (ChangeScaleAnimation) animation;
      ArrayList<Float> after = change.getValueChangeFrom();
      ArrayList<Float> before = change.getValueChangeTo();
      if (before.size() >= 1) {
        returnString += this.repeatingTimeInfor(xString, loopInformation, change) +
                + before.get(0) + "\" to=\"" + after.get(0)
                + "\" fill=\"freeze\" />\n";
        returnString += this.getLoopInformation(xString, String.valueOf(shape.getWidth()));
      }
      if (before.size() >= 2) {
        returnString += this.repeatingTimeInfor(yString, loopInformation, change) +
                + before.get(1) + "\" to=\""
                + after.get(1) + "\" fill=\"freeze\" />\n";
        returnString += this.getLoopInformation(yString, String.valueOf(shape.getHeight()));
      }
    } else {
      ChangeColorAnimation colorChange = (ChangeColorAnimation) animation;
      String startFrom = "rgb("
              + (int)Math.floor(colorChange.getChangeFrom().getRed() * 255)
              + ","
              + (int)Math.floor(colorChange.getChangeFrom().getGreen() * 255)
              + ","
              + (int)Math.floor(colorChange.getChangeFrom().getBlue() * 255) + ")";
      String attributeStart = "rgb("
              + (int)Math.floor(shape.getColor().getRed() * 255)
              + ","
              + (int)Math.floor(shape.getColor().getGreen() * 255)
              + ","
              + (int)Math.floor(shape.getColor().getBlue() * 255) + ")";
      returnString += this.repeatingTimeInfor("fill", loopInformation, colorChange)
              + startFrom + "\" to=\""
              + "rgb("
              + (int)Math.floor(colorChange.getChangeTo().getRed()  * 255)
              + ","
              + (int)Math.floor(colorChange.getChangeTo().getGreen() * 255)
              + ","
              + (int)Math.floor(colorChange.getChangeTo().getBlue() * 255)
              + ")\" fill=\"freeze\" />\n";
      returnString += this.getLoopInformation("fill", attributeStart);
    }
    return returnString;
  }


  /**
   * Get the end loop information.
   *
   * @param name the name of the changing attribute
   * @param start the start attribute of this animation
   * @return the set animation back to original one svg information
   */
  private String getLoopInformation(String name, String start) {
    String result = "";
    if (loopOrNot) {
      return  "    <animate attributeType=\"xml\" begin=\"" +
              "base.end\" dur=\"10ms\" attributeName=\"" + name + "\" to=\"" +
              start  + "\" fill=\"freeze\" />\n";
    }
    return  result;
  }

  /**
   * Getting the repeating string part of animation command.
   *
   * @param replace the replacing string
   * @param loopInformation the loop information string
   * @param animation the animation we want to transfer to svg
   * @return the string to represent the repeating code part
   */
  private String repeatingTimeInfor(String replace, String loopInformation,
                                    AbstractAnimation animation) {
    return  "    <animate attributeType=\"xml\" begin=\""
            + loopInformation
            +  (animation.getStartTime() * (1000 * (1.0 / this.tempo)))
            +  "ms\" dur=\""
            + ((animation.getEndTime() * (1000 * (1.0 / this.tempo)))
            - (animation.getStartTime() * (1000 * (1.0 / this.tempo))))
            + "ms\" attributeName=\"" + replace + "\" from=\"";
  }

}
