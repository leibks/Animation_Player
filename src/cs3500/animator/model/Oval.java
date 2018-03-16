package cs3500.animator.model;

import java.util.Objects;

/**
 * Represent the class of oval and implements the AbstractShape to inherit some
 * common fields and methods in the shape. Besides common features, oval still has
 * x radius and y radius of oval.
 */
public class Oval extends  AbstractShape {
  private float xRadius;
  private float yRadius;

  /**
   * Constructor one oval shape which contains the common attributes of shape,
   * and it still has x and y radius.
   *
   * @param name the name of the oval
   * @param type the type of the oval
   * @param color the color of this shape
   * @param position the position of this shape
   * @param appears the appears time of the oval
   * @param disappears   the disappears time of the oval
   * @param xRadius x radius of this oval
   * @param yRadius y radius of this oval
   * @param layer       the layer of shapes need to be shown
   */
  public Oval(String name, ShapeType type, ColorInfo color, Position2D position,
              int appears, int disappears, float xRadius, float yRadius, int layer) {
    super(name, type, color, position, appears, disappears, layer);
    if (type != ShapeType.OVAL) {
      throw new IllegalArgumentException("Invalid given type of shape");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * Constructor one oval shape which contains the common attributes of shape,
   * and it still has x and y radius, default layer is 0.
   *
   * @param name the name of the oval
   * @param type the type of the oval
   * @param color the color of this shape
   * @param position the position of this shape
   * @param appears the appears time of the oval
   * @param disappears   the disappears time of the oval
   * @param xRadius x radius of this oval
   * @param yRadius y radius of this oval
   */
  public Oval(String name, ShapeType type, ColorInfo color, Position2D position,
              int appears, int disappears, float xRadius, float yRadius) {
    this(name, type, color, position, appears, disappears, xRadius, yRadius, 0);
  }


  @Override
  public float getWidth() {
    return this.xRadius;
  }

  @Override
  public float getHeight() {
    return this.yRadius;
  }

  @Override
  public String getInfor() {
    String result = "";
    result += this.stringTitle();
    result += "Center: " + this.position.makeString() + ", "
            + "X radius: " + String.format("%s", xRadius) + ", "
            + "Y radius: " +  String.format("%s", yRadius) + ", "
            + "Color: " + this.color.colorState()  + "\r\n";
    result += this.appearStringInfor();
    return  result;
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (! (o instanceof AbstractShape)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
    AbstractShape that = (AbstractShape) o;

    return this.name.equals(that.name)
            && this.type.equals(that.type)
            && this.color.getRed() == that.getColor().getRed()
            && this.color.getGreen() == that.getColor().getGreen()
            && this.color.getBlue() == that.getColor().getBlue()
            && this.position.getX() == that.getPosition().getX()
            && this.position.getY() == that.getPosition().getY()
            && this.appears == that.appears
            && this.disappears == that.disappears
            && this.chosen == that.chosen
            && this.xRadius == that.getWidth()
            && this.yRadius == that.getHeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, color.getRed(), color.getGreen(), color.getBlue(),
            position.getX(), position.getY(), appears, disappears, chosen, xRadius, yRadius);
  }


}
