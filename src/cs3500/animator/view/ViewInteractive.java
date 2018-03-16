package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public interface ViewInteractive extends IViews {

  /**
   * Get the tempo (speed of view) in the view, which helped user to change the tempo.
   *
   * @return the tempo that represented how many tick per second, speed of view
   */
  double getSpeed();

  /**
   * Pause or continue the current animation panel.
   */
  void pauseOrResumerPanel();

  /**
   * Rewind to beginning and start of the animation panel.
   */
  void restart();

  /**
   * Control the animation panel to start animation.
   */
  void startAnimation();

  /**
   * Set the listener for any actions.
   *
   * @param clicks the given click of the action listener
   */
  void setListeners(ActionListener clicks);

  /**
   * Exporting the animation that you see (i.e. with only selected shapes,
   * with or without looping) to an SVG file named by the user.
   */
  void getSVGFile();

  /**
   * Gets if this loop has been set or not.
   */
  boolean getLoopSet();

  /**
   * Gets all the checkBox field within the view to see which shapes have been selected or not.
   *
   * @return the JCheckbox that represents the chosen shapes information
   */
  JCheckBox[] getCheckBoxes();

  /**
   * Get the background color.
   * @return the Color the background is
   */
  Color getBackgroundColor();

  /**
   * Set the background color.
   * @param col the given color we want the background to be set
   */
  void setBackgroundColor(Color col);

}
