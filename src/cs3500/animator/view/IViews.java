package cs3500.animator.view;

/**
 * The view interface. To motivate the methods to show
 * all views about operations in the model.
 */
public interface IViews {

  /**
   * Visually presents the given view in its provided form.
   */
  void showView();

  /**
   * Gets the textual output of an IView animation video.
   *
   * @return returns an Writeable with the textual result of the view.
   */
  Appendable getOutput();

  /**
   * Change the speed of the animation by user.
   *
   * @param tempo how many ticks per second, higher tempo means higher speed
   */
  void setSpeed(double tempo);

  /**
   * Find the type of view and help controller to control them by using different functionality.
   *
   * @return the string that represented the type of view
   */
  String getViewType();

  /**
   * Enable/Disable looping and cause the animation to automatically loopback.
   */
  void setLoop();

}