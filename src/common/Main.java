package common;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Main extends Application
{
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Group root = new Group();
    Scene scene = new Scene(root, 800, 800);
    scene.setFill(Color.WHEAT);
    //primaryStage.setScene(new Scene(root, 800, 800));

    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setNearClip(0.1);
    camera.setFarClip(10000.0);
    camera.setTranslateZ(-1000);
    scene.setCamera(camera);

    Grid grid = new Grid(2, 50);
    grid.buildGrid();

    /*
    final PhongMaterial blueMaterial = new PhongMaterial();
    blueMaterial.setDiffuseColor(Color.LIGHTBLUE);
    blueMaterial.setSpecularColor(Color.BLUE);
    final PhongMaterial greenMaterial = new PhongMaterial();
    greenMaterial.setDiffuseColor(Color.LIGHTGREEN);
    blueMaterial.setSpecularColor(Color.GREEN);
    final PhongMaterial coralMaterial = new PhongMaterial();
    coralMaterial.setDiffuseColor(Color.LIGHTCORAL);
    coralMaterial.setSpecularColor(Color.CORAL);
    final PhongMaterial cyanMaterial = new PhongMaterial();
    cyanMaterial.setDiffuseColor(Color.LIGHTCYAN);
    cyanMaterial.setSpecularColor(Color.CYAN);

    final Box cube1 = new Box(10, 10, 10);
    final Box cube2 = new Box(10, 10, 10);
    final Box cube3 = new Box(10, 10, 10);
    final Box cube4 = new Box(10, 10, 10);
    cube1.setMaterial(greenMaterial);
    cube2.setMaterial(blueMaterial);
    cube3.setMaterial(coralMaterial);
    cube4.setMaterial(cyanMaterial);

    cube1.setTranslateX(-150);
    cube1.setTranslateY(-150);

    cube2.setTranslateX(-150);
    cube2.setTranslateY(-138);

    cube3.setTranslateX(-138);
    cube3.setTranslateY(-150);

    cube4.setTranslateX(-138);
    cube4.setTranslateY(-138);

    root.getChildren().addAll(cube1, cube2, cube3, cube4);
    */
    //Group group = new Group(grid.getGroup());
    //group.setRotationAxis(Rotate.Y_AXIS);
    //group.setRotate(45);
    //group.setRotationAxis(Rotate.X_AXIS);
    //group.setRotate(45);

    root.getChildren().addAll(grid.getGroup());
    root.setRotationAxis(Rotate.Y_AXIS);
    root.setRotate(45);

    primaryStage.setTitle("Game of Life");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  public static void main(String[] args)
  {
    launch(args);
  }
}
