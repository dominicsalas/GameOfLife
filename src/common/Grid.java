package common;

import javafx.scene.Group;

/**
 * Dominic Salas
 *
 * Builds the overall grid
 */
public class Grid
{
  private int gridSize;
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

  /**
   * Method for the actual building grid logic.
   */
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
          Cube cell = new Cube(cubeSize);
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

  /**
   * Builds the neighbor collection for each cell
   */
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

  /**
   * Returns the group of cells
   * @return group
   */
  public Group getGroup()
  {
    return group;
  }

  /**
   * Sets the game rules
   * @param r1
   * @param r2
   * @param r3
   * @param r4
   */
  public void setRinput(int r1, int r2, int r3, int r4)
  {
    this.r1 = r1;
    this.r2 = r2;
    this.r3 = r3;
    this.r4 = r4;
  }

  /**
   * Returns r1
   * @return r1
   */
  public int getR1()
  {
    return this.r1;
  }

  /**
   * Returns r2
   * @return r2
   */
  public int getR2()
  {
    return this.r2;
  }

  /**
   * returns r3
   * @return r3
   */
  public int getR3()
  {
    return this.r3;
  }

  /**
   * Returns r4
   * @return
   */
  public int getR4()
  {
    return this.r4;
  }

  /**
   * Returns the size of the grid
   * @return grid size
   */
  public int getGridSize()
  {
    return this.gridSize;
  }

  /**
   * Returns a specific cell
   * @param x
   * @param y
   * @param z
   * @return
   */
  public Cube getCell(int x, int y, int z)
  {
    return grid[x][y][z];
  }
}
