package cs3500.animator.model;

/**
 * Represent an abstract class of animation commands in the model, which contains all common
 * attribute and methods for animations. It can provide useful data of animation for others
 * to retrieve. It can show animation’s information by string.
 */
public abstract class AbstractAnimation {
  protected AnimationType aniType;
  protected String shapeName;
  protected int startTime;
  protected int endTime;

  /**
   * Constructor a abstract class of animation which contains some common fields
   * for different type of animations.
   *
   * @param aniType the type of the animation
   * @param shapeName the name of the given shape to animation
   * @param startTime the given start time of this animation
   * @param endTime the given end time of this animation
   */
  public AbstractAnimation(AnimationType aniType, String shapeName, int startTime, int endTime) {
    if ( startTime < 0 || endTime < 0 || endTime < startTime ) {
      throw new IllegalArgumentException("Not valid start or end time");
    }
    this.aniType = aniType;
    this.shapeName = shapeName;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * Get type of animation..
   *
   * @return the type of animating.
   */
  public AnimationType getAnimationType() {
    return aniType;
  }

  /**
   * Get name of animating shape.
   *
   * @return the name of animating shape.
   */
  public String getShapeName() {
    return shapeName;
  }

  /**
   * Get the start time of this animation.
   *
   * @return the integer that represents the start of this animation
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * Get the end time of this animation.
   *
   * @return the integer that represents the end of this animation
   */
  public int getEndTime() {
    return endTime;
  }

  /**
   * Using the string to describe the animation information.
   *
   * @return the text description of the animation
   */
  public abstract String getString();

  /**
   * Basing on the now world tick situation and shape information, get the new shape
   * after doing this animation on this on tick.
   *
   * @param shape the given shape we want to do animation to it.
   * @param nowTime the given tick that represented the world tick in the animation panel now
   * @return the new shape that changed from old given shape by this animation.
   */
  public abstract AbstractShape doAnimation(AbstractShape shape, int nowTime);

  /**
   * Current value by using tweening for calculation by using this formula
   * 𝑓(𝑡)= 𝑎 * [(𝑡𝑏−𝑡) / (t𝑏−𝑡𝑎)] + 𝑏 * [(𝑡−𝑡𝑎) / (𝑡𝑏−𝑡𝑎)].
   *
   * @param changeFrom the value we change from.
   * @param changeTo the value we want to change to
   * @param t the current tick in the animation panel
   * @return the value that is calculated by the above formula
   */
  protected float tweening(float changeFrom, float changeTo, int t) {
    return  changeFrom * ((endTime - (float) t) / (endTime - startTime) )
            +   changeTo * (((float) t - startTime) / (endTime - startTime));
  }

}
