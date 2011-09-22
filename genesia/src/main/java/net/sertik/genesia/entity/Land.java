package net.sertik.genesia.entity;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joeri
 */
public class Land {
  private String name;
  private int x;
  private int y;
  private Player owner;
  private List<Tile> tiles = new LinkedList<Tile>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public List<Tile> getTiles() {
    return tiles;
  }

  public void setTiles(List<Tile> tiles) {
    this.tiles = tiles;
  }
}
