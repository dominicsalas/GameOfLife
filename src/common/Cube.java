package common;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by dsalas on 9/24/16.
 */
public class Cube extends Box
{
  // 0 - Alive
  // 1 - Dead
  // 2 - Alive -> Dead
  // 3 - Dead -> Alive
  private int status, size;
  private float transitionSize;
  private int x, y, z; // Coordinates
  private int neighborsAlive, neighborsDead;
  ArrayList<Cube> neighbors;
  private final PhongMaterial cubeMaterial = new PhongMaterial();

  public Cube(Cube cell)
  {
    super(cell.getSize(), cell.getSize(), cell.getSize());
    this.status = cell.getStatus();
    this.size = cell.getSize();
    this.neighbors = cell.getNeighbors();
    this.transitionSize = cell.getTransitionSize();
    this.neighborsAlive = cell.getNeighborsAlive();
    this.neighborsDead = cell.getNeighborsDead();
    this.setCoordinates(cell.getX(), cell.getY(), cell.getZ());
    this.setMaterial(cell.getMaterial());
  }

  public Cube(int size, int status, ArrayList<Cube> neighbors, PhongMaterial material)
  {
    super(size, size, size);
    this.status = status;
    this.size = size;
    this.neighbors = neighbors;
    this.setMaterial(material);
  }

  /**
   * Constructor for setting random cubes
   * @param size
   * @param random
   */
  public Cube(int size, boolean random)
  {
    super(size, size, size);
    Random rand = new Random();
    int number = rand.nextInt(2);
    if (number == 0)
    {
      this.status = 0;
      //this.cellAlive();
      cubeMaterial.setDiffuseColor(Color.LIGHTGREEN);
      cubeMaterial.setSpecularColor(Color.GREEN);
      this.setMaterial(cubeMaterial);
    }
    else
    {
      this.status = 1;
      this.setVisible(false);
    }

    this.size = size;
    this.neighbors = new ArrayList<>();
  }

