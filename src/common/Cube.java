package common;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.Collection;

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
  private int x, y, z; // Coordinates
  ArrayList<Cube> neighbors;

  public Cube(int size)
  {
    super(size, size, size);
    this.status = 0;
    this.size = size;
    this.neighbors = new ArrayList<>();
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
