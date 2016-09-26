package three_d;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape3D;

/**
 * Created by Theodore (Alex) Evans on 9/17/16.
 * The "game loop" for a 3d test
 */
class Loop extends AnimationTimer
{
  private static final double RATE = 0.5;
  private Shape3D shape;

  Loop(Shape3D s)
  {
    super();
    shape = s;
  }

  @Override
  public void handle(long now)
  {
    double angle = 360.0*now*RATE/1e9;

    shape.setRotate(angle);
  }
}
