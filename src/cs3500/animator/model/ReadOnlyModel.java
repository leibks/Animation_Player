package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class of read only model, although it also implements the ModelOperation interface,
 * it only has function to provide copy information of the model, and any methods that modify
 * the model will be throw UnsupportedOperationException. The class protect given BasicModel avoid
 * for modifying by others.
 */
public class ReadOnlyModel implements ModelOperations {
  private ModelOperations delegate;

  /**
   * Constructor one ReadOnlyModel and only can be read by views or others.
   *
   * @param delegate the Basic model that is only used for providing information
   */
  public ReadOnlyModel(ModelOperations delegate) {
    this.delegate = delegate;
  }

  @Override
  public ArrayList<AbstractShape> getShapesInfor() {
    return delegate.getShapesInfor();
  }

  @Override
  public HashMap<Integer, ArrayList<AbstractAnimation>> getAnimationInfor() {
    return delegate.getAnimationInfor();
  }

  @Override
  public ArrayList<Integer> getTimeUseInfor() {
    return delegate.getTimeUseInfor();
  }

  @Override
  public void addShape(AbstractShape addShape) {
    throw new UnsupportedOperationException("You cannot modify the model "
            + "by adding shapes, this is read only model");
  }

  @Override
  public void deleteShape(String shapeName) {
    throw new UnsupportedOperationException("You cannot modify the model"
            + "by deleting shapes, this is read only model");
  }

  @Override
  public void addAnimation(AbstractAnimation animation) {
    throw new UnsupportedOperationException("You cannot modify the model"
            + "by adding animation command, this is read only model");
  }

}
