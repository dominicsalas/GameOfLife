package common;

/**
 * Created by dsalas on 9/26/16.
 */
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

class Loop extends AnimationTimer
{
  private static final double RATE = 0.1;
  private Group group;

  Loop(Group g)
  {
    super();
    group = g;
  }

  @Override
  public void handle(long now)
  {
    //double angle = 360.0*now*RATE/1e9;
    double angle = 90*now*RATE/1e9;

    //group.setRotationAxis();
    //group.getTransforms().add(new Rotate(45, 0, 20, 0));
    group.setRotate(angle);
  }
}
