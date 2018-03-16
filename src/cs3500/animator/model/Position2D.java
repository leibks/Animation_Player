package cs3500.animator.model;

/**
 * Represent the class of position, which use the two decimal numbers
 * to represent the position of some objects(eg: shape) in the 2D background
 * of animation.
 */
public class Position2D {
  private float xPosition;
  private float yPosition;

  /**
   * Construct one Position2D from given x and y value to make sure the 2D
   * position in the background.
   *
   * @param x the given double for represent the x position in the background
   * @param y the given double for represent the y position in the background
   */
  public Position2D(float x, float y) {
    this.xPosition = x;
    this.yPosition = y;
  }

  /**
   * Get hte x position of the Position2D class.
   *
   * @return the double to represent x coordinate position
   */
  public float getX() {
    return this.xPosition;
  }

  /**
   * Get hte y position of the Position2D class.
   *
   * @return the double to represent y coordinate position
   */
  public float getY() {
    return this.yPosition;
  }

  /**
   * get the string to represent information of position x and y.
   *
   * @return the string to represent the position of shape
   */
  public String makeString() {
    return "(" + String.format("%s",  xPosition) + ","
            + String.format("%s",  yPosition) + ")";
  }

}
