package common;

/**
 * Created by dsalas on 9/26/16.
 */
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

class Loop extends AnimationTimer
{
  private static final double RATE = 0.25;
  private Group group, newGroup;
  private Grid currentGrid, newGrid;

  Loop(Group g, Grid currentGrid, Grid newGrid)
  {
    super();
    group = g;
    newGroup = new Group();
    this.currentGrid = currentGrid;
    this.newGrid = newGrid;
  }

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

  private void gamePlay()
  {
    for (int x = 1; x < currentGrid.getGridSize()-1; x++)
    {
      for (int y = 1; y < currentGrid.getGridSize()-1; y++)
      {
        for (int z = 1; z < currentGrid.getGridSize()-1; z++)
        {
          Cube cell = currentGrid.getCell(x, y, z);
          checkCell(cell); //Without synchronization

          //Cube newCell = new Cube(cell);
          //Cube newCell = new Cube(cell.getSize(), cell.getStatus(),
          //        cell.getNeighbors(), cell.getCubeMaterial());
          //newCell.setCoordinates(cell.getX(), cell.getY(), cell.getZ());
          //newCell.calculateNeighbors();
          //Cube newCell = new Cube(cell);
          //checkCell(newCell);
          //newGrid.addCell(newCell, x, y, z);
          //newGroup.getChildren().add(newCell);
        }
      }
    }

    //group = newGroup;
    //newGrid.setGroup(group);
    currentGrid = newGrid;
    currentGrid.buildNeighbors();
  }

  private void checkCell(Cube cell)
  {
    if (cell.getStatus() == 0)
    {
      if (cell.getNeighborsAlive() > currentGrid.getR3()
              || cell.getNeighborsAlive() < currentGrid.getR4())
      {
        cell.setStatus(2);
        cell.setTransitionSize(cell.getSize());
      }
    }
    else if(cell.getStatus() == 1)
    {
      if (cell.getNeighborsAlive() >= currentGrid.getR1()
              && cell.getNeighborsAlive() <= currentGrid.getR2())
      {
        cell.setStatus(3);
        cell.setTransitionSize(1);
      }
    }

    cell.updateCell();
  }
}
