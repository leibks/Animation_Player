package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is an interface of my animation model. The model support various
 * kinds of 2D shapes and can add various kinds of animations to shapes.
 */
public interface ModelOperations {

  /**
   * Transfer the shapes information in the model and for convenient to show in view, and
   * make user can know the shapes situations of model.
   *
   * @return ArrayList of Abstract shapes that contains all shapes information in the model
   */
  ArrayList<AbstractShape> getShapesInfor();

  /**
   * Transfer the animations information in the model and for convenient to show in view, and
   * make user can know the animations situations of model.
   *
   * @return HashMap that key is integer represents the time of the world, one list of animations
   *          is the value that represents all animations starts from this time
   */
  HashMap<Integer, ArrayList<AbstractAnimation>> getAnimationInfor();

  /**
   * Transfer the time use information in the model and for convenient to show in view.
   *
   * @return the arrayList of int represent the time we have used
   */
  ArrayList<Integer> getTimeUseInfor();

  /**
   * Add one new shape to the model for animations.
   *
   * @param addShape the given shape which is we want to add in the model
   * @throws IllegalArgumentException if name of adding shape has existed in the model
   */
  void addShape(AbstractShape addShape);

  /**
   * Delete the shape that wanted to delete by user according to the given name, because
   * the name of shape is unique.
   *
   * @param shapeName the given name of shape user wants to delete
   * @throws IllegalArgumentException if the given shape cannot found, because we cannot delete
   *                                  the shape that is not existing
   */
  void  deleteShape(String shapeName);

  /**
   * Add one new animation for one shape to the model, later model can use this adding animation
   * to do something for this shapes in the model, but the animation's shape should exit and
   * did not overlap on the time interval for same type of animation and same shape.
   *
   * @param animation the given animation we want to add in the model,
   *                     the model will store these animations that
   *                     instruct how to move or do some changes on shapes
   * @throws IllegalArgumentException if the given animation cannot be executed or overlap
   */
  void addAnimation(AbstractAnimation animation);


}
