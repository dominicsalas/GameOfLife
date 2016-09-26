package common;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * Created by dsalas on 9/24/16.
 */
public class Cube extends Box
{
  // 0 - Dead
  // 1 - Dying
  // 2 - Alive
  private int status, size;
  private int x, y, z; // Coordinates

  public Cube(int size)
  {
    super(size, size, size);
    this.status = 0;
    this.size = size;
    final PhongMaterial blueMaterial = new PhongMaterial();
    blueMaterial.setDiffuseColor(Color.LIGHTBLUE);
    blueMaterial.setSpecularColor(Color.BLUE);
    this.setMaterial(blueMaterial);
  }

  public int getStatus()
  {
    return status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public void setCoordinates(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
    this.setTranslateX(x);
    this.setTranslateY(y);
    this.setTranslateZ(z);
  }

  public int getSize()
  {
    return this.size;
  }
}
