package common;

import javafx.scene.Group;

/**
 * Created by dsalas on 9/25/16.
 */
public class Grid
{
  int x, y, z, size;
  // Grid 3D dimensions. E.g., 3 x 3 x 3.
  int gridSize;
  // Cube 3D dimensions. E.g., 10 x 10 x 10
  int cubeSize;
  private Cube[][][] grid;
  private Group group = new Group();
  private int startX = -150;
  private int startY = -150;
  private int startZ = 0;
  private int space = 5;

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

    //int space = 5; // Space between cubes
    int xCord = startX;
    int yCord, zCord;

    //for (int x = 0; x < grid.length; x++)
    for (int x = 1; x < gridSize-1; x++)
    {
      yCord = startY;
      //for (int y = 0; y < grid[y].length; y++)
      for (int y = 1; y < gridSize-1; y++)
      {
        zCord = startZ;
        //for (int z = 0; z < grid[y].length; z++)
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

  public void buildNeighbors()
  {
    //for (int x = 0; x < grid.length; x++)
    for (int x = 1; x < gridSize-1; x++)
    {
      //for (int y = 0; y < grid[y].length; y++)
      for (int y = 1; y < gridSize-1; y++)
      {
        //for (int z = 0; z < grid[y].length; z++)
        for (int z = 1; z < gridSize-1; z++)
        {
          Cube cell = grid[x][y][z];
          cell.setNeighbors(grid, x, y, z);
        }
      }
    }
  }

  public Group getGroup()
  {
    return group;
  }
}
