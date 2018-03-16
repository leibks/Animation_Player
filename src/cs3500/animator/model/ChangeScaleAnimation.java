package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Represent the class of changing shape's scale animation, which extends the abstract class
 * AbstractAnimation, inherited all common fields should exist in the animation
 * The class represents the details of changing current scale of shape to
 * another scale during one time interval in 2D.
 */
public class ChangeScaleAnimation extends AbstractAnimation {
  // represent list of double value need to be change for the scales of the given shape
  private ArrayList<Float> valueChange;
  // represent list of double value need to be change to for scales fo the given shape
  private ArrayList<Float> valueChangeTo;
  private ShapeType shapeType;

  /**
   * Constructor a class of changing scale animation which inherits some common fields
   * in abstract class and has extra changing two list of scales (from and to).
   *
   * @param aniType the type of the animation
   * @param shapeName the name of the given shape to animation
   * @param shapeType the type of the given shape to animation
   * @param startTime the given start time of this animation
   * @param endTime   the given end time of this animation
   * @param valueChange the list values of scale for given shape want to change from
   * @param valueChangeTo the list values of scale for given shape want to change to
   */
  public ChangeScaleAnimation(AnimationType aniType, String shapeName, ShapeType shapeType,
                              int startTime, int endTime,
                              ArrayList<Float> valueChange,
                              ArrayList<Float> valueChangeTo) {
    super(aniType, shapeName, startTime, endTime);
    if (aniType != AnimationType.SCALECHANGE) {
      throw new IllegalArgumentException("Invalid given type of animation");
    }
    this.valueChange = valueChange;
    this.valueChangeTo = valueChangeTo;
    this.shapeType = shapeType;
  }

  /**
   * Get the list of values that represents attributes the shape want to change from.
   *
   * @return the list of values that we want to change from
   */
  public ArrayList<Float> getValueChangeFrom() {
    return new ArrayList(valueChange);
  }

  /**
   * Get the list of values that represents attributes the shape want to change to.
   *
   * @return the list of values that we want to change to
   */
  public ArrayList<Float> getValueChangeTo() {
    return new ArrayList(valueChangeTo);
  }

  /**|
   * Get the shape type that this animation want to change, which helps us found what scale
   * do we want to change.
   *
   * @return the type of shape we want to do animation on it.
   */
  public ShapeType getChangeShapeType() {
    return shapeType;
  }

  @Override
  public String getString() {
    return  "Shape " + shapeName + " scales " + showScaleInfor()
            + " from t=" + startTime + " to t=" + endTime;
  }

  /**
   * Get the changing scale information basing on different shape's scale.
   *
   * @return the string to show the information of changing scale part in the shape
   */
  protected String showScaleInfor() {
    String result = "";
    switch (shapeType) {
      case RECTANGLE:
        result += "from Width: " + valueChange.get(0) + ", Height: " + valueChange.get(1)
                + " to Width: " + valueChangeTo.get(0) + ", Height: "
                + valueChangeTo.get(1);
        break;
      case OVAL:
        result += "from X radius: " + valueChange.get(0) + ", Y radius: "
                + valueChange.get(1) + " to X radius: " + valueChangeTo.get(0)
                + ", Y radius: " + valueChangeTo.get(1);
        break;
      //I will add more shapes type here in the future assignment,
      // now I just use empty to make one default, I did not throw illegal ,because
      // when the shape was created, the shape type has be limited to not NUll
      default: result += "";
    }
    return result;
  }

  @Override
  public AbstractShape doAnimation(AbstractShape shape, int nowTime) {
    float from1 = valueChange.get(0);
    float from2 = valueChange.get(1);
    float to1 = valueChangeTo.get(0);
    float to2 = valueChangeTo.get(1);
    AbstractShape result = null;
    switch (shape.getType()) {
      case OVAL:
        result = new Oval(shape.getName(), shape.getType(), shape.getColor(),
                shape.getPosition(), shape.getAppear(), shape.getDisappear(),
                 this.tweening(from1, to1, nowTime), this.tweening(from2, to2, nowTime));
        break;
      case RECTANGLE: result = new Rectangle(shape.getName(), shape.getType(), shape.getColor(),
              shape.getPosition(), shape.getAppear(), shape.getDisappear(),
              this.tweening(from1, to1, nowTime), this.tweening(from2, to2, nowTime));
        break;
      default: throw new IllegalArgumentException("Wrong");
    }
    return result;
  }


}
