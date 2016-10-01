package test;

/**
 * Created by dsalas on 9/19/16.
 */
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class SimpleScene3D extends Application {

  private PerspectiveCamera camera;
  private final double cameraModifier = 50.0;
  private final double cameraQuantity = 10.0;
  private final double sceneWidth = 800;
  private final double sceneHeight = 800;
  private double mouseXold = 0;
  private double mouseYold = 0;
  private final double cameraYlimit = 15;
  private final double rotateModifier = 25;


  @Override
  public void start(Stage primaryStage) {

    //Build your Scene and Camera
    Group sceneRoot = new Group();
    Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight);
    scene.setFill(Color.WHEAT);
    //scene.setFill(Color.BLACK);
    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setNearClip(0.1);
    camera.setFarClip(10000.0);
    camera.setTranslateZ(-1000);
    scene.setCamera(camera);

    //Add a primitive
    /**
    final Cylinder cylinder = new Cylinder(50, 100);
    final PhongMaterial blueMaterial = new PhongMaterial();
    //blueMaterial.setDiffuseColor(Color.DARKBLUE);
    blueMaterial.setDiffuseColor(Color.TURQUOISE);
    //blueMaterial.setSpecularColor(Color.BLUE);
    blueMaterial.setSpecularColor(Color.DARKBLUE);
    cylinder.setMaterial(blueMaterial);
    //sceneRoot.getChildren().add(cylinder);

    //Translate and Rotate primitive into position
    cylinder.setRotationAxis(Rotate.X_AXIS);
    //cylinder.setRotationAxis(Rotate.Z_AXIS);
    cylinder.setRotate(45);
    cylinder.setTranslateZ(-200);
    //cylinder.setTranslateY(90);
     **/

    //Add and Transform more primitives
    final PhongMaterial blueMaterial = new PhongMaterial();
    //greenMaterial.setDiffuseColor(Color.DARKGREEN);
    blueMaterial.setDiffuseColor(Color.LIGHTBLUE);
    //greenMaterial.setDiffuseColor(Color.web("#ffff0080"));
    //greenMaterial.setSpecularColor(Color.GREEN);
    blueMaterial.setSpecularColor(Color.BLUE);
    //final Box cube = new Box(50, 50, 50);
    final Box cube = new Box(10, 10, 10);
    final Box cube2 = new Box(10, 10, 10);
    cube.setMaterial(blueMaterial);
    cube2.setMaterial(blueMaterial);

    cube.setRotationAxis(Rotate.Y_AXIS);
    //cube.setRotate(45);
    cube.setTranslateX(-150);
    cube2.setTranslateX(-162);
    cube.setTranslateY(-150);
    cube2.setTranslateY(-150);
    //cube.setTranslateZ(150);

    //sceneRoot.getChildren().addAll(cylinder,cube,sphere);

    //All Together Now: Grouped Primitives
    //Group primitiveGroup = new Group(cylinder,cube,sphere);
    Group primitiveGroup = new Group(cube, cube2);
    //primitiveGroup.setRotationAxis(Rotate.Z_AXIS);
    primitiveGroup.setRotationAxis(Rotate.Y_AXIS);
    //primitiveGroup.setRotate(180); //Rotate the Group as a whole
    primitiveGroup.setRotate(0); //Rotate the Group as a whole
    sceneRoot.getChildren().addAll(primitiveGroup);

    primaryStage.setTitle("SimpleScene3D");
    primaryStage.setScene(scene);

    /*
    //Primitive Picking for Primitives
    scene.setOnMouseClicked(event-> {
      Node picked = event.getPickResult().getIntersectedNode();
      if(null != picked) {
        double scalar = 2;
        if(picked.getScaleX() > 1)
          scalar = 1;
        picked.setScaleX(scalar);
        picked.setScaleY(scalar);
        picked.setScaleZ(scalar);
      }
    });
    */

    //Add a Movement Keyboard Handler
    scene.setOnKeyPressed(event -> {
      double change = cameraQuantity;
      //Add shift modifier to simulate "Running Speed"
      if(event.isShiftDown()) { change = cameraModifier; }
      //What key did the user press?
      KeyCode keycode = event.getCode();
      //Step 2c: Add Zoom controls
      if(keycode == KeyCode.W) { camera.setTranslateZ(camera.getTranslateZ() + change); }
      if(keycode == KeyCode.S) { camera.setTranslateZ(camera.getTranslateZ() - change); }
      //Step 2d:  Add Strafe controls
      if(keycode == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change); }
      if(keycode == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change); }
    });

    /*
    //Add a Camera Control Mouse Event handler
    Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
    Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
    camera.getTransforms().addAll(xRotate,yRotate);
    scene.addEventHandler(MouseEvent.ANY, event -> {
      if (event.getEventType() == MouseEvent.MOUSE_PRESSED ||
              event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
        //acquire the new Mouse coordinates from the recent event
        double mouseXnew  = event.getSceneX();
        double mouseYnew = event.getSceneY();
        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
          //calculate the rotational change of the camera pitch
          double pitchRotate =xRotate.getAngle()+(mouseYnew - mouseYold) / rotateModifier;
          //set min/max camera pitch to prevent camera flipping
          pitchRotate = pitchRotate > cameraYlimit ? cameraYlimit : pitchRotate;
          pitchRotate = pitchRotate < -cameraYlimit ? -cameraYlimit : pitchRotate;
          //replace the old camera pitch rotation with the new one.
          xRotate.setAngle(pitchRotate);
          //calculate the rotational change of the camera yaw
          double yawRotate=yRotate.getAngle()-(mouseXnew - mouseXold) / rotateModifier;
          yRotate.setAngle(yawRotate);
        }
        mouseXold = mouseXnew;
        mouseYold = mouseYnew;
      }
    });
    */

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
