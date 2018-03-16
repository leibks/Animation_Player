package cs3500.animator.view;

import cs3500.animator.model.ModelOperations;
import cs3500.animator.model.ReadOnlyModel;

/**
 * A Factory class that return a new instance of a view based on the given String.
 */
public class ViewCreator {
  private ModelOperations model;
  private int tempo;

  /**
   * Constructor one class that creates a new Factory class ViewCreator, with the stored
   * Model and Tempo that should go in the constructor.
   *
   * @param model the model the view is based off of.
   * @param tempo the ticks per second the model should use.
   */
  public ViewCreator(ModelOperations model, int tempo) {
    this.model = model;
    this.tempo = tempo;
  }

  /**
   * Returns a new instance of a view based on the given String.
   *
   * @param viewName the string that represents the view we are trying to return.
   * @return an instance of an IView we wanted to create.
   */
  public IViews create(String viewName) {
    ReadOnlyModel model = new ReadOnlyModel(this.model);
    switch (viewName) {
      case "text" : return new TextView(model, this.tempo);
      case "visual": return  new VisualAnimationView(model, this.tempo);
      case "svg" : return new SVGView(model, this.tempo);
      case "interactive": return new InteractiveView(model, this.tempo);
      default: throw new IllegalArgumentException("Invalid View Required to Create");
    }
  }

}
