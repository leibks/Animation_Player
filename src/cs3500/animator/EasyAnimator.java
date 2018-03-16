package cs3500.animator;

import java.io.FileNotFoundException;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import cs3500.animator.controller.BasicViewController;
import cs3500.animator.controller.ControllerOperations;
import cs3500.animator.controller.InteractiveAnimatorController;
import cs3500.animator.model.BasicModel;
import cs3500.animator.model.ModelOperations;
import cs3500.animator.view.AnimationFileReader;
import cs3500.animator.view.IViews;
import cs3500.animator.view.InteractiveView;
import cs3500.animator.view.ViewCreator;

/**
 * Main class from which the view is run from.
 */
public class EasyAnimator {

  /**
   * in the main method for EasyAnimator, takes in an input file with the animation to present, and
   * then takes in a type of view to present. Afterwards if the view requires an output file, it
   * routs all the information necessary to make the given animation to that output file. It also
   * takes a parameter that can change the speed of the animation.
   *
   * @param args - arguments for the program
   * @throws IllegalArgumentException if the given parameters form a malformed animation
   */
  public static void main(String[] args) {
    JPanel panel = new JPanel();
    boolean needFile = false;
    String inputFile = "";
    boolean needViewName = false;
    String viewType = "";
    boolean needWhereOut = false;
    String outputName = "";
    boolean needSpeed = false;
    int speed = 1;
    for (String arg : args) {
      if (arg.equals("-if")) {
        needFile = true;
      } else if (arg.equals("-iv")) {
        needViewName = true;
      } else if (arg.equals("-o")) {
        needWhereOut = true;
      } else if (arg.equals("-speed")) {
        needSpeed = true;
      } else if (needFile) {
        inputFile = arg;
        needFile = false;
      } else if (needViewName) {
        viewType = arg;
        needViewName = false;
      } else if (needSpeed) {
        try {
          speed = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
          throw new NumberFormatException("given string is not an int for speed");
        }
        needSpeed = false;
      } else if (needWhereOut) {
        outputName = arg;
        needSpeed = false;
      } else {
        JOptionPane.showMessageDialog(panel, "Could find valid input format",
                "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    if (!inputFile.equals("") && !viewType.equals("")) {
      AnimationFileReader reader = new AnimationFileReader();
      ModelOperations model = null;
      try {
        model = reader.readFile(inputFile, new BasicModel.Builder());

      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(panel,
                "FileNotFoundException: Could not find file",
                "Error", JOptionPane.ERROR_MESSAGE);
      }

        IViews view = new ViewCreator(model, speed).create(viewType);
        if (viewType.equals("interactive")) {
          try {
            ControllerOperations controller = new InteractiveAnimatorController(
                    (InteractiveView) view);
          } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panel,
                    "Error interactive Controller to run",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
          }
        } else {
          try {
            ControllerOperations controller2 = new BasicViewController(
                    view, viewType, outputName);
          } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panel, "Error basic Controller to run",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
          }
        }
    } else {
      JOptionPane.showMessageDialog(panel, "Could find valid input format",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }


}
