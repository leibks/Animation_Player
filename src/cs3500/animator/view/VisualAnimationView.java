package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import cs3500.animator.model.ReadOnlyModel;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * a view for the Animation generator application, which when given
 * an input file an output file, and a tempo of speed for an application,
 * produces a video which displays an animation.
 */
public class VisualAnimationView extends JFrame implements IViews {
  private AnimationPanel animationPanel;

  /**
   * Constructs a VisualAnimationView view object which displays an Animation
   * on screen.
   *
   * @param model the given model is the read only model to show a visual example
   * @param tempo the numbers of ticks per second
   */
  public VisualAnimationView(ReadOnlyModel model, int tempo) {
    super();
    this.setTitle("Animation!");
    this.setSize(1200,1000);
    setLocation(500, 10);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    animationPanel = new AnimationPanel(model, tempo);
    animationPanel.setPreferredSize(new Dimension(1000,800));

    // use scroll bars to ensure that the user can see different parts.
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(300, 300));
    this.add(scrollPane, BorderLayout.CENTER);

  }

  @Override
  public Appendable getOutput() {
    throw new UnsupportedOperationException("Animation View Cannot Get Output");
  }

  @Override
  public void setSpeed(double tempo) {
    animationPanel.setSpeed(tempo);
  }

  @Override
  public String getViewType() {
    return "Visual View";
  }

  @Override
  public void setLoop() {
    throw new UnsupportedOperationException("This Visual Animation View Cannot Loop");
  }

  @Override
  public void showView() {
    animationPanel.startAnimation();
    setVisible(true);
  }
}
