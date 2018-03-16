package cs3500.animator.model;

/**
 * Represent the class of changing shape's color animation, which extends the abstract class
 * AbstractAnimation, inherited all common fields should exist in the animation
 * The class represents the details of changing current color of shape to
 * another color during one time interval in 2D.
 */
public class ChangeColorAnimation extends AbstractAnimation {
  private ColorInfo changeFrom;
  private ColorInfo changeTo;

  /**
   * Constructor a class of changing color animation which inherits some common fields
   * in abstract class and has extra changing two colors (from and to).
   *
   * @param aniType the type of the animation
   * @param shapeName the name of the given shape to animation
   * @param startTime the given start time of this animation
   * @param endTime   the given end time of this animation
   * @param changeFrom the color information that change will start
   * @param changeTo the color information that change will end
   */
  public ChangeColorAnimation(AnimationType aniType, String shapeName, int startTime, int endTime,
                              ColorInfo changeFrom, ColorInfo changeTo) {
    super(aniType, shapeName,startTime, endTime);
    if (aniType != AnimationType.COLORCHANGE) {
      throw new IllegalArgumentException("Invalid given type of animation");
    }
    this.changeFrom = changeFrom;
    this.changeTo = changeTo;
  }

  /**
   * Get color where this shape will change from.
   *
   * @return the color we want to change from
   */
  public ColorInfo getChangeFrom() {
    return new ColorInfo(changeFrom.getRed(), changeFrom.getGreen(),  changeFrom.getBlue());
  }

  /**
   * Get color where this shape will change to.
   *
   * @return the color we want to change to
   */
  public ColorInfo getChangeTo() {
    return new ColorInfo(changeTo.getRed(), changeTo.getGreen(),  changeTo.getBlue());
  }


  @Override
  public String getString() {
    return  "Shape " + shapeName + " changes color "
            + "from " + changeFrom.colorState() + " to "
            + changeTo.colorState() + " from t=" + startTime
            + " to t=" + endTime;
  }

  @Override
  public AbstractShape doAnimation(AbstractShape shape, int nowTime) {
    ColorInfo newColor = new ColorInfo(this.tweening(changeFrom.getRed(),
            changeTo.getRed(), nowTime) , this.tweening(changeFrom.getGreen(),
            changeTo.getGreen(), nowTime), this.tweening(changeFrom.getBlue(),
            changeTo.getBlue(), nowTime));
    AbstractShape result = null;
    switch (shape.getType()) {
      case OVAL:
        result = new Oval(shape.getName(), shape.getType(), newColor, shape.getPosition(),
                shape.getAppear(), shape.getDisappear(), shape.getWidth(), shape.getHeight());
        break;
      case RECTANGLE: result = new Rectangle(shape.getName(), shape.getType(), newColor,
              shape.getPosition(), shape.getAppear(), shape.getDisappear(), shape.getWidth(),
              shape.getHeight());
        break;
      default: throw new IllegalArgumentException("Wrong");
    }

    return result;
  }

}
