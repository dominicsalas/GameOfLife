package common;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Dominic Salas
 *
 * Settings GUI
 */
public class SettingsGUI
{
  Loop loop;
  public SettingsGUI(Loop loop)
  {
    this.loop = loop;
  }

  private class Presets extends Button implements EventHandler<ActionEvent>
  {
    int presetNumber;

    Presets(int num)
    {
      super("Option " + num);
      presetNumber = num;
      setOnAction(this);
      setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    @Override
    public void handle(ActionEvent event)
    {
      loop.stop();
    }
  }
}
