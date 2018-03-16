//package cs3500.animator.view;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.util.ArrayList;
//
//import cs3500.animator.model.AbstractShape;
//import cs3500.animator.model.ReadOnlyModel;
//
///**
// * This is one animationPanel that will be used in the interactiveView and help user to
// * Start, pause, resume, and restart game, or have other functionality.
// */
//public class InteractiveAnimationPanel extends AnimationPanel {
//  private int endTime;
//  private boolean doLoop;
//  private double originalTempo;
//  private boolean stop;
//
//  /**
//   * Constructor one animation panel class and make shapes can do animation and show on this panel.
//   *
//   * @param model the model that contains shapes and animations we want to show in panel
//   * @param tempo the tempo represents how tick per second in the world (animation panel)
//   * @param endTime the end time for now last shape disappear time
//   */
//
//  public InteractiveAnimationPanel(ReadOnlyModel model, double tempo, int endTime) {
//    super(model, tempo);
//    this.endTime = endTime;
//    this.doLoop = false;
//    this.originalTempo = tempo;
//    this.stop = false;
//  }
//
//  /**
//   * Get the pause or run information of the panel for testing.
//   *
//   * @return the boolean that decide stop animation or not
//   */
//  public boolean getStop() {
//    return stop;
//  }
//
//  /**|
//   * Start the Interactive Animation panel by giving the list of shapes user want to see.
//   *
//   * @param list the chosen list of shapes to show animations
//   */
//  public void startAnimation(ArrayList<AbstractShape> list) {
//    listOfShapes = new ArrayList<>(list);
//    super.startAnimation();
//  }
//
//  /**
//   * Rewind to beginning and start of the animation panel.
//   *
//   * @param list the chosen list of shapes to show animations
//   */
//  public void restart(ArrayList<AbstractShape> list) {
//    this.listOfShapes = new ArrayList<>(list);
//    this.setCurrentDo();
//    this.timer.restart();
//    this.nowTick = - (int)this.originalTempo;
//    this.setBackground(Color.WHITE);
//  }
//
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    super.actionPerformed(e);
//    this.checkAndLoopBack();
//  }
//
//  /**
//   * Pause or continue the current animation panel.
//   */
//  public void pauseOrResumerPanel() {
//    if (this.stop) {
//      this.timer.start();
//      this.stop = false;
//    } else {
//      this.timer.stop();
//      this.stop = true;
//    }
//  }
//
//  /**
//   * Enable/Disable looping and cause the animation to automatically loopback.
//   */
//  public void chooseDoLoop() {
//    if (this.doLoop) {
//      this.doLoop = false;
//    } else {
//      this.doLoop = true;
//    }
//  }
//
//  /**
//   * Checking do we need to loop back the animation Panel, if yes, loop back the animation panel.
//   */
//  private void checkAndLoopBack() {
//    if (nowTick >= endTime && doLoop) {
//      this.restart(listOfShapes);
//    }
//  }
//}
