package common;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * Dominic Salas
 *
 * Main class the initiates the program and the GUI.
 */
public class Main extends Application
{
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Group root = new Group();
    //Scene scene = new Scene(root, 800, 800, true);
    Group settingsGroup = new Group();
    SubScene settings = new SubScene(settingsGroup, 800, 800);
    Group guiGroup = new Group();
    SubScene guiScene = new SubScene(guiGroup, 800, 800);
    root.getChildren().addAll(settingsGroup, guiGroup);
    Scene scene = new Scene(root, 800, 800, true);
    //scene.setFill(Color.WHEAT);
    scene.setFill(Color.BLACK);
    //primaryStage.setScene(new Scene(root, 800, 800));

    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setNearClip(0.1);
    camera.setFarClip(10000.0);
    camera.setTranslateZ(-1000);
    scene.setCamera(camera);

    Grid grid = new Grid(32, 7);
    //Grid grid = new Grid(12, 20);
    grid.buildGrid();
    grid.setRinput(5, 5, 6, 3);
    //grid.setRinput(2, 4, 3, 1);
    grid.buildNeighbors();

    grid.getGroup().setRotationAxis(Rotate.Y_AXIS);
    root.getChildren().addAll(grid.getGroup());
    //root.setRotationAxis(Rotate.Y_AXIS);
    //root.setRotate(120);

    Loop loop = new Loop(grid.getGroup(), grid);

    primaryStage.setTitle("Game of Life");
    primaryStage.setScene(scene);
    primaryStage.show();
    loop.start();
  }

  /**
   * Main method
   * @param args
   */
  public static void main(String[] args)
  {
    launch(args);
  }
}
