package common;

import javafx.scene.Group;

/**
 * Created by dsalas on 9/25/16.
 */
public class Grid
{
  int x, y, z, size;
  // Grid 3D dimensions. E.g., 3 x 3 x 3.
  private int gridSize;
  // Cube 3D dimensions. E.g., 10 x 10 x 10
  int cubeSize;
  private Cube[][][] grid;
  private Group group = new Group();
  private int startX = -150;
  private int startY = -150;
  private int startZ = 0;
  private int space = 5;
  private int r1, r2, r3, r4;

  /**
   * Default constructor for building a static grid of 30 x 30 x 30.
   *
   * The size is set to 32 to account for a 1 x 1 x 1 transparent border.
   */
  public Grid()
  {
    gridSize = 32;
    grid = new Cube[gridSize][gridSize][gridSize];
  }

  /**
   * Constructor for building a custom sized grid. For example, if 3 is passed
   * then that'll create a 3 x 3 x 3 Grid.
   *
   * +2 is added for a transparent border.
   */
  public Grid(int gridSize, int cubeSize)
  {
    this.gridSize = gridSize;
    this.cubeSize = cubeSize;
    grid = new Cube[gridSize][gridSize][gridSize];
  }

  public void buildGrid()
  {
    int xCord = startX;
    int yCord, zCord;

    for (int x = 1; x < gridSize-1; x++)
    {
      yCord = startY;
      for (int y = 1; y < gridSize-1; y++)
      {
        zCord = startZ;
        for (int z = 1; z < gridSize-1; z++)
        {
          Cube cell = new Cube(cubeSize, true);
          cell.setCoordinates(xCord, yCord, zCord);

          grid[x][y][z] = cell;
          group.getChildren().add(cell);
          zCord += cubeSize + space;
        }
        // Grabs the the cube at index 0 and returns size
        yCord += cubeSize + space;
      }
      xCord += cubeSize + space;
    }
  }

  public void buildNeighbors()
  {
    for (int x = 1; x < gridSize-1; x++)
    {
      for (int y = 1; y < gridSize-1; y++)
      {
        for (int z = 1; z < gridSize-1; z++)
        {
          Cube cell = grid[x][y][z];
          cell.setNeighbors(grid, x, y, z);
          cell.calculateNeighbors();
        }
      }
    }
  }

  public Group getGroup()
  {
    return group;
  }

  public void setGroup(Group group)
  {
    this.group = group;
  }

  public void setRinput(int r1, int r2, int r3, int r4)
  {
    this.r1 = r1;
    this.r2 = r2;
    this.r3 = r3;
    this.r4 = r4;
  }

  public int getR1()
  {
    return this.r1;
  }

  public int getR2()
  {
    return this.r2;
  }

  public int getR3()
  {
    return this.r3;
  }

  public int getR4()
  {
    return this.r4;
  }

  public int getGridSize()
  {
    return this.gridSize;
  }

  public Cube getCell(int x, int y, int z)
  {
    return grid[x][y][z];
  }

  public void addCell(Cube cell, int x, int y, int z)
  {
    grid[x][y][z] = cell;
  }

  public Cube[][][] getGrid()
  {
    return this.grid;
  }
}
