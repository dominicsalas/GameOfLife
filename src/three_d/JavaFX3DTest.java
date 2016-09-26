package three_d;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class JavaFX3DTest extends Application
{
  private static final double LIMIT = 315.0;
  private static final String RESOURCES =
    "/three_d/";
  private static final String BUMP_MAP1 = RESOURCES+"4268-bump.jpg";
  private static final String BUMP_MAP2 = RESOURCES+"granite.jpg";
  private static final String DIFF_MAP = RESOURCES+"GOPR0283.JPG";

  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    Group root = new Group();
    Scene scene = new Scene(root, 600, 600, true);
    Camera camera = makeCamera();
    Shape3D box = makeBox();
    //TileMaker tm = new TileMaker();

    scene.setFill(Color.GRAY);
    root.getChildren().add(littleBalls());
    root.getChildren().add(tile());
    root.getChildren().add(walls());
    root.getChildren().add(bigBalls());

    scene.setCamera(camera);
    primaryStage.setScene(scene);

    //root.getChildren().add(box);
    Loop loop = new Loop(box);
    scene.setOnKeyPressed(new Keyboard(camera));
    primaryStage.show();
    loop.start();
  }

  private Camera makeCamera()
  {
    Camera camera = new PerspectiveCamera(true);

    camera.setNearClip(0.01);
    camera.setFarClip(200);
    camera.setTranslateX(0);
    camera.setTranslateY(-5);
    camera.setTranslateZ(-100);
    camera.setRotationAxis(new Point3D(0.0, 1.0, 0.0));

    return camera;
  }

  private Shape3D makeBox()
  {
    Box box = new Box(10, 10, 10);
    PhongMaterial boxMaterial = new PhongMaterial();

    box.setTranslateX(0.0);
    box.setTranslateX(0.0);
    box.setTranslateX(0.0);
    box.setRotationAxis(new Point3D(1.0, 1.0, 1.0));

    boxMaterial.setSpecularColor(Color.GREEN);
    boxMaterial.setDiffuseMap(new Image(DIFF_MAP));
    box.setMaterial(boxMaterial);

    return box;
  }

  private Group littleBalls()
  {
    PhongMaterial defaultMaterial = new PhongMaterial();
    Group group = new Group();

    defaultMaterial.setDiffuseColor(Color.DARKGRAY);
    defaultMaterial.setSpecularColor(Color.GREEN);
    defaultMaterial.setBumpMap(new Image(BUMP_MAP1));

    for (double x = -LIMIT; x<=LIMIT; x += 30.0)
    {
      for (double y = -LIMIT; y<=LIMIT; y += 30.0)
      {
        for (double z = -LIMIT; z<=LIMIT; z += 30.0)
        {
          Shape3D point = new Sphere(2.0);
          point.setMaterial(defaultMaterial);
          point.setTranslateX(x);
          point.setTranslateY(y);
          point.setTranslateZ(z);
          group.getChildren().add(point);
        }
      }
    }
    return group;
  }

  private Group bigBalls()
  {
    Group group = new Group();

    for (int x = -1; x<=1; x += 2)
    {
      for (int y = -1; y<=1; y += 2)
      {
        for (int z = -1; z<=1; z += 2)
        {
          Shape3D point = new Sphere(5);
          PhongMaterial material = new PhongMaterial();
          material.setDiffuseColor(
            Color.rgb(x<0 ? 0 : 255, y<0 ? 0 : 255, z<0 ? 0 : 255));
          material.setSpecularColor(Color.GREEN);
          material.setBumpMap(new Image(BUMP_MAP2));
          point.setTranslateX(15*x);
          point.setTranslateY(15*y);
          point.setTranslateZ(15*z);
          point.setMaterial(material);
          group.getChildren().add(point);
        }
      }
    }
    return group;
  }

  private Group tile()
  {
    Group group = new Group();

    double y = 0;
    for (int i = 0; i<20; i++)
    {
      double x = -20.0+i*2.0;
      for (int j = 0; j<20; j++)
      {
        double z = -20.0+j*2.0;
        Group shape = TileMaker.tile((i+j)%4);
        shape.setTranslateX(x);
        shape.setTranslateY(y);
        shape.setTranslateZ(z);
        group.getChildren().add(shape);
      }
    }
    return group;
  }

  private Group walls()
  {
    Group group = new Group();
    double y = 0;
    for (int i = 0; i<21; i++)
    {
      for (int j = 0; j<4; j++)
      {
        double x, z;
        switch (j)
        {
          case 0:
            x = -22.0;
            z = -20+2.0*i;
            break;
          case 1:
            x = -20+2.0*i;
            z = 22.0;
            break;
          case 2:
            x = 22.0;
            z = 20-2*i;
            break;
          case 3:
            x = 20-2*i;
            z = -22;
            break;
          default:
            x=z=0;
        }
        Group wall = TileMaker.wall(i%4);
        wall.setTranslateX(x);
        wall.setTranslateY(y);
        wall.setTranslateZ(z);
        group.getChildren().add(wall);
      }
    }
    return group;
  }
}