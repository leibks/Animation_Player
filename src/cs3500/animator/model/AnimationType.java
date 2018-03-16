package cs3500.animator.model;

/**
 * The type of different kinds of animations, now it just include three kinds of animations:
 * Move: move the shape from one position to another position during time interval
 * ChangeColor:  change the color of shape from one color to another color during time interval
 * ChangeScale: change the scale of shape from some values to other values during time interval.
 */
public enum AnimationType {
  MOVE, COLORCHANGE, SCALECHANGE;

}
