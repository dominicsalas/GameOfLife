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
    Scene scene = new Scene(root, 800, 800, true);
    scene.setFill(Color.WHEAT);
    //primaryStage.setScene(new Scene(root, 800, 800));

    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setNearClip(0.1);
    camera.setFarClip(10000.0);
    camera.setTranslateZ(-1000);
    scene.setCamera(camera);

    Grid grid = new Grid(2, 100);
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

    final Box cube1 = new Box(100, 100, 100);
    final Box cube2 = new Box(100, 100, 100);
    final Box cube3 = new Box(100, 100, 100);
    final Box cube4 = new Box(100, 100, 100);
    final Box cube5 = new Box(100, 100, 100);
    final Box cube6 = new Box(100, 100, 100);
    final Box cube7 = new Box(100, 100, 100);
    final Box cube8 = new Box(100, 100, 100);
    cube1.setMaterial(greenMaterial);
    cube2.setMaterial(blueMaterial);
    cube3.setMaterial(coralMaterial);
    cube4.setMaterial(cyanMaterial);
    cube5.setMaterial(greenMaterial);
    cube6.setMaterial(blueMaterial);
    cube7.setMaterial(coralMaterial);
    cube8.setMaterial(cyanMaterial);

    cube1.setTranslateX(0);
    cube2.setTranslateX(0);
    cube3.setTranslateX(0);
    cube4.setTranslateX(0);
    cube5.setTranslateX(120);
    cube6.setTranslateX(120);
    cube7.setTranslateX(120);
    cube8.setTranslateX(120);

    cube1.setTranslateY(0);
    cube2.setTranslateY(0);
    cube3.setTranslateY(120);
    cube4.setTranslateY(120);
    cube5.setTranslateY(0);
    cube6.setTranslateY(0);
    cube7.setTranslateY(120);
    cube8.setTranslateY(120);

    cube1.setTranslateZ(0);
    cube2.setTranslateZ(-120);
    cube3.setTranslateZ(0);
    cube4.setTranslateZ(-120);
    cube5.setTranslateZ(0);
    cube6.setTranslateZ(-120);
    cube7.setTranslateZ(0);
    cube8.setTranslateZ(-120);
    root.getChildren().addAll(cube1, cube2, cube3, cube4, cube5, cube6, cube7, cube8);
    */

    //Group group = new Group(grid.getGroup());
    //grid.getGroup().setRotationAxis(Rotate.Y_AXIS);
    //grid.getGroup().setRotate(0);
    //group.setRotationAxis(Rotate.Y_AXIS);
    //group.setRotate(120);
    //group.setRotationAxis(Rotate.X_AXIS);
    //group.setRotate(45);

    //root.getChildren().addAll(grid.getGroup());
    grid.getGroup().setRotationAxis(Rotate.Y_AXIS);
    root.getChildren().addAll(grid.getGroup());
    //root.setRotationAxis(Rotate.Y_AXIS);
    //root.setRotate(120);

    Loop loop = new Loop(grid.getGroup());

    primaryStage.setTitle("Game of Life");
    primaryStage.setScene(scene);
    primaryStage.show();
    loop.start();
  }


  public static void main(String[] args)
  {
    launch(args);
  }
}
