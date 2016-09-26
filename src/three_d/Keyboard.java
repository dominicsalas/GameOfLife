package three_d;

import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.input.KeyEvent;

/**
 * Created by Theodore (Alex) Evans on 9/17/16.
 * The keyboard handler for a 3d test
 */
class Keyboard implements EventHandler<KeyEvent>
{
  private static final double RATE = 3.0;

  private Camera camera;
  private double x, z, degrees, radians;

  Keyboard(Camera c)
  {
    camera = c;
    x = 0.0;
    z = -100.0;
    degrees = 0.0;
    radians = 0.0;
    camera.setTranslateX(x);
    camera.setTranslateZ(z);
    camera.setRotate(degrees);
  }

  @Override
  public void handle(KeyEvent event)
  {
    switch (event.getCode())
    {
      case A:
        z = z+Math.cos(radians-Math.PI/2)*RATE;
        x = x+Math.sin(radians-Math.PI/2)*RATE;
        break;
      case D:
        z = z+Math.cos(radians+Math.PI/2)*RATE;
        x = x+Math.sin(radians+Math.PI/2)*RATE;
        break;
      case W:
        z = z+Math.cos(radians)*RATE;
        x = x+Math.sin(radians)*RATE;
        break;
      case S:
        z = z+Math.cos(radians+Math.PI)*RATE;
        x = x+Math.sin(radians+Math.PI)*RATE;
        break;
      case Z:
        x = 0.0;
        z = -100.0;
        degrees = 0.0;
        break;
      case RIGHT:
        degrees += RATE;
        break;
      case LEFT:
        degrees -= RATE;
        break;
    }
    if (degrees>=360.0) degrees -= 360.0;
    if (degrees<0.0) degrees += 360.0;
    radians = degrees*Math.PI/180.0;
    camera.setTranslateX(x);
    camera.setTranslateZ(z);
    camera.setRotate(degrees);
  }
}
