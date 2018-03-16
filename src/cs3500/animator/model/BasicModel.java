package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.view.TweenModelBuilder;

/**
 * Represent one animation model abstract class, which implements
 * to the ModelOperation and will inherit some common
 * methods to manipulate some operations and also will
 * show the basic fields to represent shapes and animations situations.
 */
public class BasicModel implements ModelOperations {
  private ArrayList<AbstractShape> listOfShapes;
  private HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations;
  private ArrayList<Integer> timeUse;

  /**
   * Constructor one model of the animation, which contains the existing shapes
   * and any animations need to be executed, shapes and animations are given at the beginning.
   *
   * @param listOfShapes the list of shapes existing in the model
   * @param mapAnimations the hashMap of list of commands contains the animations execute in
   *                         the model. Every list of animations will show at that specific
   *                         time (value of map), just store, not order
   * @param timeUse ArrayList of integer that records the time has been used,
   *                when we want to show animations or run animations, we just need to sort
   *                it and follow the order of startTime to pick up animations in above hashMap.
   */
  public BasicModel(ArrayList<AbstractShape> listOfShapes,
                    HashMap<Integer, ArrayList<AbstractAnimation>> mapAnimations,
                    ArrayList<Integer> timeUse) {
    this.listOfShapes = listOfShapes;
    this.mapAnimations = mapAnimations;
    this.timeUse = timeUse;
  }

  /**
   * Constructor one model of the animation, which contains the existing shapes
   * and any animations need to be executed, no shapes and animations exists at the beginning.
   */
  public BasicModel() {
    this.listOfShapes = new ArrayList<>();
    this.mapAnimations = new HashMap<>();
    this.timeUse = new ArrayList<>();
  }

  /**
   * Implement this builder interface class, create builder to describes constructing any
   * animation, shape-by-shape and step-by-step for AnimationFileReader.
   */
  public static final class Builder implements TweenModelBuilder<ModelOperations> {
    ModelOperations model;

    /**
     * Constructs a builder for configuring and then creating a basic animation model instance.
     */
    public Builder() {
      this.model = new BasicModel();
    }


    @Override
    public TweenModelBuilder<ModelOperations> addOval(
            String name, float cx, float cy, float xRadius, float yRadius, float red,
            float green, float blue, int startOfLife, int endOfLife, int layer) {
      this.model.addShape(new Oval(name, ShapeType.OVAL, new ColorInfo(red, green, blue),
              new Position2D(cx, cy), startOfLife, endOfLife, xRadius, yRadius, layer));
      return this;
    }

    @Override
    public TweenModelBuilder<ModelOperations> addRectangle(
            String name, float lx, float ly, float width, float height, float red,
            float green, float blue, int startOfLife, int endOfLife, int layer) {
      this.model.addShape(new Rectangle(name, ShapeType.RECTANGLE, new ColorInfo(
              red, green, blue), new Position2D(lx, ly), startOfLife,
              endOfLife, width, height, layer));
      return this;
    }


    @Override
    public TweenModelBuilder<ModelOperations> addMove(
            String name, float moveFromX, float moveFromY, float moveToX,
            float moveToY, int startTime, int endTime) {
      this.model.addAnimation(new MoveAnimation(AnimationType.MOVE, name, startTime,
              endTime, new Position2D(moveFromX, moveFromY),
              new Position2D(moveToX, moveToY)));
      return this;
    }

    @Override
    public TweenModelBuilder<ModelOperations> addColorChange(
            String name, float oldR, float oldG, float oldB, float newR,
            float newG, float newB, int startTime, int endTime) {
      this.model.addAnimation(new ChangeColorAnimation(AnimationType.COLORCHANGE, name, startTime,
              endTime, new ColorInfo(oldR, oldG, oldB),
              new ColorInfo(newR, newG, newB)));
      return this;
    }

    @Override
    public TweenModelBuilder<ModelOperations> addScaleToChange(
            String name, float fromSx, float fromSy, float toSx,
            float toSy, int startTime, int endTime) {
      ShapeType type = null;
      ArrayList<AbstractShape> listOfShapes = new ArrayList<>(model.getShapesInfor());
      for (int i = 0; i < listOfShapes.size(); i++) {
        if (listOfShapes.get(i).getName().equals(name)) {
          type = listOfShapes.get(i).getType();
        }
      }
      ArrayList<Float> from  = new ArrayList<>();
      ArrayList<Float> to = new ArrayList<>();
      from.add(fromSx);
      from.add(fromSy);
      to.add(toSx);
      to.add(toSy);
      this.model.addAnimation(new ChangeScaleAnimation(AnimationType.SCALECHANGE,
              name, type, startTime, endTime, from , to));
      return this;
    }

    @Override
    public ModelOperations build() {
      return new BasicModel(this);
    }

  }

