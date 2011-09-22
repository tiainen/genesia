package net.sertik.genesia.entity;

import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author joeri
 */
public class Player {
  public static final Color[] COLORS = {
    Color.WHITE, Color.BLUE, Color.ORANGE
  };
  public static final String[] NAMES = {
    "Edward", "Karnac"
  };

  private boolean computer;
  private String name;
  private Color color;
  private List<Land> lands;

  public boolean getComputer() {
    return computer;
  }

  public void setComputer(boolean computer) {
    this.computer = computer;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Land> getLands() {
    return lands;
  }

  public void setLands(List<Land> lands) {
    this.lands = lands;
  }
}
