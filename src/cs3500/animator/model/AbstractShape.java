package cs3500.animator.model;


/**
 * Represent an abstract class of 2D shapes in the model, which contains all common attributes
 * of all shapes and methods to manipulate shapes. It can provide some useful data of shape
 * for others to retrieve. It can show shape’s information by string.
 */
public abstract class AbstractShape {
  protected String name;
  protected ShapeType type;
  protected ColorInfo color;
  protected Position2D position;
  protected int appears;
  protected int disappears;
  protected boolean chosen;
  protected int layer;

  /**
   * Constructor one basic shape which contains the common attributes of shape.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   * @param color the color of this shape
   * @param position the position of this shape
   * @param appears the appears time of the shape
   * @param disappears the disappears time of the shape
   * @param layer       the layer of shapes need to be shown
   * @throws IllegalArgumentException if the given parameters are invalid for one shape
   */
  public AbstractShape(String name, ShapeType type, ColorInfo color, Position2D position,
                       int appears, int disappears, int layer) {
    if ( disappears < 0 || appears < 0 || disappears < appears ) {
      throw  new IllegalArgumentException("Not valid appear or disappear time");
    }
    if (type == null || position == null || color == null) {
      throw new IllegalArgumentException("Invalid given type, color or position of shape");
    }
    this.name = name;
    this.type = type;
    this.color = color;
    this.position = position;
    this.appears = appears;
    this.disappears = disappears;
    this.layer = layer;
    this.chosen = true;
  }

  /**
   * Constructor one basic shape which contains the common attributes of shape, default layer
   * is 0.
   *
   * @param name the name of the shape
   * @param type the type of the shape
   * @param color the color of this shape
   * @param position the position of this shape
   * @param appears the appears time of the shape
   * @param disappears the disappears time of the shape
   * @throws IllegalArgumentException if the given parameters are invalid for one shape
   */
  public AbstractShape(String name, ShapeType type, ColorInfo color, Position2D position,
                       int appears, int disappears) {
    this(name, type, color, position, appears, disappears, 0);
  }


  /**
   * Get the layer of the shape.
   * @return the int that represent the layer of the shape
   */
  public int getLayer() {
    return  layer;
  }

  /**
   * Set this shape was chose to display or not.
   * @param want the boolean that represent show or not show this shapep
   */
  public void setChosen(boolean want) {
    chosen = want;
  }

  /**
   * Get show or not show information of shape.
   * @return boolean that represent the shape was chosen or not
   */
  public boolean getChosen() {
    return chosen;
  }

  /**
   * Set the color of shapes.
   * @param given the given new color of shape
   */
  public void setColor(ColorInfo given) {
    color = given;
  }

  /**
   * Get the position of the shape.
   *
   * @return the position that represent shape in x and y coordinate
   */
  public Position2D getPosition() {
    return  this.position;
  }

  /**
   * get the color information of the shape.
   *
   * @return the color information that represents red, greed, and blue part for shape
   */
  public ColorInfo getColor() {
    return  this.color;
  }

  /**
   * Briefly understand this value is first special attribute of shape, like width, xRadius.
   *
   * @return the width part attribute of the shape
   */
  public abstract float getWidth();

  /**
   * Briefly understand this value is second special attribute of shape, like height, yRadius.
   *
   * @return the height part attribute of the shape
   */
  public abstract float getHeight();

  /**
   *  Get the shape type of this shape.
   *
   * @return the type of this shape
   */
  public ShapeType getType() {
    return this.type;
  }

  /**
   * Get the shape name of this shape.
   *
   * @return the name of this shape
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the appear time of this shape.
   *
   * @return the int represent the appear time of this shape
   */
  public int getAppear() {
    return this.appears;
  }

  /**
   * Get the disappear time of this shape.
   *
   * @return the int represent the disappear time of this shape
   */
  public int getDisappear() {
    return this.disappears;
  }

  /**
   * Get the string that represent the information for different type of shapes.
   *
   * @return the String to describe the shape information
   */
  public abstract String getInfor();

  /**
   * Get the string for type of shape.
   *
   * @return the string to represent the type of shape
   */
  protected String getShapeTypeString() {
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
   * @return the string for common title the shape
   */
  protected String stringTitle() {
    String result = "";
    result += "Name: " + this.name + "\r\n";
    result += "Type: " + this.getShapeTypeString() + "\r\n";
    return  result;
  }

  /**
   * Get the String to represent the appearing and disappearing time of the shape.
   *
   * @return the String to represent the common appearing and disappearing time
   */
  protected  String appearStringInfor() {
    String result = "";
    result += "Appears at t=" + this.appears + "\r\n";
    result += "Disappears at t=" + this.disappears;
    return  result;
  }

}