  /**
   * Constructor one BasicModel class and fields provided by builder.
   *
   * @param builder thee given builder of the BasicModel class
   */
  private BasicModel(Builder builder) {
    this.listOfShapes = builder.model.getShapesInfor();
    this.mapAnimations = builder.model.getAnimationInfor();
    this.timeUse = builder.model.getTimeUseInfor();
  }


  @Override
  public ArrayList<AbstractShape> getShapesInfor() {
    return new ArrayList<>(listOfShapes);
  }

  @Override
  public HashMap<Integer, ArrayList<AbstractAnimation>> getAnimationInfor() {
    return new HashMap<>(mapAnimations);
  }

  @Override
  public ArrayList<Integer> getTimeUseInfor() {
    return new ArrayList<>(timeUse);
  }

  @Override
  public void addShape(AbstractShape addShape) {
    boolean check = true;
    for (int i = 0; i < listOfShapes.size(); i++ ) {
      if (listOfShapes.get(i).getName().equals(addShape.getName())) {
        check = false;
      }
    }
    if (check) {
      //if the check is true, this shape does not have repeated name
      listOfShapes.add(addShape);
    } else {
      throw new IllegalArgumentException("Invalid repeating name shape, cannot add shape");
    }
  }

  @Override
  public void deleteShape(String shapeName) {
    boolean check = false;
    int needDelete = 0;
    for (int i = 0; i < listOfShapes.size(); i++ ) {
      if (listOfShapes.get(i).getName().equals(shapeName)) {
        needDelete = i;
        check = true;
      }
    }
    if (check) {
      // check is true means the deletion can be done now because we find it
      listOfShapes.remove(needDelete);
    } else {
      // check is false means the deletion is invalid because we cannot found it
      throw new IllegalArgumentException("Invalid delete shape, cannot find it");
    }
  }

  @Override
  public void addAnimation(AbstractAnimation animation) {
    boolean check = false;
    for (int i = 0; i < listOfShapes.size(); i++) {
      AbstractShape want = listOfShapes.get(i);
      if (want.getName().equals(animation.getShapeName())) {
        if (this.checkValidAnimation(animation, want.getAppear(), want.getDisappear())) {
          // when this animation is valid, we can add it
          this.addInOrder(animation);
        } else {
          throw new IllegalArgumentException("Invalid Animation Adding");
        }
        //we can find the suitable shape name for this animation
        check = true;
      }
    }
    if (!check) {
      throw new IllegalArgumentException("Can not find this shape to do animation"
              + " by given animation");
    }
  }

  /**
   * Determine if this animation command can be added to the mapAnimations,
   * check the time interval of given animation is valid for other existing animations that have
   * same shape name and same animation type, which means not existing overlapping time interval
   * with them. Also, the time interval of given animation should be in the time interval of
   * shape that given animation want to operate.
   *
   * @param animation the given animation to check
   * @param appears the given appear time of shape that animation operates
   * @param disappears the given disappear time of shape that animation operates
   * @return if animation is valid for adding, return true, if not, return false
   */
  private boolean checkValidAnimation(AbstractAnimation animation, int appears, int disappears) {
    boolean check = true;
    if (animation.getStartTime() < appears || animation.getEndTime() > disappears ) {
      return  false;
    }
    for (int k = 0; k < timeUse.size(); k++ ) {
      ArrayList<AbstractAnimation> want = mapAnimations.get(timeUse.get(k));
      for (int j = 0; j < want.size(); j++) {
        AbstractAnimation checkOne = want.get(j);
        // when finding one animation has same shape change name and animation type with
        // the given animation, we need to consider the overlapping problems.
        if (animation.getShapeName().equals(checkOne.getShapeName())
                && animation.getAnimationType() == checkOne.getAnimationType()) {
          // correct and not overlap are only when given's startTime happen after
          // checkOne' endTime or given's endTime happen before checkOne's startTime
          if (!(animation.getStartTime() > checkOne.getEndTime()
                  || animation.getEndTime() < checkOne.getStartTime())) {
            check = false;
          }
        }
      }
    }
    return check;
  }

  /**
   * Add the animation to the specific time (key of mapAnimations) of the model
   * according its start time.
   *
   * @param animation the animation we want to add to the specific time in the mapAnimations
   */
  private void addInOrder(AbstractAnimation animation) {
    if (mapAnimations.get(animation.getStartTime()) == null) {
      // if this start time did not exist in map, add one list of animations
      // which contains given animation to this start time
      ArrayList<AbstractAnimation> listAnimation = new ArrayList<>();
      listAnimation.add(animation);
      mapAnimations.put(animation.getStartTime(), listAnimation);
      timeUse.add(animation.getStartTime());
    } else {
      //if existed , add it directly to this start time arrayList
      mapAnimations.get(animation.getStartTime()).add(animation);
    }
  }


}
