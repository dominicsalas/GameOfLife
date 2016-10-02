package common;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * Dominic Salas
 *
 * The GUI class. This handles switching back and forth between windows.
 */
public class GUI extends Application
{
  private static Stage theStage;
  private Scene settingsScene;
  private Loop loop;
  private int r1, r2, r3, r4;
  Grid grid;
  private Button backButton;
  private int gridSize, cubeSize;

  @FXML
  private Button btnGameScene;
  @FXML
  private Button btnLoadDefaults;
  @FXML
  private TextField r1TextArea;
  @FXML
  private TextField r2TextArea;
  @FXML
  private TextField r3TextArea;
  @FXML
  private TextField r4TextArea;
  @FXML
  private TextField gridTextArea;
  @FXML
  private TextField cubeTextArea;


  @FXML
  public void handleButtonAction(ActionEvent e)
  {
    if (e.getSource() == btnLoadDefaults)
    {
      r1TextArea.setText("5");
      r2TextArea.setText("5");
      r3TextArea.setText("6");
      r4TextArea.setText("3");
      gridTextArea.setText("32");
      cubeTextArea.setText("7");
    }

    if (e.getSource() == btnGameScene)
    {
      // Builds grid using default settings with some or all fields not filled in
      if (r1TextArea.getText().isEmpty() || r2TextArea.getText().isEmpty() ||
              r3TextArea.getText().isEmpty() || r4TextArea.getText().isEmpty())
      {
        r1 = 5;
        r2 = 5;
        r3 = 6;
        r4 = 6;

        // Checks if grid and cube fields are blank.
        checkGridCubeFields();

        grid = new Grid(gridSize, cubeSize);
        grid.buildGrid();
        gameGui();
      }
      else
      {
        r1 = Integer.parseInt(r1TextArea.getText());
        r2 = Integer.parseInt(r2TextArea.getText());
        r3 = Integer.parseInt(r3TextArea.getText());
        r4 = Integer.parseInt(r4TextArea.getText());

        // Checks if grid and cube fields are blank.
        checkGridCubeFields();

        grid = new Grid(gridSize, cubeSize);
        grid.buildGrid();
        gameGui();
      }
    }
  }

  private boolean validateFieldInput()
  {
    try
    {
      r1 = Integer.parseInt(r1TextArea.getText());
      r2 = Integer.parseInt(r2TextArea.getText());
      r3 = Integer.parseInt(r3TextArea.getText());
      r4 = Integer.parseInt(r4TextArea.getText());
      gridSize = Integer.parseInt(gridTextArea.getText());
      cubeSize = Integer.parseInt(cubeTextArea.getText());
    }
    catch (IllegalArgumentException e1)
    {
      e1.printStackTrace();
      try
      {
        start(theStage);
      } catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return true;
  }

  private void checkGridCubeFields()
  {
    if (gridTextArea.getText().isEmpty() || cubeTextArea.getText().isEmpty())
    {
      gridSize = 32;
      cubeSize = 7;
    }
    else
    {
      gridSize = Integer.parseInt(gridTextArea.getText());
      cubeSize = Integer.parseInt(cubeTextArea.getText());
    }
  }

  /**
   * Builds the GUI game
   */
  private void gameGui()
  {
    Group root = new Group();
    Scene scene = new Scene(root, 800, 800, true);
    scene.setFill(Color.BLACK);

    PerspectiveCamera camera = new PerspectiveCamera(true);
    camera.setNearClip(0.1);
    camera.setFarClip(10000.0);
    camera.setTranslateZ(-1000);
    scene.setCamera(camera);

    // Add Button
    backButton = new Button("Go Back");

    backButton.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        loop.stop();
        try
        {
          start(theStage);
        } catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
    backButton.setTranslateX(-250);
    backButton.setTranslateY(-250);
    root.getChildren().add(backButton);

    grid.setRinput(r1, r2, r3, r4);
    grid.buildNeighbors();

    grid.getGroup().setRotationAxis(Rotate.Y_AXIS);
    root.getChildren().addAll(grid.getGroup());

    loop = new Loop(grid.getGroup(), grid);

    theStage.setScene(scene);
    loop.start();
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    theStage = primaryStage;
    Parent homepage = FXMLLoader.load(getClass().getResource("gui.fxml"));
    settingsScene = new Scene(homepage, 800, 800);

    primaryStage.setTitle("Game of Life");
    primaryStage.setScene(settingsScene);
    primaryStage.show();
  }
}
