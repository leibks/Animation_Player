package cs3500.animator.model;

/**
 * Represent the color information: three original color float value:
 * Red, Green, and Blue, the value is from 0.0 to 1.0.
 */
public class ColorInfo {
  private float redPart;
  private float greenPart;
  private float bluePart;

  /**
   * Constructor one ColorInfor construct to contain information of three parts of color.
   *
   * @param redPart the red part of the color
   * @param greenPart the green part of the color
   * @param bluePart the blue part of the color
   */
  public ColorInfo(float redPart, float greenPart, float bluePart) {
    this.redPart = redPart;
    this.greenPart = greenPart;
    this.bluePart = bluePart;
  }

  /**
   * Get the red par of the color.
   *
   * @return the float that represents the red value of the color
   */
  public float getRed() {
    return redPart;
  }

  /**
   * Get the green par of the color.
   *
   * @return the float that represents the green value of the color
   */
  public float getGreen() {
    return greenPart;
  }

  /**
   * Get the blue par of the color.
   *
   * @return the float that represents the blue value of the color
   */
  public float getBlue() {
    return bluePart;
  }

  /**
   * Transfer the three value of color information to string.
   *
   * @return String represent three float value in the color
   */
  public String colorState() {
    String result = "";
    result += "(" + String.format("%s",  redPart) + ","
            +  String.format("%s",  greenPart) + "," + String.format("%s",  bluePart) + ")";
    return  result;
  }

}