  public int getStatus()
  {
    return status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public void updateCell()
  {
    // Cell is dying
    if (this.status == 2)
    {
      // Cell has died
      if (transitionSize < 1)
      {
        this.killCell();
      }
      // Still dying
      else
      {
        this.transitionSize -= size * 0.09;
        this.cellDying();
      }
    }
    // Cell is coming alive
    else if(this.status == 3)
    {
      // Cell is alive
      if (this.transitionSize >= size)
      {
        this.cellAlive();
      }
      // Still coming alive
      else
      {
        this.transitionSize += size * 0.09;
        this.cellComingAlive();
      }
    }
  }

  public void killCell()
  {
    this.status = 1;
    this.setVisible(false);
  }

  public void cellAlive()
  {
    this.status = 0;
    this.depthProperty().setValue(size);
    this.heightProperty().setValue(size);
    this.widthProperty().setValue(size);
    cubeMaterial.setDiffuseColor(Color.LIGHTGREEN);
    cubeMaterial.setSpecularColor(Color.GREEN);
    this.setMaterial(cubeMaterial);
  }

  public void cellDying()
  {
    this.depthProperty().setValue(transitionSize);
    this.heightProperty().setValue(transitionSize);
    this.widthProperty().setValue(transitionSize);
    cubeMaterial.setDiffuseColor(Color.CORAL);
    cubeMaterial.setSpecularColor(Color.RED);
    this.setMaterial(cubeMaterial);
  }

  public void cellComingAlive()
  {
    this.depthProperty().setValue(transitionSize);
    this.heightProperty().setValue(transitionSize);
    this.widthProperty().setValue(transitionSize);
    cubeMaterial.setDiffuseColor(Color.LIGHTGREEN);
    cubeMaterial.setSpecularColor(Color.GREEN);
    this.setMaterial(cubeMaterial);
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

  public void calculateNeighbors()
  {
    for (Cube cube : neighbors)
    {
      if (cube.getStatus() == 0)
      {
        neighborsAlive++;
      }
      else if (cube.getStatus() == 1)
      {
        neighborsDead++;
      }
    }
  }

  public int getNeighborsAlive()
  {
    return this.neighborsAlive;
  }

  public void setTransitionSize(float transitionSize)
  {
    this.transitionSize = transitionSize;
  }

  public float getTransitionSize()
  {
    return this.transitionSize;
  }

  public int getNeighborsDead()
  {
    return this.neighborsDead;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public int getZ()
  {
    return this.y;
  }

  public int getSize()
  {
    return this.size;
  }

  public PhongMaterial getCubeMaterial()
  {
    return this.cubeMaterial;
  }

  public ArrayList<Cube> getNeighbors()
  {
    return this.neighbors;
  }

  public void setNeighbors(Cube[][][] grid, int x, int y, int z)
  {

    // Checks Z+1 and ensures is not null
    if (grid[x][y][z+1] != null)
    {
      neighbors.add(grid[x][y][z + 1]);

      // Checks Y+1 and ensures is not null
      if (grid[x][y + 1][z + 1] != null)
      {
        neighbors.add(grid[x][y + 1][z + 1]);

        // Checks Y+1 X+1 corner and ensures is not null
        if (grid[x+1][y + 1][z+1] != null)
        {
          neighbors.add(grid[x + 1][y + 1][z+1]);
        }

        // Checks Y+1 and X-1 corner and ensures is not null
        if (grid[x-1][y + 1][z+1] != null)
        {
          neighbors.add(grid[x - 1][y + 1][z+1]);
        }
      }

      // Checks Y-1 and ensures is not null
      if (grid[x][y - 1][z + 1] != null)
      {
        neighbors.add(grid[x][y - 1][z + 1]);

        // Checks Y-1 and X+1 corner and ensures is not null
        if (grid[x+1][y - 1][z+1] != null)
        {
          neighbors.add(grid[x + 1][y - 1][z+1]);
        }

        // Checks Y-1 and X-1 and corner and ensures is not null
        if (grid[x-1][y - 1][z+1] != null)
        {
          neighbors.add(grid[x - 1][y - 1][z+1]);
        }
      }

      // Checks X+1 and ensures is not null
      if (grid[x + 1][y][z + 1] != null)
      {
        neighbors.add(grid[x + 1][y][z + 1]);
      }

      // Checks X-1 and ensures is not null
      if (grid[x - 1][y][z + 1] != null)
      {
        neighbors.add(grid[x - 1][y][z + 1]);
      }
    }

    // Checks Z-1 and ensures is not null
    if (grid[x][y][z-1] != null)
    {
      neighbors.add(grid[x][y][z-1]);

      // Checks Y+1 and ensures is not null
      if (grid[x][y + 1][z-1] != null)
      {
        neighbors.add(grid[x][y + 1][z - 1]);

        // Checks Y+1 X+1 corner and ensures is not null
        if (grid[x+1][y + 1][z-1] != null)
        {
          neighbors.add(grid[x + 1][y + 1][z-1]);
        }

        // Checks Y+1 and X-1 corner and ensures is not null
        if (grid[x-1][y + 1][z-1] != null)
        {
          neighbors.add(grid[x - 1][y + 1][z-1]);
        }
      }

      // Checks Y-1 and ensures is not null
      if (grid[x][y - 1][z - 1] != null)
      {
        neighbors.add(grid[x][y - 1][z - 1]);

        // Checks Y-1 and X+1 corner and ensures is not null
        if (grid[x+1][y - 1][z-1] != null)
        {
          neighbors.add(grid[x + 1][y - 1][z-1]);
        }

        // Checks Y-1 and X-1 and corner and ensures is not null
        if (grid[x-1][y - 1][z-1] != null)
        {
          neighbors.add(grid[x - 1][y - 1][z-1]);
        }
      }

      // Checks X+1 and ensures is not null
      if (grid[x + 1][y][z - 1] != null)
      {
        neighbors.add(grid[x + 1][y][z - 1]);
      }

      // Checks X-1 and ensures is not null
      if (grid[x - 1][y][z - 1] != null)
      {
        neighbors.add(grid[x - 1][y][z - 1]);
      }
    }

    // Checks Y+1 and ensures is not null
    if (grid[x][y + 1][z] != null)
    {
      neighbors.add(grid[x][y + 1][z]);

      // Checks Y+1 X+1 corner and ensures is not null
      if (grid[x+1][y + 1][z] != null)
      {
        neighbors.add(grid[x + 1][y + 1][z]);
      }

      // Checks Y+1 and X-1 corner and ensures is not null
      if (grid[x-1][y + 1][z] != null)
      {
        neighbors.add(grid[x - 1][y + 1][z]);
      }
    }

    // Checks Y-1 and ensures is not null
    if (grid[x][y - 1][z] != null)
    {
      neighbors.add(grid[x][y - 1][z]);

      // Checks Y-1 and X+1 corner and ensures is not null
      if (grid[x+1][y - 1][z] != null)
      {
        neighbors.add(grid[x + 1][y - 1][z]);
      }

      // Checks Y-1 and X-1 and corner and ensures is not null
      if (grid[x-1][y - 1][z] != null)
      {
        neighbors.add(grid[x - 1][y - 1][z]);
      }
    }

    // Checks X+1 and ensures is not null
    if (grid[x+1][y][z] != null)
    {
      neighbors.add(grid[x+1][y][z]);
    }

    // Checks X-1 and ensures is not null
    if (grid[x-1][y][z] != null)
    {
      neighbors.add(grid[x - 1][y][z]);
    }
  }
}
