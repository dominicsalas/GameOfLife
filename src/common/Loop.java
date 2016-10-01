package common;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

/**
 * Dominic Salas
 *
 * Loop that allows the game to continue to run
 */
class Loop extends AnimationTimer
{
  private static final double RATE = 0.25;
  private Group group;
  private Grid grid;

  /**
   * Loop constructor that is being passed the group of cells and grid
   * @param g
   * @param grid
   */
  Loop(Group g, Grid grid)
  {
    super();
    group = g;
    this.grid = grid;
  }

  /**
   * Built in method that is repeatedly being called.
   * @param now
   */
  @Override
  public void handle(long now)
  {
    //double angle = 360.0*now*RATE/1e9;
    double angle = 90*now*RATE/1e9;

    //group.setRotationAxis();
    //group.getTransforms().add(new Rotate(45, 0, 20, 0));
    group.setRotate(angle);

    gamePlay();
  }

  /**
   * Continuous gameplay
   */
  private void gamePlay()
  {
    for (int x = 1; x < grid.getGridSize()-1; x++)
    {
      for (int y = 1; y < grid.getGridSize()-1; y++)
      {
        for (int z = 1; z < grid.getGridSize()-1; z++)
        {
          Cube cell = grid.getCell(x, y, z);
          checkCell(cell);
        }
      }
    }

    grid.buildNeighbors();
  }

  /**
   * Checks each cell and then updates it
   * @param cell
   */
  private void checkCell(Cube cell)
  {
    if (cell.getStatus() == 0)
    {
      if (cell.getNeighborsAlive() > grid.getR3()
              || cell.getNeighborsAlive() < grid.getR4())
      {
        cell.setStatus(2);
        cell.setTransitionSize(cell.getSize());
      }
    }
    else if(cell.getStatus() == 1)
    {
      if (cell.getNeighborsAlive() >= grid.getR1()
              && cell.getNeighborsAlive() <= grid.getR2())
      {
        cell.setStatus(3);
        cell.setTransitionSize(1);
      }
    }

    cell.updateCell();
  }
}
