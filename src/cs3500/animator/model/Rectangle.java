package cs3500.animator.model;

import java.util.Objects;

/**
 * Represent the class of rectangle and implements the AbstractShape to inherit some
 * common fields and methods in the shape. Besides common features, rectangle still has
 * width and height.
 */
public class Rectangle extends AbstractShape {
  private float width;
  private float height;

  /**
   * Constructor one rectangle shape which contains the common attributes of shape,
   * and still has width and height.
   *
   * @param name  the name of the rectangle
   * @param type  the type of the rectangle
   * @param color the color of this shape
   * @param position the position of this shape
   * @param appears the appears time of the rectangle
   * @param disappears  the disappears time of the rectangle
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @param layer       the layer of shapes need to be shown
   */
  public Rectangle(String name, ShapeType type, ColorInfo color, Position2D position,
                   int appears, int disappears, float width, float height, int layer) {
    super(name, type, color, position, appears, disappears, layer);
    if (type != ShapeType.RECTANGLE) {
      throw new IllegalArgumentException("Invalid given type of shape");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Constructor one rectangle shape which contains the common attributes of shape,
   * and still has width and height, default layer is 0.
   *
   * @param name  the name of the rectangle
   * @param type  the type of the rectangle
   * @param color the color of this shape
   * @param position the position of this shape
   * @param appears the appears time of the rectangle
   * @param disappears  the disappears time of the rectangle
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   */
  public Rectangle(String name, ShapeType type, ColorInfo color, Position2D position,
                   int appears, int disappears, float width, float height) {
    this(name, type, color, position, appears, disappears, width, height, 0);
  }


  @Override
  public float getWidth() {
    return this.width;
  }

  @Override
  public float getHeight() {
    return this.height;
  }

  @Override
  public String getInfor() {
    String result = "";
    result += this.stringTitle();
    result += "Lower-left corner: " + this.position.makeString() + ", "
            + "Width: " + String.format("%s",  width) + ", "
            + "Height: " + String.format("%s",  height) + ", "
            + "Color: "  + this.color.colorState() + "\r\n";
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
            && this.width == that.getWidth()
            && this.height == that.getHeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, color.getRed(), color.getGreen(), color.getBlue(),
            position.getX(), position.getY(), appears, disappears, chosen, width, height);
  }

}
