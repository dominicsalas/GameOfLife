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
  private int startX = 0;
  private int startY = 0;
  private int startZ = 0;

  /**
   * Default constructor for, which creates a 3 x 3 x 3 Grid
   */
  public Grid(int gridSize, int cubeSize)
  {
    this.gridSize = gridSize;
    this.cubeSize = cubeSize;
    grid = new Cube[gridSize][gridSize][gridSize];
  }

  public Grid(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
    grid = new Cube[this.x][this.y][this.z];
  }

  public void buildGrid()
  {

    int space = 20; // Space between cubes
    int xCord = startX;
    int yCord, zCord;

    //for (int x = 0; x < grid.length; x++)
    for (int x = 0; x < gridSize; x++)
    {
      yCord = startY;
      //for (int y = 0; y < grid[y].length; y++)
      for (int y = 0; y < gridSize; y++)
      {
        zCord = startZ;
        //for (int z = 0; z < grid[y].length; z++)
        for (int z = 0; z < gridSize; z++)
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

  public Group getGroup()
  {
    return group;
  }
}
