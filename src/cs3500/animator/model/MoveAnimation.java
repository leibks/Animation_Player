package cs3500.animator.model;

/**
 * Represent the class of moving shape animation, which extends the abstract class
 * AbstractAnimation, inherited all common fields should exist in the animation
 * The class represents the details of moving
 * shape from one now position of shape to another position in 2D during one time interval.
 */
public class MoveAnimation extends  AbstractAnimation {
  private Position2D moveFrom;
  private Position2D moveTo;

  /**
   * Constructor a class of moving shape's position animation which inherits some common fields
   * in abstract class and has extra changing two positions (from and to).
   *
   * @param aniType the type of the animation
   * @param shapeName the name of the given shape to animation
   * @param startTime the given start time of this animation
   * @param endTime   the given end time of this animation
   * @param moveFrom the position it wants to move from
   * @param moveTo the position it wants to move to
   */
  public MoveAnimation(AnimationType aniType, String shapeName, int startTime, int endTime,
                       Position2D moveFrom, Position2D moveTo) {
    super(aniType, shapeName,startTime, endTime);
    if (aniType != AnimationType.MOVE) {
      throw new IllegalArgumentException("Invalid given type of animation");
    }
    this.moveFrom = moveFrom;
    this.moveTo = moveTo;
  }

  /**
   * Get position where this shape will move from.
   *
   * @return the position we want to change from
   */
  public Position2D getMoveFrom() {
    return new Position2D(moveFrom.getX(), moveFrom.getY());
  }

  /**
   * Get position where this shape will move to.
   *
   * @return the position we want to change to
   */
  public Position2D getMoveTo() {
    return new Position2D(moveTo.getX(), moveTo.getY());
  }

  @Override
  public String getString() {
    return  "Shape " + shapeName + " moves "
            + "from " + moveFrom.makeString() + " to "
            + moveTo.makeString() + " from t=" + startTime
            + " to t=" + endTime;
  }

  @Override
  public AbstractShape doAnimation(AbstractShape shape, int nowTime) {
    Position2D newPosition = new Position2D(this.tweening(moveFrom.getX(),moveTo.getX(),
            nowTime) , this.tweening(moveFrom.getY(),moveTo.getY(), nowTime));
    AbstractShape result = null;
    switch (shape.getType()) {
      case OVAL:
        result = new Oval(shape.getName(), shape.getType(), shape.getColor(), newPosition,
                shape.getAppear(), shape.getDisappear(), shape.getWidth(), shape.getHeight());
        break;
      case RECTANGLE:
        result = new Rectangle(shape.getName(), shape.getType(), shape.getColor(),
                newPosition, shape.getAppear(), shape.getDisappear(), shape.getWidth(),
                shape.getHeight());
        break;
      default: throw new IllegalArgumentException("Wrong");
    }
    return result;
  }

}
