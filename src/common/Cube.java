package common;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.Random;

/**
 * Dominic Salas
 *
 * Cube class. Stores all of the information for each cube within the overall
 * grid.
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
  private PhongMaterial cubeMaterial;

  /**
   * Constructor for setting random cubes
   * @param size
   */
  public Cube(int size)
  {
    super(size, size, size);
    cubeMaterial = new PhongMaterial();
    Random rand = new Random();
    this.size = size;
    int number = rand.nextInt(2);
    if (number == 0)
    {
      this.status = 0;
      this.cellAlive();
    }
    else
    {
      this.status = 1;
      this.killCell();
    }

    this.neighbors = new ArrayList<>();
  }

  /**
   * Returns status or state of the cube
   * @return status
   */
  public int getStatus()
  {
    return status;
  }

  /**
   * Sets status or state of the cube
   * @param status
   */
  public void setStatus(int status)
  {
    this.status = status;
  }

  /**
   * Updates the cell with the latest information
   */
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
        this.transitionSize -= size * 0.01;
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
        this.transitionSize += size * 0.01;
        this.cellComingAlive();
      }
    }
  }

  /**
   * Kills off cell but updating status and making not visible.
   */
  public void killCell()
  {
    this.status = 1;
    this.setVisible(false);
  }

  /**
   * Sets cell to come alive
   */
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

  /**
   * Updates cells setting to dying
   */
  public void cellDying()
  {
    this.depthProperty().setValue(transitionSize);
    this.heightProperty().setValue(transitionSize);
    this.widthProperty().setValue(transitionSize);
    cubeMaterial.setDiffuseColor(Color.CORAL);
    cubeMaterial.setSpecularColor(Color.RED);
    this.setMaterial(cubeMaterial);
  }

  /**
   * Updates cells info to coming alive
   */
  public void cellComingAlive()
  {
    this.depthProperty().setValue(transitionSize);
    this.heightProperty().setValue(transitionSize);
    this.widthProperty().setValue(transitionSize);
    cubeMaterial.setDiffuseColor(Color.LIGHTGREEN);
    cubeMaterial.setSpecularColor(Color.GREEN);
    this.setMaterial(cubeMaterial);
  }

  /**
   * Sets coordinates for the cell. Only needed in the begining.
   * @param x coordinate
   * @param y coordinate
   * @param z coordinate
   */
  public void setCoordinates(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
    this.setTranslateX(x);
    this.setTranslateY(y);
    this.setTranslateZ(z);
  }

  /**
   * Calculates the number of neighbors
   */
  public void calculateNeighbors()
  {
    neighborsAlive = 0;
    neighborsDead = 0;

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

  /**
   * Gets the neighbors that are alive
   * @return number of neighbors alive
   */
  public int getNeighborsAlive()
  {
    return this.neighborsAlive;
  }

  /**
   * Sets new size of cell
   * @param transitionSize
   */
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
