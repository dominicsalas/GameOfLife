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
import javafx.scene.control.MenuItem;
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
  private boolean preset1, preset2, preset3, preset4;

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
  private MenuItem presetOne;
  @FXML
  private MenuItem presetTwo;
  @FXML
  private MenuItem presetThree;
  @FXML
  private MenuItem presetFour;


  @FXML
  public void handleButtonAction(ActionEvent e)
  {
    if (e.getSource() == presetOne)
    {
      r1TextArea.setText("4");
      r2TextArea.setText("4");
      r3TextArea.setText("3");
      r4TextArea.setText("4");
      gridTextArea.setText("32");
      cubeTextArea.setText("7");
      setPresetBooleans(1);
    }
    else if (e.getSource() == presetTwo)
    {
      r1TextArea.setText("4");
      r2TextArea.setText("4");
      r3TextArea.setText("4");
      r4TextArea.setText("3");
      gridTextArea.setText("32");
      cubeTextArea.setText("7");
      setPresetBooleans(2);
    }
    else if (e.getSource() == presetThree)
    {
      r1TextArea.setText("4");
      r2TextArea.setText("5");
      r3TextArea.setText("3");
      r4TextArea.setText("4");
      gridTextArea.setText("32");
      cubeTextArea.setText("7");
      setPresetBooleans(3);
    }
    else if (e.getSource() == presetFour)
    {
      r1TextArea.setText("6");
      r2TextArea.setText("6");
      r3TextArea.setText("7");
      r4TextArea.setText("5");
      gridTextArea.setText("32");
      cubeTextArea.setText("7");
      setPresetBooleans(4);
    }

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

        if (preset1 || preset2 || preset3)
        {
          grid = new Grid(gridSize, cubeSize);
          grid.buildGridAllDead();
          grid.setFirstPresets();
          gameGui();
        }
        else if (preset4)
        {
          // DO SOMETHING DIFFERENT BELOW
          grid = new Grid(gridSize, cubeSize);
          grid.buildGridAllDead();
          grid.setPresetFour();
          gameGui();
        }
        else
        {
          grid = new Grid(gridSize, cubeSize);
          grid.buildGrid();
          gameGui();
        }
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

  public void setPresetBooleans(int preset)
  {
    if (preset == 1)
    {
      preset1 = true;

      preset2 = false;
      preset3 = false;
      preset4 = false;
    }
    else if (preset == 2)
    {
      preset2 = true;

      preset1 = false;
      preset3 = false;
      preset4 = false;
    }
    else if (preset == 3)
    {
      preset3 = true;

      preset1 = false;
      preset2 = false;
      preset4 = false;
    }
    else if (preset == 4)
    {
      preset4 = true;

      preset1 = false;
      preset2 = false;
      preset3 = false;
    }
    else
    {
      System.out.println("Unrecognized preset");

      preset1 = false;
      preset2 = false;
      preset3 = false;
      preset4 = false;
    }
  }
}
