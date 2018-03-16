package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cs3500.animator.model.AbstractAnimation;
import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.BasicModel;
import cs3500.animator.model.ColorInfo;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeType;

/**
 * The interactive class and also can be called hybrid view, which interact with the user will have
 * capabilities of the visual view, in that it will actually show the animation being played.
 * It will also have capabilities of the SVG view, in that it will be able to create and export
 * the animation in SVG format. In this sense, this will be a hybrid of two existing views.
 */
public class InteractiveView extends JFrame implements ViewInteractive,
        ItemListener, ActionListener, ChangeListener {
  private ReadOnlyModel model;
  private double tempo;
  private DisplayPanel animationPanel;
  private SVGView svgView;
  // User Interface:
  private JButton startButton;
  private JButton pauseAndResumeButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private JButton outputSVGButtion;
  private JCheckBox[] checkBoxes;

  private JButton backGroundColor;
  private JLabel colorChooserDisplay;

  private AbstractShape background;

  private boolean loopOrNot;
  // Prepare Animations:
  private final HashMap<Integer, ArrayList<AbstractAnimation>> listOfAnimation;
  private ArrayList<Integer> useTime;
  private ArrayList<AbstractAnimation> listCurrentDo;

  //prepare shapes
  private ArrayList<AbstractShape> listOriginalShapes;
  private HashMap<String, AbstractShape> listCurrentShapes;

  private int nowTick;
  private Timer timer;
  private boolean stop;
  private int endTime;

  private HashMap<Integer, HashMap<String, AbstractShape>> storeShapeInformation;
  private JSlider processJSlider;


  /**
   * Constructor one interactive view for users, which is hybrid views and has visual and svg views'
   * functionality, also has its special control functionality.
   *
   * @param model the given model to execute hybrid views and other controls functionality
   * @param tempo the numbers of ticks per second
   */
  public InteractiveView(ReadOnlyModel model, double tempo) {
    super();
    this.setTitle("Animation!");
    this.setSize(1200,1000);
    setLocation(500, 10);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.model = model;
    this.tempo = tempo;
    this.svgView = new SVGView(new ReadOnlyModel(this.model), this.tempo);
    this.loopOrNot = false;

    this.nowTick = 1;
    this.timer = new Timer( (int)((1.0 / tempo) * 1000), this);
    this.stop = false;

    this.listOfAnimation = model.getAnimationInfor();
    this.useTime = model.getTimeUseInfor();
    this.listCurrentDo = new ArrayList<>();

    this.listOriginalShapes = model.getShapesInfor();
    this.orderByLayers();
    this.listCurrentShapes = new HashMap<>();
    this.endTime = this.getEndTime();
    this.storeShapeInformation = new HashMap<>();

    this.setBackground();
    this.setAnimation();
    this.storeShapeInfor();

    animationPanel = new DisplayPanel(this.model, this.tempo);
    animationPanel.setPreferredSize(new Dimension(1000,800));

    // use scroll bars to ensure that the user can see different parts.
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(300, 300));
    this.add(scrollPane, BorderLayout.CENTER);


    // choose shapes user want to do animation
    JPanel checkBoxPanel = new JPanel();
    checkBoxPanel.setLayout(new GridLayout(0, 2));
    JLabel chooseShapes = new JLabel("Choose shapes");
    checkBoxPanel.add(chooseShapes);

    JCheckBox selectAll = new JCheckBox("Check All");
    selectAll.setSelected(false);
    selectAll.setActionCommand("Select All");
    selectAll.setFont(new Font("Arial", Font.PLAIN, 15));
    selectAll.addItemListener(this);
    checkBoxPanel.add(selectAll);

    checkBoxPanel.setBorder(BorderFactory.createTitledBorder("Checkboxes"));
    ArrayList<AbstractShape> listShapes = model.getShapesInfor();
    checkBoxes = new JCheckBox[listShapes.size()];
    for (int i = 0; i < checkBoxes.length; i++) {
      checkBoxes[i] = new JCheckBox(listShapes.get(i).getName());
      checkBoxes[i].setSelected(true);
      checkBoxes[i].setActionCommand(listShapes.get(i).getName());
      checkBoxes[i].addItemListener(this);
      checkBoxPanel.add(checkBoxes[i]);
    }

    JScrollPane scrollPane2 = new JScrollPane(checkBoxPanel);
    scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane2.setPreferredSize(new Dimension(200, 1000));
    this.add(scrollPane2, BorderLayout.EAST);

    // Buttons panel
    JPanel buttons = new JPanel();
    buttons.setPreferredSize(new Dimension(50,60));
    this.add(buttons, BorderLayout.NORTH);

    startButton = new JButton("Start!");
    startButton.setMargin(new Insets(0,0,0,0));
    startButton.setFont(new Font("Arial", Font.PLAIN, 20));
    startButton.setActionCommand("Start Animation");
    startButton.setPreferredSize(new Dimension(60,40));
    buttons.add(startButton);

    pauseAndResumeButton = new JButton("Pause/Resume");
    pauseAndResumeButton.setMargin(new Insets(0,0,0,0));
    pauseAndResumeButton.setFont(new Font("Arial", Font.PLAIN, 20));
    pauseAndResumeButton.setActionCommand("Pause OR Resume Animation");
    pauseAndResumeButton.setPreferredSize(new Dimension(150,40));
    buttons.add(pauseAndResumeButton);

    restartButton = new JButton("Restart");
    restartButton.setMargin(new Insets(0,0,0,0));
    restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
    restartButton.setActionCommand("Restart Animation");
    restartButton.setPreferredSize(new Dimension(80,40));
    buttons.add(restartButton);

    loopButton = new JButton("Looping");
    loopButton.setMargin(new Insets(0,0,0,0));
    loopButton.setFont(new Font("Arial", Font.PLAIN, 20));
    loopButton.setActionCommand("Enable/disable looping");
    loopButton.setPreferredSize(new Dimension(80,40));
    buttons.add(loopButton);

    increaseSpeedButton = new JButton("Speed Up");
    increaseSpeedButton.setMargin(new Insets(0,0,0,0));
    increaseSpeedButton.setFont(new Font("Arial", Font.PLAIN, 20));
    increaseSpeedButton.setActionCommand("Increase Speed of Animation");
    increaseSpeedButton.setPreferredSize(new Dimension(150,40));
    buttons.add(increaseSpeedButton);

    decreaseSpeedButton = new JButton("Speed Down");
    decreaseSpeedButton.setMargin(new Insets(0,0,0,0));
    decreaseSpeedButton.setFont(new Font("Arial", Font.PLAIN, 20));
    decreaseSpeedButton.setActionCommand("Decrease Speed of Animation");
    decreaseSpeedButton.setPreferredSize(new Dimension(150,40));
    buttons.add(decreaseSpeedButton);

    outputSVGButtion = new JButton("Output SVG");
    outputSVGButtion.setMargin(new Insets(0,0,0,0));
    outputSVGButtion.setFont(new Font("Arial", Font.PLAIN, 20));
    outputSVGButtion.setActionCommand("Output SVG");
    outputSVGButtion.setPreferredSize(new Dimension(150,40));
    buttons.add(outputSVGButtion);

    backGroundColor = new JButton("Background Color");
    backGroundColor.setMargin(new Insets(0,0,0,0));
    backGroundColor.setFont(new Font("Arial", Font.PLAIN, 20));
    backGroundColor.setActionCommand("Color Chooser");
    backGroundColor.setPreferredSize(new Dimension(200,40));
    buttons.add(backGroundColor);

    colorChooserDisplay = new JLabel("      ");
    backGroundColor.setOpaque(true); //so that background color shows up
    colorChooserDisplay.setBackground(Color.WHITE);
    buttons.add(colorChooserDisplay);

    int max = this.getEndTime();
    //Create the slider
    processJSlider = new JSlider(JSlider.HORIZONTAL, 0, max, 0);
    processJSlider.addChangeListener(this);

    processJSlider.setValue(1);
    processJSlider.setMinorTickSpacing(2);
    processJSlider.setMajorTickSpacing(30);
    processJSlider.setPaintTicks(true);
    processJSlider.setPaintLabels(true);
    processJSlider.setSnapToTicks(true);

    this.add(processJSlider, BorderLayout.PAGE_END);

  }

  // Basic Methods:
  @Override
  public void showView() {
    setVisible(true);
  }

  @Override
  public Appendable getOutput() {
    return svgView.getOutput();
  }

  @Override
  public String getViewType() {
    return "Interactive View";
  }

  @Override
  public JCheckBox[] getCheckBoxes() {
    JCheckBox[] newReference = new JCheckBox[this.checkBoxes.length];
    for (int i = 0; i < this.checkBoxes.length; i += 1) {
      newReference[i] = checkBoxes[i];
    }
    return newReference;
  }

  @Override
  public Color getBackgroundColor() {
    return new Color(background.getColor().getRed(), background.getColor().getGreen(),
            background.getColor().getBlue());
  }

  @Override
  public void setBackgroundColor(Color col) {
    for (int j = 1; j <= this.getEndTime(); j++) {
      this.storeShapeInformation.get(j).get("backgroundS").
              setColor(new ColorInfo(
                      (float) (1.0/ 255.0) * col.getRed(),
                      (float) (1.0/ 255.0) * col.getGreen(),
                      (float) (1.0/ 255.0) * col.getBlue()));
    }
    for (int i = 0; i < listOriginalShapes.size(); i++) {
      if (listOriginalShapes.get(i).getName().equals("backgroundS")) {
        listOriginalShapes.get(i).setColor(new ColorInfo(
                (float) (1.0/ 255.0) * col.getRed(),
                (float) (1.0/ 255.0) * col.getGreen(),
                (float) (1.0/ 255.0) * col.getBlue()));
      }
    }
  }


  //Support run animation:

  /**
   * Check background exits or not, if exist just set changing color to it, if not
   * add one new background to change color.
   */
  private void setBackground() {
      background = new Rectangle("backgroundS", ShapeType.RECTANGLE,
              new ColorInfo((float) 1.0, (float)1.0, (float)1.0),
              new Position2D(0, 0),
              1, this.endTime, (float) 2000.0, (float) 1500.0);
      listOriginalShapes.add(0, background);

  }

  /**
   * Store all animation of shapes information
   */
  private void storeShapeInfor() {
    int timeRun = 0;
    while (timeRun < this.getEndTime()) {
      timeRun++;
      HashMap<String, AbstractShape> change = new HashMap<>();
      for (int i = 0; i < listCurrentDo.size(); i++) {
        AbstractAnimation nowDo = listCurrentDo.get(i);
        if (nowDo.getEndTime() >= timeRun && nowDo.getStartTime() <= timeRun) {
          listCurrentShapes.replace(nowDo.getShapeName(),
                  listCurrentShapes.get(nowDo.getShapeName()),
                  nowDo.doAnimation(listCurrentShapes.get(nowDo.getShapeName()), timeRun));
        }
      }
      storeShapeInformation.put(timeRun, new HashMap<>(listCurrentShapes));
    }
  }

  /**
   * Transfer hash map list of shapes to arrayList of shapes.
   * @param map the given one map list of shapes
   * @return the arrayList of shapes
   */
  private ArrayList<AbstractShape> transferToList(HashMap<String, AbstractShape> map) {
    ArrayList<AbstractShape> listOfShapes = new ArrayList<>();
    for (int i = 0; i < listOriginalShapes.size(); i++ ) {
      AbstractShape shape = listOriginalShapes.get(i);
      listOfShapes.add(map.get(shape.getName()));
    }
    return  listOfShapes;
  }

  /**
   * Set the animation commands to the arrayList and prepare shapes name for shape hashmap.
   */
  private void setAnimation() {
    Collections.sort(useTime);
    for (int k = 0; k < useTime.size(); k++ ) {
      ArrayList<AbstractAnimation> want = listOfAnimation.get(useTime.get(k));
      for (int j = 0; j < want.size(); j++) {
        listCurrentDo.add(want.get(j));
      }
    }
    for (int i = 0; i < listOriginalShapes.size(); i++ ) {
      AbstractShape shape = listOriginalShapes.get(i);
      listCurrentShapes.put(shape.getName(), shape);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.animationPanel.runTick();
    if (nowTick <= this.getEndTime() && nowTick != 0) {
      processJSlider.setValue(nowTick);
      this.animationPanel.setListOfShapes(this.transferToList(
              storeShapeInformation.get(nowTick)));
    }
    this.checkAndLoopBack();
    repaint();
    nowTick++;
  }

  /**
   * Checking do we need to loop back the animation Panel, if yes, loop back the animation panel.
   */
  private void checkAndLoopBack() {
    if (nowTick >= endTime && loopOrNot) {
      this.restart();
    }
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    if (source.getValueIsAdjusting() &&  nowTick <= getEndTime()) {
  //    this.pauseOrResumerPanel();
      int progress = source.getValue();
      nowTick = progress;
      this.animationPanel.setNowTick(progress);
      if (source.getValue() == 0) {
        processJSlider.setValue(1);
      }
      this.animationPanel.setListOfShapes(this.transferToList(
              storeShapeInformation.get(nowTick)));
      this.repaint();
    }
  }

  /**
   * Obtain the world end time from the list of shapes, check when is the last shape disappear.
   *
   * @return the tick in the world that represented the panel will show nothing after this tick
   */
  private int getEndTime() {
    int result = 0;
    for (int i = 0; i < listOriginalShapes .size(); i++) {
      int currentDisappearTime = listOriginalShapes.get(i).getDisappear();
      if (currentDisappearTime > result) {
        result = currentDisappearTime;
      }
    }
    return result;
  }

  /**
   * Make list of shapes order by their layers
   */
  private void orderByLayers() {
    ArrayList<AbstractShape> result = new ArrayList<>();
    int lastLayer = 0;
    HashMap<Integer, ArrayList<AbstractShape>> layerMap = new HashMap<>();
    for (int i = 0; i < listOriginalShapes.size(); i++) {
      AbstractShape shape = listOriginalShapes.get(i);
      if (lastLayer < shape.getLayer()) {
        lastLayer = shape.getLayer();
      }
      if (layerMap.containsKey(shape.getLayer())) {
        layerMap.get(shape.getLayer()).add(shape);
      } else {
        ArrayList<AbstractShape> add = new ArrayList<>();
        add.add(shape);
        layerMap.put(shape.getLayer(), add);
      }
    }
    for (int i = 0; i <= lastLayer; i++) {
      if (layerMap.containsKey(i)) {
        ArrayList<AbstractShape> list = layerMap.get(i);
        for (int j = 0; j < list.size(); j++) {
          result.add(list.get(j));
        }
      }
    }
    listOriginalShapes = result;
  }


  // Operations Start Here !!!
  @Override
  public void startAnimation() {
    this.timer.start();
  }

  @Override
  public void restart() {
    nowTick = 1;
    this.animationPanel.setNowTick(1);
    processJSlider.setValue(1);
    this.timer.restart();
  }

  @Override
  public void pauseOrResumerPanel() {
    if (this.stop) {
      this.timer.start();
      this.stop = false;
    } else {
      this.timer.stop();
      this.stop = true;
    }
  }

  @Override
  public void setSpeed(double tempoGiven) {
    if (tempoGiven >= 1) {
      this.timer.setDelay((int) ((1000 / tempoGiven)));
      this.tempo = tempoGiven;
    }
  }

  @Override
  public double getSpeed() {
    return tempo;
  }

  @Override
  public boolean getLoopSet() {
    return loopOrNot;
  }

  @Override
  public void setLoop() {
    if (this.loopOrNot) {
      this.loopOrNot = false;
    } else {
      this.loopOrNot = true;
    }
  }

  @Override
  public void setListeners(ActionListener clicks) {
    startButton.addActionListener(clicks);
    pauseAndResumeButton.addActionListener(clicks);
    restartButton.addActionListener(clicks);
    loopButton.addActionListener(clicks);
    increaseSpeedButton.addActionListener(clicks);
    decreaseSpeedButton.addActionListener(clicks);
    outputSVGButtion.addActionListener(clicks);
    backGroundColor.addActionListener(clicks);

  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    String who = ((JCheckBox) e.getItemSelectable()).getActionCommand();
    if (who.equals("Select All")) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        chooseAllItems(true);
      } else {
        chooseAllItems(false);
      }
    }
    for (int i = 0; i < listOriginalShapes.size(); i++ ) {
      if (who.equals(listOriginalShapes.get(i).getName())) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          this.listOriginalShapes.get(i).setChosen(true);
          for (int j = 1; j <= this.getEndTime(); j++) {
            this.storeShapeInformation.get(j).get(who).setChosen(true);
          }
        } else {
          this.listOriginalShapes.get(i).setChosen(false);
          for (int j = 1; j <= this.getEndTime(); j++) {
            this.storeShapeInformation.get(j).get(who).setChosen(false);
          }
        }
      }
    }
  }

  /**
   * Makes every item in currentList of shapes visible or invisible in the animationPanel,
   * based on the given boolean.
   *
   * @param check the given boolean that indicates whether or not we should choose all shapes
   */
  private void chooseAllItems(boolean check) {
    if (check) {
      for (int i = 0; i < checkBoxes.length; i++) {
        checkBoxes[i].setSelected(true);
      }
    } else {
      for (int i = 0; i < checkBoxes.length; i++) {
        checkBoxes[i].setSelected(false);
      }
    }
  }

  @Override
  public void getSVGFile() {
    ArrayList<AbstractShape> list = new ArrayList<>();
    for (int i = 0; i < listOriginalShapes.size(); i++) {
      if (listOriginalShapes.get(i).getChosen()) {
        list.add(listOriginalShapes.get(i));
      }
    }
    this.svgView = new SVGView(new ReadOnlyModel(new BasicModel(
            new ArrayList<>(list),
            listOfAnimation, model.getTimeUseInfor())), this.tempo);
    svgView.prepareLoop(this.getEndTime(), loopOrNot);
    StringWriter x = new StringWriter();
    JPanel panel = new JPanel();
    try {
      x.append(svgView.getOutput().toString());
      JFileChooser fchooser = new JFileChooser(".");
      int retvalue = fchooser.showSaveDialog(InteractiveView.this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        File svgFile = new File(f.getAbsolutePath() + ".svg");
        BufferedWriter fileToWriteTo = new BufferedWriter(new FileWriter(svgFile));
        fileToWriteTo.write(x.toString());
        fileToWriteTo.close();
      }
      return;
    }
    catch (IOException e) {
      JOptionPane.showMessageDialog(panel, "IOException: Could not writefile",
              "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
  }
}
