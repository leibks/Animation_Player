package cs3500.animator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.ViewInteractive;

/**
 * A Controller class that is a bare bones controller of a video representing an animation,
 * it only control  the hybrid view for user to do some operations for the visual view
 * and svg view.
 */
public class InteractiveAnimatorController implements ControllerOperations, ActionListener {
  private ViewInteractive view;
  private boolean hasStart;

  /**
   * Constructor one InteractiveAnimatorController class that control the hybrid views that
   * has visual view, svg functionality and functionality.
   *
   * @param view the given interactive view for controller
   */
  public InteractiveAnimatorController(ViewInteractive view) {
    this.view = view;
    view.setListeners(this);
    view.showView();
    this.hasStart = false;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Start Animation":
        if (!hasStart) {
          view.startAnimation();
          hasStart = true;
        } else {
          view.restart();
        }
        break;
      case "Pause OR Resume Animation":
        view.pauseOrResumerPanel();
        break;
      case "Restart Animation":
        view.restart();
        break;
      case "Enable/disable looping":
        view.setLoop();
        break;
      case "Increase Speed of Animation":
        view.setSpeed(view.getSpeed() + 1);
        break;
      case "Decrease Speed of Animation":
        view.setSpeed(view.getSpeed() - 1);
        break;
      case "Output SVG":
        view.getSVGFile();
        break;
      case "Color Chooser":
        Color col = JColorChooser.showDialog((InteractiveView) (this.view),
                "Choose a color", view.getBackgroundColor());
        view.setBackgroundColor(col);
        break;
      default: throw new IllegalArgumentException("Invalid Command");
    }
  }


}
