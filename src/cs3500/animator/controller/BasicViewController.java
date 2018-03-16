package cs3500.animator.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;


import cs3500.animator.view.IViews;

/**
 * This one basic view controller, which only control the text, visual and svg view,
 * we move the easy animator part to here, and basing on the view type to show or output file.
 */
public class BasicViewController implements ControllerOperations {
  private IViews view;
  private String viewType;
  private String outputName;

  /**
   * Constructor the BasicViewController that do operations for different three views:
   * textual, svg and visual views.
   *
   * @param view the given view to do controller operations
   * @param viewType the type of the view
   * @param outputName the output file name
   */
  public BasicViewController(IViews view, String viewType, String outputName) {
    this.view = view;
    this.viewType = viewType;
    this.outputName = outputName;
    this.doCommand();
  }

  /**
   * Do the command by users, including output file or show view.
   */
  private void doCommand() {
    StringWriter x = new StringWriter();
    if (!viewType.equals("visual")) {
      try {
        x.append(view.getOutput().toString());
        if (outputName.equals("out") || outputName.equals("")) {
          System.out.println(x.toString());
          return;
        } else {
          BufferedWriter fileToWriteTo = new BufferedWriter(new FileWriter(outputName));
          fileToWriteTo.write(x.toString());
          fileToWriteTo.close();
          return;
        }
      } catch (IOException e) {
        throw new IllegalArgumentException("IOException: Could not writefile");
      }
    } else {
      view.showView();
    }
  }


}
