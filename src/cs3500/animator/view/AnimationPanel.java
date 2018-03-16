package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.Timer;

import cs3500.animator.model.AbstractAnimation;
import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.ShapeType;

/**
 * This one animation panel class and extends the JPanel. It represents the region where the
 * shapes and animations shows in the model will show graph on this panel. Furthermore,
 * we make is implement ActionListener and make it able to do timer after every delay time.
 */
public class AnimationPanel extends JPanel implements ActionListener {
  protected ArrayList<AbstractShape> listOfShapes;
  private final HashMap<Integer, ArrayList<AbstractAnimation>> listOfAnimation;
  private ArrayList<Integer> useTime;
  private ArrayList<AbstractAnimation> listCurrentDo;
  private HashMap<String, AbstractShape> listCurrentShapes;
  private ArrayList<String> listNamesOfShapes;
  protected int nowTick;
  protected Timer timer;
  private double tempo;

  /**
   * Constructor one animation panel class and make shapes can do animation and show on this panel.
   *
   * @param model the model that contains shapes and animations we want to
   *              show in panel, only read
   * @param tempo the tempo represents how tick per second in the world (animation panel)
   */
  public AnimationPanel(ReadOnlyModel model, double tempo) {
    this.listOfShapes = model.getShapesInfor();
    this.listOfAnimation = model.getAnimationInfor();
    this.useTime = model.getTimeUseInfor();
    this.listCurrentDo = new ArrayList<>();
    this.setAnimation();
    this.listCurrentShapes = new HashMap<>();
    this.nowTick = - (int) tempo;
    this.tempo =  tempo;
    this.timer = new Timer( (int)((1.0 / tempo) * 1000), this);
    this.setBackground(Color.WHITE);
    listNamesOfShapes = new ArrayList<>();

  }

  /**
   * Provide list of shapes now we need to do animations.
   *
   * @return arrayList of shapes we need to show
   */
  public ArrayList<AbstractShape> getNowShapes() {
    return new ArrayList<>(listOfShapes);
  }

  /**
   * Get the the now tick (world time).
   *
   * @return the current tick of this panel under the timer run
   */
  public int getNowTick( ) {
    Integer result = nowTick;
    return result;
  }

  /**
   * Set the animation commands to the arrayList.
   */
  private void setAnimation() {
    Collections.sort(useTime);
    for (int k = 0; k < useTime.size(); k++ ) {
      ArrayList<AbstractAnimation> want = listOfAnimation.get(useTime.get(k));
      for (int j = 0; j < want.size(); j++) {
        listCurrentDo.add(want.get(j));
      }
    }
  }

  /**
   * Set the current list animations command should do, shapes we want to do animations
   * and list of shape names.
   */
  protected void setCurrentDo() {
    this.listNamesOfShapes = new ArrayList<>();
    this.listCurrentShapes = new HashMap<>();

    for (int i = 0; i < listOfShapes.size(); i++ ) {
      AbstractShape shape = listOfShapes.get(i);
      listCurrentShapes.put(shape.getName(), shape);
      listNamesOfShapes.add(shape.getName());
    }
  }

  /**
   * Provide information for user about speed of this animation panel.
   *
   * @return the tempo in the animation panel
   */
  public double getSpeed() {
    double result = this.tempo;
    return result;
  }

  /**
   * Change hte speed of the animation panel according to the given tempo.
   *
   * @param tempoGiven the given tempo we want to change to
   */
  public void setSpeed(double tempoGiven) {
    if (tempoGiven >= 1) {
      this.timer.setDelay((int) ((1000 / tempoGiven)));
      this.tempo = tempoGiven;
    }
  }

  /**
   * Start the timer in the animation panel.
   */
  public void startAnimation() {
    this.setCurrentDo();
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // now the nowTick is the new tick for animation to do
    nowTick = nowTick + 1;
    // do the current list of animations
    for (int i = 0; i < listCurrentDo.size(); i++) {
      AbstractAnimation nowDo = listCurrentDo.get(i);
      if (nowDo.getEndTime() >= nowTick && nowDo.getStartTime() <= nowTick) {
        if (listCurrentShapes.containsKey(nowDo.getShapeName())) {
          listCurrentShapes.replace(nowDo.getShapeName(),
                  listCurrentShapes.get(nowDo.getShapeName()),
                  nowDo.doAnimation(listCurrentShapes.get(nowDo.getShapeName()), nowTick));
        }
      }
    }
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);

    // show current shapes
    for (int i = 0; i < listNamesOfShapes.size(); i++) {
      AbstractShape shape = listCurrentShapes.get(listNamesOfShapes.get(i));
      if (shape.getDisappear() >= nowTick && shape.getAppear() <= nowTick) {
        g2d.setColor(new Color(shape.getColor().getRed(),
                shape.getColor().getGreen(), shape.getColor().getBlue()));
        if (shape.getType() == ShapeType.OVAL) {
          g2d.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getWidth() , (int) shape.getHeight() );
        } else if (shape.getType() == ShapeType.RECTANGLE) {
          g2d.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getWidth(), (int) shape.getHeight());
        }
      }
    }
  }
}


