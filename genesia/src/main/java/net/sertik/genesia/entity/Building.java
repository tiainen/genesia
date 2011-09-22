package net.sertik.genesia.entity;

/**
 *
 * @author joeri
 */
public class Building {
  private String name;
  private int gold;
  private int wood;
  private int metal;
  private int stone;
  private boolean requiresArchitect;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public int getMetal() {
    return metal;
  }

  public void setMetal(int metal) {
    this.metal = metal;
  }

  public boolean getRequiresArchitect() {
    return requiresArchitect;
  }

  public void setRequiresArchitect(boolean requiresArchitect) {
    this.requiresArchitect = requiresArchitect;
  }

  public int getStone() {
    return stone;
  }

  public void setStone(int stone) {
    this.stone = stone;
  }

  public int getWood() {
    return wood;
  }

  public void setWood(int wood) {
    this.wood = wood;
  }
}
