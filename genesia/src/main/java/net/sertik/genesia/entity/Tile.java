package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joeri
 */
public class Tile {
  private int x;
  private int y;
  private Land land;
  private List<GameObject> objects = new LinkedList<GameObject>();

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Land getLand() {
    return land;
  }

  public void setLand(Land land) {
    this.land = land;
  }

  public List<GameObject> getObjects() {
    return objects;
  }

  public void setObjects(List<GameObject> objects) {
    this.objects = objects;
  }
}
