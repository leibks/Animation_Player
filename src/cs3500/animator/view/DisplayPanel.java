package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.ShapeType;

public class DisplayPanel extends JPanel {
  protected ArrayList<AbstractShape> listOfShapes;
  protected int nowTick;

  /**
   * Constructor one animation panel class and make shapes can do animation and show on this panel.
   *
   * @param model the model that contains shapes and animations we want to
   *              show in panel, only read
   * @param tempo the tempo represents how tick per second in the world (animation panel)
   */
  public DisplayPanel(ReadOnlyModel model, double tempo) {
    this.listOfShapes = model.getShapesInfor();
    this.nowTick = 0;
    this.setBackground(Color.WHITE);

  }

  /**
   * Set the shapes to show.
   * @param list the given new list of shapes to show
   */
  public void setListOfShapes(ArrayList<AbstractShape> list) {
    listOfShapes = new ArrayList<>(list);
  }

  /**
   * Every time, when it was called by hybrid view, it will automatically plus one
   */
  public void runTick() {
    this.nowTick++;
  }

  /**
   * Set current tick.
   * @param nowTick the given tick
   */
  public void setNowTick(int nowTick) {
    this.nowTick = nowTick;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLACK);

    // show current shapes
    for (int i = 0; i < listOfShapes.size(); i++) {
      AbstractShape shape = listOfShapes.get(i);
      if (shape.getDisappear() >= nowTick && shape.getAppear() <= nowTick
              && shape.getChosen()) {
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
